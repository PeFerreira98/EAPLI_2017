package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.ecafeteria.application.menus.ListMenuController;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListMenuUI extends AbstractListUI<Menu>{

	private final ListMenuController theController = new ListMenuController();
	
	protected Controller controller(){
		return this.theController;
	}

	@Override
    protected Iterable<Menu> listOfElements() {
        return this.theController.allMenus();
    }

    @Override
    protected Visitor<Menu> elementPrinter() {
        return new MenuPrinter();
    }

    @Override
    protected String elementName() {
        return "Menu";
    }
	
}
