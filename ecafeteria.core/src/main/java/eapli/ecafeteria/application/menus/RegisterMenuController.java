/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author zero_
 */
public class RegisterMenuController implements Controller{
    
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    public Menu createMenu(final String name, final Calendar beginningDate, 
            final Calendar endingDate) throws DataIntegrityViolationException, DataConcurrencyException{
        
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        
        final Menu newMenu = new Menu(name, beginningDate, endingDate);
        Menu result = this.menuRepository.save(newMenu);
        
        return result;
    }
}
