/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menus;

import eapli.ecafeteria.domain.meals.CompositeIdMeal;
import eapli.ecafeteria.domain.meals.Meal;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

/**
 *
 * @author Fernando
 */
@Entity
public class MenuPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Version
    private Long version;
    
    @EmbeddedId
    private CompositeIdMenuPlan id;
    private int quantity;
    
    protected MenuPlan(){
    }
    
    public MenuPlan( Menu menu, Meal meal, int quantity ){
        if (menu == null || meal == null) {
            throw new IllegalArgumentException();
        }
        this.quantity = quantity;
        this.id = new CompositeIdMenuPlan( meal.id(), menu.id());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuPlan)) {
            return false;
        }
        MenuPlan other = (MenuPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.menus.MealPlan[ id=" + id + " ]";
    }
    
}
