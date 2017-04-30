package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.framework.actions.Action;

public class RegisterMenuAction implements Action{

	@Override
	public boolean execute() {
		return new RegisterMenuUI().show();
	}

}
