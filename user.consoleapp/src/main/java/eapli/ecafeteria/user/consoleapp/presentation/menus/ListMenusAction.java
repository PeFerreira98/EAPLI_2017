package eapli.ecafeteria.user.consoleapp.presentation.menus;

import eapli.framework.actions.Action;

public class ListMenusAction implements Action{

	@Override
	public boolean execute() {
		return new ListMenusUI().show();
	}

}
