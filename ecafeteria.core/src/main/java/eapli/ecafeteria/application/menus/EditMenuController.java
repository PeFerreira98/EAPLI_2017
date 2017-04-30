/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author Marcos
 */
public class EditMenuController implements Controller{
    
    private final ListMenuService lstMenuSvc = new ListMenuService();
    
    private final MenuRepository menuRepo = PersistenceContext.repositories().menus();
    
    public void updateMenu(Menu menu, String newName, Calendar newBeginningDate, Calendar newEndDate) throws DataConcurrencyException, DataIntegrityViolationException{
        menu.update(newName, newBeginningDate, newEndDate);
        this.menuRepo.save(menu);
    }
    
    public Iterable<Menu> editableMenus(){
        return this.lstMenuSvc.editableMenus();
    }
}
