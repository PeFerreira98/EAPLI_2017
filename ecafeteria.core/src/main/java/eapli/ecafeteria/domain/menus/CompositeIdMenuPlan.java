/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menus;

import eapli.ecafeteria.domain.meals.CompositeIdMeal;
import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Fernando
 */
@Embeddable
public class CompositeIdMenuPlan implements ValueObject, Serializable{
    private static final long serialVersionUID =1L;
    
    private CompositeIdMeal mealId;
    private String menuId;
    
    protected CompositeIdMenuPlan(){
    }

    public CompositeIdMenuPlan(CompositeIdMeal mealId, String menuId) {
        if (mealId == null || menuId == null) {
            throw new IllegalArgumentException("Null parameter inserted");
        }
        this.mealId = mealId;
        this.menuId = menuId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.mealId);
        hash = 53 * hash + Objects.hashCode(this.menuId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CompositeIdMenuPlan other = (CompositeIdMenuPlan) obj;
        if (!Objects.equals(this.menuId, other.menuId)) {
            return false;
        }
        if (!Objects.equals(this.mealId, other.mealId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CompositeIdMealPlan{" + "mealId=" + mealId + ", menuId=" + menuId + '}';
    }
}
