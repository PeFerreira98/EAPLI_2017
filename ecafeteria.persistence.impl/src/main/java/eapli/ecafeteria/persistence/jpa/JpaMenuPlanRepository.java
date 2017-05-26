/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.menus.CompositeIdMenuPlan;
import eapli.ecafeteria.domain.menus.MenuPlan;
import eapli.ecafeteria.persistence.MenuPlanRepository;

/**
 *
 * @author Fernando
 */
public class JpaMenuPlanRepository extends CafeteriaJpaRepositoryBase<MenuPlan, CompositeIdMenuPlan> implements MenuPlanRepository{

//    @Override
//    public Iterable<MenuPlan> menuPlanByMenu(String idMenuPlan) {
//        return match("e.id.menuId='" + idMenuPlan + "'");   
//    }
    
}
