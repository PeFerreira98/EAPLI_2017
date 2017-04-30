package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.framework.actions.Action;

public class ListMenuAction implements Action{

	@Override
	public boolean execute() {
		return new ListMenuUI().show();
	}

}
