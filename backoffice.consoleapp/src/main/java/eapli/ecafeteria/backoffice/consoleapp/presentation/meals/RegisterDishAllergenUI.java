/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.RegisterDishAllergenController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.menus.MenuPrinter;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;
import java.util.Calendar;

/**
 *
 * @author KAMMIKAZI
 */
public class RegisterDishAllergenUI extends AbstractUI{
    private final RegisterDishAllergenController theController = new RegisterDishAllergenController();
	
	protected Controller controller() {
        return this.theController;
    }

	@Override
	protected boolean doShow() {
		final Iterable<Allergen> allergens = this.theController.listAllergen();
		final Iterable<Dish> dishes = this.theController.listDish();
                		
		final SelectWidget<Dish> dishSelector = new SelectWidget<>("xpto", dishes, new DishPrinter());
                
		dishSelector.show();
                
		final Dish dishSel = dishSelector.selectedElement();
		
		final SelectWidget<Allergen> allergenSelector = new SelectWidget<>("xpto", allergens, new AllergenPrinter());
                
		allergenSelector.show();
                
		final Allergen allergensSel = allergenSelector.selectedElement();
		
		try {			
			this.theController.registerDishAllergen(dishSel, allergensSel);
			
		} catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a Dish Allergen which already exists in the database.");
        }
		
		return false;
	}

	@Override
	public String headline() {
		return "Register DishAllergen";
	}

}
