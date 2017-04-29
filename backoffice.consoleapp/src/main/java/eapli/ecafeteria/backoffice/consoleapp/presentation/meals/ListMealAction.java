package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

public class ListMealAction implements Action{

	@Override
	public boolean execute() {
		return new ListMealUI().show();
	}

}
