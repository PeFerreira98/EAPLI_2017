package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.RegisterMealTypeController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;

public class RegisterMealTypeUI extends AbstractUI{

	private final RegisterMealTypeController theController = new RegisterMealTypeController();
	
	protected Controller controller() {
        return this.theController;
    }
	
	@Override
	protected boolean doShow() {
		
		final String acronym = Console.readLine("Acronym");
		
		final String description = Console.readLine("Description");

		try {			
			this.theController.registerMealType(acronym, description);
			
		} catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a meal which already exists in the database.");
        }
		
		return false;
	}

	@Override
	public String headline() {
		return "Register MealType";
	}

}
