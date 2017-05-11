/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cashregister;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.meals.MealType;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Alexandra Ferreira 1140388
 */
@Entity
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Calendar date;

    private MealType mealType;

    private CashRegister cashRegister;

    private SystemUser user;

    protected Shift() {

    }

    /**
     * Constructor with the meal type and cash register
     *
     * @param mealType working meal type of the shift
     * @param cashRegister
     */
    public Shift(MealType mealType, CashRegister cashRegister) {
        if (mealType == null || cashRegister == null) {
            throw new IllegalArgumentException("New Shift missing arguments.");
        }
        this.mealType = mealType;
        this.cashRegister = cashRegister;
        this.user = null;
    }

    /**
     * Start date of the shift
     *
     * @return start date
     */
    public Calendar date() {
        return this.date;
    }

    /**
     * Meal Type of the shift
     *
     * @return meal type
     */
    public MealType mealType() {
        return this.mealType;
    }

    /**
     * User working on this shift
     *
     * @return user
     */
    public SystemUser user() {
        return this.user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.cashregister.Shift[ id=" + id + " ]";
    }

}
