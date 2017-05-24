package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;

public class ListMenuController implements Controller {

	private ListMenuService menuService = new ListMenuService();
	private ListMealService mealService = new ListMealService();
	
	public Iterable<Menu> allMenus(){
		return this.menuService.allMenus();
	}
	
	public Iterable<Menu> publishedMenus(){
		return this.menuService.publishedMenus();
	}
	
	public Iterable<Meal> mealsBymenu(Menu menu){
		return this.mealService.mealsByMenu(menu);
	}
}
