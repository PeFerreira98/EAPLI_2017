/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterDishAllergenController;
import eapli.framework.actions.Action;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;
/**
 *
 * @author JoãoPedro
 */
public class DishAllergenBootstraper implements Action {

    @Override
    public boolean execute() {
        
        return false;
    }

    /**
     *
     */
    private void register(Dish dish, Allergen allergen) {
//        final RegisterDishAllergenController controller = new RegisterDishAllergenController();
//        try {
//            controller.registerDishAllergen(dish, allergen);
//        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
//            // ignoring exception. assuming it is just a primary key violation
//            // due to the tentative of inserting a duplicated user
//            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
//                    .info("EAPLI-DI001: bootstrapping existing record");
//        }
    }
}
