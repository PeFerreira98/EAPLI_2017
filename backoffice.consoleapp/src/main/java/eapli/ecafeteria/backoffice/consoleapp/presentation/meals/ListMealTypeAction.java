package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

public class ListMealTypeAction implements Action{

	@Override
	public boolean execute() {
		return new ListMealTypeUI().show();
	}

}
