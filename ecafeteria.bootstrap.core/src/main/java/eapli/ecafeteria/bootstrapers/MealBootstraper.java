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

        final MenuRepository menuRepository = PersistenceContext.repositories().menus();
        final MealTypeRepository mealTypeRepository = PersistenceContext.repositories().mealTypes();
        final DishRepository dishRepository = PersistenceContext.repositories().dishes();
        Menu menu = menuRepository.findByName("MenuSemanaAbril");
        MealType mealTypeLunch = mealTypeRepository.findByAcronym("lunch");
        Dish dish = dishRepository.first();
        Calendar calendar = GregorianCalendar.getInstance();

		calendar.set(2017, Calendar.APRIL, 10);
		register(menu, dish, mealTypeLunch, calendar, "meal1");
		
		calendar.set(2017, Calendar.APRIL, 11);
		register(menu, dish, mealTypeLunch, calendar, "meal2");
		
		calendar.set(2017, Calendar.APRIL, 13);
		register(menu, dish, mealTypeLunch, calendar, "meal3");
        try {
            Menu menuMaio = menuRepository.findByName("MenuSemanaMaio");
            MealType mealTypeDinner = mealTypeRepository.findByAcronym("dinner");
            dish = dishRepository.first();

            calendar.set(2017, Calendar.MAY, 27);
            register(menuMaio, dish, mealTypeLunch, calendar, "meal4");

            calendar.set(2017, Calendar.MAY, 27);
            register(menuMaio, dish, mealTypeDinner, calendar, "meal5");
        }catch(Throwable e){
            System.out.println(e.getMessage());
        }
        return false;
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
