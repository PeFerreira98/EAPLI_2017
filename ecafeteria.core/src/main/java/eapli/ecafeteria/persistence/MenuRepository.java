/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

/**
 *
 * @author zero_
 */
public interface MenuRepository extends DataRepository<Menu, String> {

    Iterable<Menu> publishedMenus();

    Iterable<Menu> editableMenus();

    Menu findByName(String name);

    Iterable<Menu> findByDate(Calendar date);

}
