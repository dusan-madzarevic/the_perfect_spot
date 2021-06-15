package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
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
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantRequirementsDTO;
import com.ftn.uns.ac.rs.theperfectmeal.helper.RestaurantMapper;
import com.ftn.uns.ac.rs.theperfectmeal.model.Cuisine;
import com.ftn.uns.ac.rs.theperfectmeal.model.Prices;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RestaurantRepository;
import com.ftn.uns.ac.rs.theperfectmeal.templates.FilterByCuisinePricesTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.FilterByCuisinesTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.FilterByPricesTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.templates.SearchRestaurantsByNameTemplateModel;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplMapper;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplementation;

@Service
public class RestaurantService {

	@Autowired
	private KieStatefulSessionService kieService;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantMapper restaurantMapper;

	public MessageResponse process(RestaurantRequirements requirements) {
		this.kieService.releaseRulesSession();
		KieSession kieSession = kieService.getRulesSession();

		kieSession.insert(requirements);
		for (Restaurant rest : restaurantRepository.findAll()) {

			kieSession.insert(rest);

		}

		kieSession.getAgenda().getAgendaGroup("fill-restaurant-requirements").setFocus();
		kieSession.fireAllRules();
		// Restaurant foundRest = new Restaurant();
		// kieSession.setGlobal("foundRestaurant", foundRest);
		// List<Restaurant> topRestaurants = new ArrayList<Restaurant>();
		// kieSession.setGlobal("foundRestaurant", topRestaurants);
		kieSession.getAgenda().getAgendaGroup("restaurant").setFocus();
		kieSession.fireAllRules();
		Restaurant top = (Restaurant) kieSession.getGlobal("foundRestaurant");
		System.out.println(top.getName());
//		System.out.println(topRestaurants.size());
//		for(Restaurant r: topRestaurants)
//			System.out.println(r.getName());
		// Restaurant foundRestaurant = (Restaurant)
		// kieSession.getGlobal("foundRestaurant");
		// System.out.println(foundRestaurant.getName());
//		
//		kieSession.getAgenda().getAgendaGroup("Calculating restaurant score").setFocus();
//		kieSession.fireAllRules();
//		
//		Restaurant topRestaurant = (Restaurant) kieSession.getGlobal("topRestaurant");
//		System.out.println(topRestaurant.getName());

		kieSession.dispose();
		return new MessageResponse("Successfully");

	}

	public ArrayList<RestaurantDTO> bestGradeRestaurant() {
		this.kieService.releaseRulesSession();
		KieSession session = this.kieService.getRulesSession();

		List<Restaurant> best = new ArrayList<Restaurant>();

		session.setGlobal("bestGraded", best);

		List<Restaurant> restaurants = this.restaurantRepository.findAll();

		for (Restaurant r : restaurants) {
			session.insert(r);
		}

		session.getAgenda().getAgendaGroup("best-graded-report").setFocus();
		session.fireAllRules();

		ArrayList<RestaurantDTO> restorantsDTO = new ArrayList<RestaurantDTO>();
		for (Restaurant r : best) {
			restorantsDTO.add(this.restaurantMapper.toDto(r));
		}
		return restorantsDTO;

	}

	public PageImplementation<RestaurantDTO> findAll(Pageable pageable) {

		Page<Restaurant> restaurantsPage = this.restaurantRepository.findAll(pageable);
		List<RestaurantDTO> dtoList = this.restaurantMapper.toDtoList(restaurantsPage.toList());
		Page<RestaurantDTO> restaurantDtoPage = new PageImpl<RestaurantDTO>(dtoList, restaurantsPage.getPageable(),
				restaurantsPage.getTotalElements());
		PageImplMapper<RestaurantDTO> pageMapper = new PageImplMapper<RestaurantDTO>();
		PageImplementation<RestaurantDTO> pageImpl = pageMapper.toPageImpl(restaurantDtoPage);

		return pageImpl;
	}

