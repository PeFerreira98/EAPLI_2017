/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import java.util.Calendar;

/**
 *
 * @author zero_
 */
public class JpaMenuRepository extends CafeteriaJpaRepositoryBase<Menu, String> implements MenuRepository {

    @Override
    public Iterable<Menu> publishedMenus() {
        return match("e.isPublished=true");
    }

    @Override
    public Iterable<Menu> editableMenus() {
        return match("e.isPublished=false");
    }

    @Override
    public Menu findByName(String name) {
        return matchOne("e.name:=name", "name", name);
    }

    // TODO test and validate query 
    @Override
    public Iterable<Menu> findByDate(Calendar date) {
        return match("e.beginDate<'" + date + "' and e.endDate>'" + date + "'");
    }

}
