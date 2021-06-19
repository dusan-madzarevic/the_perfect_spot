package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.cep.PopularRecipeEvent;
import com.ftn.uns.ac.rs.theperfectmeal.cep.WrongCredentialsEvent;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeCalcInfo;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.helper.RecipeMapper;
import com.ftn.uns.ac.rs.theperfectmeal.model.Alarm;
import com.ftn.uns.ac.rs.theperfectmeal.model.AlarmType;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.model.RegisteredUser;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RecipeRepository;
import com.ftn.uns.ac.rs.theperfectmeal.templates.DifficultyCategoryTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.FilterByDishTypesTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.SearchRecipesByNameTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.util.LoginAlarm;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplMapper;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplementation;
import com.ftn.uns.ac.rs.theperfectmeal.util.PopularBadRatedRecipeAlarm;
import com.ftn.uns.ac.rs.theperfectmeal.util.PopularRecipeAlarm;
import com.ftn.uns.ac.rs.theperfectmeal.util.RecipeType;

@Service
public class RecipeService {

	@Autowired
	private KieStatefulSessionService kieService;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeMapper recipeMapper;
	
	@Autowired
	private AlarmService alarmService;

	public RecipeDTO process(RecipeRequirements requirements) throws IOException, MavenInvocationException {

		RegisteredUser user = (RegisteredUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		this.kieService.releaseRulesSession();
		KieSession kieSession = kieService.getRulesSession();

		kieSession.insert(requirements);
		for (Recipe recipe : recipeRepository.findAll()) {
			RecipeCalcInfo info = new RecipeCalcInfo();
			info.setRecipeId(recipe.getRecipeId());
			
			kieSession.insert(recipe);
			kieSession.insert(info);
		}
		
		kieSession.getAgenda().getAgendaGroup("ingredients").setFocus();
		kieSession.fireAllRules();
		
		/*
		List<DifficultyCategoryTemplateModel> templateModels = new ArrayList<DifficultyCategoryTemplateModel>();
		
		templateModels.add(new DifficultyCategoryTemplateModel(0, 200, CookingSkill.LOW));
		templateModels.add(new DifficultyCategoryTemplateModel(200, 500, CookingSkill.MEDIUM));
		templateModels.add(new DifficultyCategoryTemplateModel(500, 9999, CookingSkill.HIGH));
		
		createRecipeDifficultyRules(templateModels);
		*/
		kieSession.getAgenda().getAgendaGroup("recipe-difficulty").setFocus();
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("recipe").setFocus();
		kieSession.fireAllRules();

		kieSession.getAgenda().getAgendaGroup("Calculating recipe score").setFocus();
		kieSession.fireAllRules();
		Recipe top = (Recipe) kieSession.getGlobal("topRecipe");
		
		kieSession.dispose();
		
		PopularRecipeEvent event = new PopularRecipeEvent(user, top);
		KieSession eventSession = kieService.getEventsSession();
		eventSession.getAgenda().getAgendaGroup("popular-recipe").setFocus();
		
		PopularRecipeAlarm popularAlarm = new PopularRecipeAlarm();
		popularAlarm.setRecipeId(0L);
		PopularBadRatedRecipeAlarm popularBadAlarm = new PopularBadRatedRecipeAlarm();
		popularBadAlarm.setRecipeId(0L);
		eventSession.setGlobal("popularRecipeAlarm", popularAlarm);
		eventSession.setGlobal("popularBadRatedRecipeAlarm", popularBadAlarm);
		eventSession.insert(event);
		eventSession.fireAllRules();	
		
		System.out.println(popularAlarm.getRecipeId());
		System.out.println(top.getRecipeId());
		System.out.println(user.getId());
		
		if(popularAlarm.getRecipeId() == top.getRecipeId()) {
			
			Alarm alarm = new Alarm(String.format("The recipe %s has been recommended to 5 different users in the past 24 hours!", top.getRecipeName()), AlarmType.POPULAR_RECIPE);
			alarmService.save(alarm);
			
		}
		
		if(popularBadAlarm.getRecipeId() == top.getRecipeId()) {
			
			Alarm alarm = new Alarm(String.format("The recipe %s has been recommended to 5 different users in the past 24 hours, but it's rating is under 2.3! Consider removing or improving this recipe.", top.getRecipeName()), AlarmType.POPULAR_RECIPE_BAD_RATING);
			alarmService.save(alarm);
			
		}
		
		return recipeMapper.toDto(top);

	}

	public ArrayList<RecipeDTO> bestGradedRecipe() {

		this.kieService.releaseRulesSession();
		KieSession session = this.kieService.getRulesSession();

		List<Recipe> bestGraded = new ArrayList<>();

		session.setGlobal("bestGraded", bestGraded);

		List<Recipe> recipes = this.recipeRepository.findAll();

		for (Recipe r : recipes) {
			session.insert(r);
		}

		session.getAgenda().getAgendaGroup("best-graded-report").setFocus();
		session.fireAllRules();
		ArrayList<RecipeDTO> recipesDto = new ArrayList<RecipeDTO>();
		for (Recipe r : bestGraded) {
			recipesDto.add(this.recipeMapper.toDto(r));
		}
		 
		return recipesDto;
	}
	
	public ArrayList<RecipeDTO> bestGradedRecipeLastMonth() {

		this.kieService.releaseRulesSession();
		KieSession session = this.kieService.getRulesSession();


		List<Recipe> recipes = this.recipeRepository.findAll();

		for (Recipe r : recipes) {
			session.insert(r);
		}
	    List<Recipe> bestGradedLastMonth = new  ArrayList<>(); 
	    session.setGlobal("bestGradedLastMonth", bestGradedLastMonth);
		

		
	   session.getAgenda().getAgendaGroup("best-graded-report-lastMonth").setFocus();
	   session.fireAllRules(); 

	   for(Recipe r: bestGradedLastMonth) 
		  System.out.println(r.getRecipeName());
	   
	   ArrayList<RecipeDTO> recipesDto = new ArrayList<RecipeDTO>();
		for (Recipe r : bestGradedLastMonth) {
			recipesDto.add(this.recipeMapper.toDto(r));
		}
		return recipesDto;
	}
	
	private boolean createRecipeDifficultyRules(List<DifficultyCategoryTemplateModel> difficultyModels) throws IOException, MavenInvocationException {
		
		InputStream template = new FileInputStream(
				"D:\\faks\\SBNZ\\drools-spring-kjar\\src\\main\\resources\\sbnz\\templates\\recipe_difficulty.drt");

		// Compile template to generate new rules
		
		
		ObjectDataCompiler compiler = new ObjectDataCompiler();
		String drl = compiler.compile(difficultyModels, template);

		// Save rule to drl file
		FileOutputStream drlFile = new FileOutputStream(new File(
				"D:\\faks\\SBNZ\\drools-spring-kjar\\src\\main\\resources\\sbnz\\integracija\\recipe_difficulty_temp.drl"));
		drlFile.write(drl.getBytes());
		drlFile.close();

		// Update Rules project
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File("D:\\faks\\SBNZ\\drools-spring-kjar\\pom.xml"));
		request.setGoals(Arrays.asList("clean", "install"));

		Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(System.getenv("M2_HOME")));
		invoker.execute(request);
		return true;
		
	}

	private KieSession createKieSessionFromDRL(String drl) {
		KieHelper kieHelper = new KieHelper();
		kieHelper.addContent(drl, ResourceType.DRL);

		Results results = kieHelper.verify();

		if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
			List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
			for (Message message : messages) {
				System.out.println("Error: " + message.getText());
			}

			throw new IllegalStateException("Compilation errors were found. Check the logs.");
		}

		return kieHelper.build().newKieSession();
	}
	
	public PageImplementation<RecipeDTO> findAll(Pageable pageable) {
		
		Page<Recipe> restaurantsPage = this.recipeRepository.findAll(pageable);
		List<RecipeDTO> dtoList = this.recipeMapper.toDtoList(restaurantsPage.toList());
		Page<RecipeDTO> recipeDtoPage = new PageImpl<RecipeDTO>(dtoList, restaurantsPage.getPageable(),
				restaurantsPage.getTotalElements());
		PageImplMapper<RecipeDTO> pageMapper = new PageImplMapper<RecipeDTO>();
		PageImplementation<RecipeDTO> pageImpl = pageMapper.toPageImpl(recipeDtoPage);

		return pageImpl;
	}

	public RecipeDTO getOne(long id) {
		Recipe r = this.recipeRepository.findById(id).orElse(null);
		RecipeDTO dto = this.recipeMapper.toDto(r);
		return dto;
	}

	public PageImplementation<RecipeDTO> filter(Pageable pageable, ArrayList<RecipeType> dishTypes) {
		List<Recipe> result = new ArrayList<Recipe>();

			InputStream template;
			try {
				template = RecipeService.class
						.getResourceAsStream("/sbnz/templates/filter_recipes_by_dish_type.drt");
				FilterByDishTypesTemplateModel dto = new FilterByDishTypesTemplateModel();
			
				dto.setDishTypes(dishTypes);
				List<FilterByDishTypesTemplateModel> arguments = new ArrayList<FilterByDishTypesTemplateModel>();
				arguments.add(dto);
				ObjectDataCompiler compiler = new ObjectDataCompiler();
				String drl = compiler.compile(arguments, template);
				System.out.println("\n\n" + drl + "\n\n");
				KieSession kieSession = createKieSessionFromDRL(drl);
				List<Recipe> recipes = this.recipeRepository.findAll();
				for (Recipe r : recipes) {
					kieSession.insert(r);
				}
				kieSession.setGlobal("recipes", result);
				kieSession.fireAllRules();
			} catch (Exception e) {
				e.printStackTrace();
			}
		List<RecipeDTO> dtoList = this.recipeMapper.toDtoList(result);
		Page<RecipeDTO> recipeDtoPage = new PageImpl<RecipeDTO>(dtoList, pageable, result.size());
		PageImplMapper<RecipeDTO> pageMapper = new PageImplMapper<RecipeDTO>();
		PageImplementation<RecipeDTO> pageImpl = pageMapper.toPageImpl(recipeDtoPage);
		return pageImpl;
	}

	public PageImplementation<RecipeDTO> search(Pageable pageable, String recipeName) {
		List<Recipe> result = new ArrayList<Recipe>();

		if (!recipeName.equalsIgnoreCase("")) {
			InputStream template;
			try {
				template = RestaurantService.class.getResourceAsStream("/sbnz/templates/search_recipe_by_name.drt");

				SearchRecipesByNameTemplateModel dto = new SearchRecipesByNameTemplateModel();
				dto.setSearchData(recipeName);
				List<SearchRecipesByNameTemplateModel> arguments = new ArrayList<>();
				arguments.add(dto);
				ObjectDataCompiler compiler = new ObjectDataCompiler();
				String drl = compiler.compile(arguments, template);

				System.out.println("\n\n" + drl + "\n\n");
				KieSession kieSession = createKieSessionFromDRL(drl);
				List<Recipe> recipes = this.recipeRepository.findAll();
				for (Recipe r : recipes) {
					kieSession.insert(r);
				}
				kieSession.setGlobal("recipes", result);
				kieSession.fireAllRules();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		List<RecipeDTO> dtoList = this.recipeMapper.toDtoList(result);
		Page<RecipeDTO> recipeDtoPage = new PageImpl<RecipeDTO>(dtoList, pageable, result.size());
		PageImplMapper<RecipeDTO> pageMapper = new PageImplMapper<RecipeDTO>();
		PageImplementation<RecipeDTO> pageImpl = pageMapper.toPageImpl(recipeDtoPage);
		return pageImpl;
	}

}
