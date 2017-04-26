/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Calendar;

/**
 *
 * @author zero_
 */
public class InMemoryMenuRepository extends InMemoryRepository<Menu, String> implements MenuRepository {

    @Override
    protected String newPK(Menu entity) {
        return entity.id();
    }

    @Override
    public Iterable<Menu> publishedMenus() {
        return match(e -> e.isPublished());
    }

    @Override
    public Iterable<Menu> editableMenus() {
        return match(e -> !e.isPublished());
    }

    @Override
    public Menu findByName(String name) {
        return matchOne(e -> e.name().equals(name));
    }

    @Override
    public Iterable<Menu> findByDate(Calendar date) {
        //TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
