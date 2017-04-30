package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

public class RegisterMealTypeAction implements Action {

	@Override
	public boolean execute() {
		return new RegisterMealTypeUI().show();
	}

}
