/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.menus.ListMenuService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishAllergen;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.DishAllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author JoãoPedro
 */
public class RegisterDishAllergenController implements Controller {

    private final DishAllergenRepository repository = PersistenceContext.repositories().dishAllergens();
    
    private final ListDishService dishSvc = new ListDishService();
    private final ListMenuService menuSvc = new ListMenuService();
    private final ListAllergenService allergenSvc = new ListAllergenService();
    
    public DishAllergen registerDishAllergen(Dish dish, Allergen allergen)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final DishAllergen newAllergen = new DishAllergen(dish, allergen);
        return this.repository.save(newAllergen);
    }
    
    public Iterable<Menu> listMenus() {
        return this.menuSvc.editableMenus();
    }
    
    public Iterable<Dish> listDish() {
        return this.dishSvc.allDishes();
    }
    public Iterable<Allergen> listAllergen() {
        return this.allergenSvc.allDishAllergens();
    }
}
