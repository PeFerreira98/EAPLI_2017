package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.application.Controller;

public class ListMenuController implements Controller {

	private ListMenuService svc = new ListMenuService();
	
	public Iterable<Menu> allMenus(){
		return this.svc.allMenus();
	}
}
