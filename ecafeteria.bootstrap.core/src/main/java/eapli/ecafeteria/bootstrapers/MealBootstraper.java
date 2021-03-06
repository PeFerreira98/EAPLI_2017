package eapli.ecafeteria.bootstrapers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class MealBootstraper implements Action {

    @Override
    public boolean execute() {
    	
    	// WARNING: Java Multithreading issues (use registerSimple to avoid calendar setting issues)
    	
        final MenuRepository menuRepository = PersistenceContext.repositories().menus();
        final MealTypeRepository mealTypeRepository = PersistenceContext.repositories().mealTypes();
        final DishRepository dishRepository = PersistenceContext.repositories().dishes();
        
        Menu menu = menuRepository.findByName("MenuSemanaAbril");
        Menu menuMaio = menuRepository.findByName("MenuSemanaMaio");
        MealType mealTypeDinner = mealTypeRepository.findByAcronym("dinner");
        MealType mealTypeLunch = mealTypeRepository.findByAcronym("lunch");
        Dish dish = dishRepository.iterator().next();

        registerSimple(menu, dish, mealTypeLunch, "meal1", 2017, Calendar.APRIL, 10);
        
        registerSimple(menu, dish, mealTypeLunch, "meal2", 2017, Calendar.APRIL, 11);
        
        registerSimple(menu, dish, mealTypeLunch, "meal3", 2017, Calendar.APRIL, 13);
        
        registerSimple(menuMaio, dish, mealTypeLunch, "meal4", 2017, Calendar.MAY, 27);
        
        registerSimple(menuMaio, dish, mealTypeDinner, "meal5", 2017, Calendar.MAY, 27);
		        
        return false;
    }
    
    private void registerSimple(Menu menu, Dish dish, MealType mealType, String description, int year, int month, int day){
        	Calendar calendar = GregorianCalendar.getInstance();
        	calendar.set(year, month, day);
        	
    		register(menu, dish, mealType, calendar, description);
    }

    private void register(Menu menu, Dish dish, MealType mealType, Calendar date, String description) {
        final RegisterMealController controller = new RegisterMealController();
        try {
            controller.registerMeal(dish, mealType, menu, date, description);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
