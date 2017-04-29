/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Marcos
 */
public class RemoveMealController implements Controller{
    
    private final ListMenuService lstMenuSvc = new ListMenuService();
    
    private final MealRepository  mealRepo = PersistenceContext.repositories().meals();
    
    public void removeMeal(Meal meal) throws DataIntegrityViolationException{
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        this.mealRepo.delete(meal);
    }
    
    public Iterable<Menu> listEditableMenus(){
        return this.lstMenuSvc.editableMenus();
    }
    
    public Iterable<Meal> mealsByMenu(Menu menu){
        return this.mealRepo.mealsByMenu(menu);
    }
    
}
