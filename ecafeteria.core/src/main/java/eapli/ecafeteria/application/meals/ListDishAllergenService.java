/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishAllergen;
import eapli.ecafeteria.persistence.DishAllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author JoãoPedro
 */
public class ListDishAllergenService {
    private DishAllergenRepository dishAllergenRepository = PersistenceContext.repositories().dishAllergens();

    public Iterable<DishAllergen> allDishAllergens() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishAllergenRepository.findAll();
    }
    
    public Iterable<DishAllergen> activeDishTypes() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishAllergenRepository.activeDishAllergens();
    }
    
    public Iterable<DishAllergen> findByDish(Dish dish) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishAllergenRepository.findByDish(dish);
    }
    
    public Iterable<DishAllergen> findByAllergen(Allergen allergen) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishAllergenRepository.findByAllergen(allergen);
    }
}
