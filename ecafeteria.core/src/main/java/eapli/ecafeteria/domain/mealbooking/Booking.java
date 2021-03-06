/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
@Entity
public class Booking implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Meal meal;

    @ManyToOne
    private CafeteriaUser cafeteriaUser;
    
    @Enumerated(EnumType.STRING)
    private BookingState state;

    protected Booking() {
    	// ORM
    }

    public Booking(CafeteriaUser user, Meal meal) {

        if (user == null || meal == null) {
            throw new IllegalArgumentException();
        }

        this.cafeteriaUser = user;
        this.meal = meal;
        this.state = BookingState.BOOKED;
    }

    public CafeteriaUser user() {
        return cafeteriaUser;
    }

    public Meal meal() {
        return meal;
    }
    
    public BookingState state(){
    	return this.state;
    }
    
    public boolean cancelBooking(){
    	if(state.canCancel()){
    		this.state = BookingState.CANCELLED;
    		return true;
    	}
    	return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking)) {
            return false;
        }

        final Booking other = (Booking) o;
        if (!Objects.equals(this.meal, other.meal)) {
            return false;
        }
        if (!Objects.equals(this.cafeteriaUser, other.cafeteriaUser)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean is(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
