package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;

public class ListMenuController implements Controller {

	private ListMenuService svc = new ListMenuService();
	
	public Iterable<Menu> allMenus(){
		return this.svc.allMenus();
	}
}
