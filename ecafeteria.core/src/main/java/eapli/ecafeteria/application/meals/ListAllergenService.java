/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author JoãoPedro
 */
public class ListAllergenService {
    private AllergenRepository dishAllergenRepository = PersistenceContext.repositories().allergens();

    public Iterable<Allergen> allDishAllergens() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.dishAllergenRepository.findAll();
    }
}