	public boolean createRuleForSearchRestaurants(SearchRestaurantsByNameTemplateModel dto) {
		try {
			InputStream template = new FileInputStream(
					"..\\drools-spring-kjar\\src\\main\\resources\\sbnz\\integracija\\templates\\search_restaurant_by_name.drt");

			// Compile template to generate new rules
			List<SearchRestaurantsByNameTemplateModel> arguments = new ArrayList<>();
			arguments.add(dto);
			ObjectDataCompiler compiler = new ObjectDataCompiler();
			String drl = compiler.compile(arguments, template);
			// Save rule to drl file
			FileOutputStream drlFile = new FileOutputStream(
					new File("..\\drools-spring-kjar\\src\\main\\resources\\sbnz\\integracija\\search_restaurant_by_"
							+ dto.getSearchData() + ".drl"));
			drlFile.write(drl.getBytes());
			drlFile.close();

			// Update Rules project
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File("../drools-spring-kjar/pom.xml"));
			request.setGoals(Arrays.asList("clean", "install"));

			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File(System.getenv("M2_HOME")));
			invoker.execute(request);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public PageImplementation<RestaurantDTO> filter(Pageable pageable, ArrayList<Cuisine> cuisines,
			ArrayList<Prices> prices) {
		List<Restaurant> result = new ArrayList<Restaurant>();

		if (cuisines.size() == 0 && prices.size() > 0) {
			InputStream template;
			try {
				template = RestaurantService.class
						.getResourceAsStream("/sbnz/templates/filter_restaurants_by_prices.drt");
				FilterByPricesTemplateModel dto = new FilterByPricesTemplateModel();
			
				dto.setPrices(prices);
				List<FilterByPricesTemplateModel> arguments = new ArrayList<FilterByPricesTemplateModel>();
				arguments.add(dto);
				ObjectDataCompiler compiler = new ObjectDataCompiler();
				String drl = compiler.compile(arguments, template);
				System.out.println("\n\n" + drl + "\n\n");
				KieSession kieSession = createKieSessionFromDRL(drl);
				List<Restaurant> restaurants = this.restaurantRepository.findAll();
				for (Restaurant r : restaurants) {
					kieSession.insert(r);
				}
				kieSession.setGlobal("restaurants", result);
				kieSession.fireAllRules();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cuisines.size() > 0 && prices.size() == 0) {
			InputStream template;
			try {
				template = RestaurantService.class
						.getResourceAsStream("/sbnz/templates/filter_restaurants_by_cuisine.drt");
				FilterByCuisinesTemplateModel dto = new FilterByCuisinesTemplateModel();
				dto.setCuisineTypes(cuisines);
				List<FilterByCuisinesTemplateModel> arguments = new ArrayList<FilterByCuisinesTemplateModel>();
				arguments.add(dto);
				ObjectDataCompiler compiler = new ObjectDataCompiler();
				String drl = compiler.compile(arguments, template);
				System.out.println("\n\n" + drl + "\n\n");
				KieSession kieSession = createKieSessionFromDRL(drl);
				List<Restaurant> restaurants = this.restaurantRepository.findAll();
				for (Restaurant r : restaurants) {
					kieSession.insert(r);
				}
				kieSession.setGlobal("restaurants", result);
				kieSession.fireAllRules();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			InputStream template;
			try {
				template = RestaurantService.class
						.getResourceAsStream("/sbnz/templates/filter_restaurants_by_cuisine_and_prices.drt");
				FilterByCuisinePricesTemplateModel dto = new FilterByCuisinePricesTemplateModel();
				dto.setCuisines(cuisines);
				dto.setPrices(prices);
				List<FilterByCuisinePricesTemplateModel> arguments = new ArrayList<FilterByCuisinePricesTemplateModel>();
				arguments.add(dto);
				ObjectDataCompiler compiler = new ObjectDataCompiler();
				String drl = compiler.compile(arguments, template);
				System.out.println("\n\n" + drl + "\n\n");
				KieSession kieSession = createKieSessionFromDRL(drl);
				List<Restaurant> restaurants = this.restaurantRepository.findAll();
				for (Restaurant r : restaurants) {
					kieSession.insert(r);
				}
				kieSession.setGlobal("restaurants", result);
				kieSession.fireAllRules();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<RestaurantDTO> dtoList = this.restaurantMapper.toDtoList(result);
		Page<RestaurantDTO> restaurantDtoPage = new PageImpl<RestaurantDTO>(dtoList, pageable, result.size());
		PageImplMapper<RestaurantDTO> pageMapper = new PageImplMapper<RestaurantDTO>();
		PageImplementation<RestaurantDTO> pageImpl = pageMapper.toPageImpl(restaurantDtoPage);
		return pageImpl;
	}

	public PageImplementation<RestaurantDTO> filter(Pageable pageable, String restName) throws FileNotFoundException {

		List<Restaurant> result = new ArrayList<Restaurant>();

		if (!restName.equalsIgnoreCase("")) {
			InputStream template;
			try {
				template = RestaurantService.class.getResourceAsStream("/sbnz/templates/search_restaurant_by_name.drt");

				SearchRestaurantsByNameTemplateModel dto = new SearchRestaurantsByNameTemplateModel();
				dto.setSearchData(restName);
				List<SearchRestaurantsByNameTemplateModel> arguments = new ArrayList<>();
				arguments.add(dto);
				ObjectDataCompiler compiler = new ObjectDataCompiler();
				String drl = compiler.compile(arguments, template);

				System.out.println("\n\n" + drl + "\n\n");
				KieSession kieSession = createKieSessionFromDRL(drl);
				List<Restaurant> restaurants = this.restaurantRepository.findAll();
				for (Restaurant r : restaurants) {
					kieSession.insert(r);
				}
				kieSession.setGlobal("restaurants", result);
				kieSession.fireAllRules();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		List<RestaurantDTO> dtoList = this.restaurantMapper.toDtoList(result);
		Page<RestaurantDTO> restaurantDtoPage = new PageImpl<RestaurantDTO>(dtoList, pageable, result.size());
		PageImplMapper<RestaurantDTO> pageMapper = new PageImplMapper<RestaurantDTO>();
		PageImplementation<RestaurantDTO> pageImpl = pageMapper.toPageImpl(restaurantDtoPage);
		return pageImpl;
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
}
