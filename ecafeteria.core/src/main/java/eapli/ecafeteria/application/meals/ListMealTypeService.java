/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Marcos
 */
public class ListMealTypeService {
    
    private final MealTypeRepository mealTypeRepo = PersistenceContext.repositories().mealTypes();

    public Iterable<MealType> allMealTypes() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.mealTypeRepo.findAll();
    }

    public Iterable<MealType> activeMealTypes() {
        return this.mealTypeRepo.activeMealTypes();
    }
    
}
