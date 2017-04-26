/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author zero_
 */
public class ListMenuService {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    public Iterable<Menu> allMenus() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        // TO DO: Check permission (100% sure)

        return this.menuRepository.findAll();
    }

    public Iterable<Menu> publishedMenus() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        // TO DO: Check permission (100% sure)
        
        return this.menuRepository.publishedMenus();
    }
    
    public Iterable<Menu> editableMenus(){
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        // TO DO: Check permission (100% sure)
        
        return this.menuRepository.editableMenus();
    }
}
