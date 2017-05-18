/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
public class ListMealService {
    
    private final MealRepository mealRepo = PersistenceContext.repositories().meals();
    
    public Iterable<Meal> allMeals() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.mealRepo.findAll();
    }
    
    public Iterable<Meal> mealsByDateAndMealType(Calendar date, MealType mealType) {
		return this.mealRepo.mealsByDateAndMealType(date, mealType);
	}
    
}
