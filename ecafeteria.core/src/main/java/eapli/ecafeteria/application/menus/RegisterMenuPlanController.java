/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.MenuPlan;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Fernando
 */
public class RegisterMenuPlanController implements Controller{
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final MenuPlanRepository menuPlanRepository = PersistenceContext.repositories().menuPlans();
    
    private ListMealService lms = new ListMealService();

    
    public Iterable<Menu> getPublishedMenus(){
        return menuRepository.publishedMenus();
    }
    
     public Iterable<Meal> getMealByMenu(Menu menu){
        return lms.mealsByMenu(menu);
    } 

    public MenuPlan CreateMenuPlan(Menu selectMenu, Meal selectMeal, int quantidade) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        
        final MenuPlan newMenuPlan = new MenuPlan(selectMenu, selectMeal, quantidade);
        
        MenuPlan result = this.menuPlanRepository.save(newMenuPlan);
        
        return result;
    }
    
//     public Iterable<MenuPlan> menuPlanByMenu( String idMenuPlan){
//        return menuPlanRepository.menuPlanByMenu(idMenuPlan);
//    }

    
    
    
}
