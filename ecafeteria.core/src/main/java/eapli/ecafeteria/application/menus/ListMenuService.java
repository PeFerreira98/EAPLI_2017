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
import java.util.Calendar;

/**
 *
 * @author zero_
 */
public class ListMenuService {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    public Iterable<Menu> allMenus() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.menuRepository.findAll();
    }

    public Iterable<Menu> publishedMenus() {        
        return this.menuRepository.publishedMenus();
    }
    
    public Iterable<Menu> editableMenus(){
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        
        return this.menuRepository.editableMenus();
    }
    
    // TODO
    public Iterable<Menu> findMenuByDate(Calendar date) {        
        return this.menuRepository.findByDate(date);
    }
}
