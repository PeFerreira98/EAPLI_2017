package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

public class ChangeMealTypeAction implements Action {

	@Override
	public boolean execute() {
		return new ChangeMealTypeUI().show();
	}

}
