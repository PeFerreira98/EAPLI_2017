package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import java.util.Calendar;

import eapli.ecafeteria.application.menus.RegisterMenuController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;

public class RegisterMenuUI extends AbstractUI{
	
private final RegisterMenuController theController = new RegisterMenuController();
	
	protected Controller controller() {
        return this.theController;
    }

	@Override
	protected boolean doShow() {
		
		final String name = Console.readLine("Menu name");
		
		final Calendar beginningDate = Console.readCalendar("Begin Date (dd-mm-aaaa)");
		
		final Calendar endingDate = Console.readCalendar("End Date (dd-mm-aaaa)");
		
		try {			
			this.theController.createMenu(name, beginningDate, endingDate);
			
		} catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a menu which already exists in the database.");
        }
		
		return false;
	}

	@Override
	public String headline() {
		return "Register Menu";
	}

}
