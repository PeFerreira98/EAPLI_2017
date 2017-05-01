/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 11/04/2016
 */
@Embeddable
public class NutricionalInfo implements ValueObject, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // TODO a very interesting situation to use Quantity pattern:
    // http://martinfowler.com/eaaDev/quantity.html
    private Integer calories;
    private Integer salt;

    public NutricionalInfo(Integer calories, Integer salt) {

        if (calories == null || calories < 0) {
            throw new IllegalStateException("Calories can't be negative");
        }
        if (salt == null || salt < 0) {
            throw new IllegalStateException("Salt can't be negative");
        }

        this.calories = calories;
        this.salt = salt;
    }

    protected NutricionalInfo() {
        // for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NutricionalInfo)) {
            return false;
        }

        final NutricionalInfo that = (NutricionalInfo) o;

        if (!this.calories.equals(that.calories)) {
            return false;
        }
        return this.salt.equals(that.salt);
    }

    @Override
    public int hashCode() {
        return this.calories.hashCode();
    }

    @Override
    public String toString() {
        return this.calories + " " + this.salt;
    }

    public Integer calories() {
        return this.calories;
    }

    public Integer salt() {
        return this.salt;
    }
    
    /**
     * Metodo que permite retornar a soma de duas Nutricional Infos, retornando uma nova.
     * @param other a nutricional Info a adicionar a esta.
     * @return uma nova NutricionalInfo que é a soma das duas.
     */
    public NutricionalInfo sumNutricionalInfo(NutricionalInfo other){
        if (other == null) return new NutricionalInfo(this.calories , this.salt);
        return new NutricionalInfo( this.calories.intValue() + other.calories.intValue() , this.salt.intValue() + other.salt.intValue() );
    }
    
    
    
}
