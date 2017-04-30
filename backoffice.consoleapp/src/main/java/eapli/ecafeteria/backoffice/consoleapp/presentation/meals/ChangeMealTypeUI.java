package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.application.meals.ChangeMealTypeController;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;

public class ChangeMealTypeUI extends AbstractUI {
	
	private final ChangeMealTypeController theController = new ChangeMealTypeController();

	protected Controller controller() {
        return this.theController;
    }
	
	@Override
	protected boolean doShow() {
		final Iterable<MealType> mealTypes = this.theController.listMealTypes();
		final SelectWidget<MealType> selector = new SelectWidget<>(mealTypes, new MealTypePrinter());
		selector.show();
		final MealType theMealType = selector.selectedElement();		
		
		if (theMealType != null) {
            final String newDescription = Console
                    .readLine("Enter new description for " + theMealType.description() + ": ");
            try {
                this.theController.changeMealType(theMealType, newDescription);
            } catch (DataConcurrencyException ex) {
                System.out.println("That entity has already been changed or deleted since you last read it");
                Logger.getLogger(ChangeMealTypeUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataIntegrityViolationException ex) {
                System.out.println("That entity ID is already in use");
                Logger.getLogger(ChangeMealTypeUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		
		return false;
	}

	@Override
	public String headline() {
		return "Change MealType";
	}

}
