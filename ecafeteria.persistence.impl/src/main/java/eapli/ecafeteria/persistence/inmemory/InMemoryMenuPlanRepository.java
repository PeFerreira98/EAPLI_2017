/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.menus.CompositeIdMenuPlan;
import eapli.ecafeteria.domain.menus.MenuPlan;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Fernando
 */
public class InMemoryMenuPlanRepository extends InMemoryRepository<MenuPlan, CompositeIdMenuPlan> implements MenuPlanRepository {

    @Override
    protected CompositeIdMenuPlan newPK(MenuPlan entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
