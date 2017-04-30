package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

public class RegisterMealAction implements Action{

	@Override
	public boolean execute() {
		return new RegisterMealUI().show();
	}

}
