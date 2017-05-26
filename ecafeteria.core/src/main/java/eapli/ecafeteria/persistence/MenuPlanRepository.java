/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.menus.CompositeIdMenuPlan;
import eapli.ecafeteria.domain.menus.MenuPlan;
import eapli.framework.persistence.repositories.DataRepository;


public interface MenuPlanRepository extends DataRepository <MenuPlan, CompositeIdMenuPlan>{
    
     //Iterable<MenuPlan> menuPlanByMenu(String idMenuPlan);

}
