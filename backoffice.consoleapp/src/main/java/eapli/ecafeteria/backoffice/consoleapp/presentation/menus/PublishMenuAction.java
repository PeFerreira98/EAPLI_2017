package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.framework.actions.Action;

public class PublishMenuAction implements Action {

	@Override
	public boolean execute() {
		return new PublishMenuUI().show();
	}

}
