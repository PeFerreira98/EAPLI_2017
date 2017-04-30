/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.application.menus.ListMenuService;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author Marcos
 */
public class RegisterMealController implements Controller{
    
    private final ListMenuService menuSvc = new ListMenuService();
    private final ListDishService dishSvc = new ListDishService();
    private final ListMealTypeService mealTypeSvc = new ListMealTypeService();
    
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    
    public Meal registerMeal(final Dish dish, final MealType mealType, final Menu menu, final Calendar date,
            final String desc) throws DataIntegrityViolationException, DataConcurrencyException {

        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        
        final Meal newMeal = new Meal(date, dish, mealType, menu, desc);

        Meal ret = this.mealRepository.save(newMeal);
        
        return ret;
    }

    public Iterable<Menu> listMenus() {
        return this.menuSvc.publishedMenus();
    }
    
    public Iterable<Dish> listDish() {
        return this.dishSvc.allDishes();
    }
    
    public Iterable<MealType> listMealTypes(){
        return this.mealTypeSvc.activeMealTypes();
    }
    
}
