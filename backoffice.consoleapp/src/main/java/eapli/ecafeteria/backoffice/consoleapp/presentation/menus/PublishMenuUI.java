package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.application.menus.PublishMenuController;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class PublishMenuUI extends AbstractUI{

	private final PublishMenuController theController = new PublishMenuController();
	
	protected Controller controller() {
        return this.theController;
    }
	
	@Override
	protected boolean doShow() {
		final Iterable<Menu> menus = this.theController.editableMenus();
		
		final SelectWidget<Menu> menuSelector = new SelectWidget<>("Non Published Menus", menus, new MenuPrinter());
		menuSelector.show();
		final Menu menu = menuSelector.selectedElement();
		
		if(menu != null){
			try {
				this.theController.publishMenu(menu);
			} catch (DataConcurrencyException ex) {
				System.out.println("That entity has already been changed or deleted since you last read it");
				Logger.getLogger(PublishMenuUI.class.getName()).log(Level.SEVERE, null, ex);
			} catch (DataIntegrityViolationException ex) {
				System.out.println("That entity ID is already in use");
				Logger.getLogger(PublishMenuUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		return false;
	}

	@Override
	public String headline() {
		return "Publish Menu";
	}

}
