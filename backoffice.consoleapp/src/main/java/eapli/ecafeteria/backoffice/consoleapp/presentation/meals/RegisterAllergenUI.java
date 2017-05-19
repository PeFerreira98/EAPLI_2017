/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.RegisterAllergenController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;

/**
 *
 * @author JoãoPedro
 */
public class RegisterAllergenUI extends AbstractUI {

    private final RegisterAllergenController theController = new RegisterAllergenController();

    protected Controller controller() {
	return this.theController;
    }

    @Override
    protected boolean doShow() {
	
	final String name = Console.readLine("Name: ");

	final String desc = Console.readLine("Description: ");

	try {
	    this.theController.registerAllergen(name,desc);
	} catch (final DataIntegrityViolationException | DataConcurrencyException e) {
	    System.out.println("You tried to enter a Allergen which already exists in the database.");
	}

	return false;
    }

    @Override
    public String headline() {
	return "Register Allergen";
    }
}

