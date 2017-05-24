package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class PublishMenuController implements Controller{

	private final ListMenuService listMenuService = new ListMenuService();
	private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
	
	public void publishMenu(Menu menu)
			throws DataConcurrencyException, DataIntegrityViolationException{
		
		menu.publishMenu();
		this.menuRepository.save(menu);
	}
	
	public Iterable<Menu> editableMenus(){
        return this.listMenuService.editableMenus();
    }
	
}
