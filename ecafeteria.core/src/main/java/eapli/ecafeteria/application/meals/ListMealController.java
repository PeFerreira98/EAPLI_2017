package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

public class ListMealController implements Controller {

	private MealRepository mealRepository = PersistenceContext.repositories().meals();
	
	public Iterable<Meal> allMeals(){
		return this.mealRepository.findAll();
	}
}
