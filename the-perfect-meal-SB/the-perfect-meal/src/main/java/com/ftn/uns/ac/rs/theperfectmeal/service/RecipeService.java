package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.dto.MessageResponse;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeCalcInfo;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.theperfectmeal.helper.RecipeMapper;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RecipeRepository;
import com.ftn.uns.ac.rs.theperfectmeal.templates.DifficultyCategoryTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.FilterByCuisinePricesTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.FilterByCuisinesTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.FilterByDishTypesTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.FilterByPricesTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.SearchRecipesByNameTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.SearchRestaurantsByNameTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplMapper;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplementation;
import com.ftn.uns.ac.rs.theperfectmeal.util.RecipeType;

@Service
public class RecipeService {

	@Autowired
	private KieStatefulSessionService kieService;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeMapper recipeMapper;

	public MessageResponse process(RecipeRequirements requirements) throws IOException, MavenInvocationException {

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
		


		Recipe topRecipe = new Recipe();
		kieSession.setGlobal("topRecipe",topRecipe);
		kieSession.getAgenda().getAgendaGroup("recipe").setFocus();
		kieSession.fireAllRules();

	
		System.out.println(topRecipe.getRecipeName());

		
		 List<Recipe> topRecipes = new ArrayList<Recipe>();
		 kieSession.setGlobal("topRecipes", topRecipes);
		 kieSession.getAgenda().getAgendaGroup("Calculating recipe score").setFocus();
		 kieSession.fireAllRules();
		 for(Recipe r: topRecipes)
			 System.out.println(r.getRecipeName());
		 
		kieSession.dispose();
		return new MessageResponse("Successfully");

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
