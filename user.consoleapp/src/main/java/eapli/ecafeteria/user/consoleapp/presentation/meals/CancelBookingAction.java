package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

public class CancelBookingAction implements Action{

	@Override
	public boolean execute() {
		return new CancelBookingUI().show();
	}

}
