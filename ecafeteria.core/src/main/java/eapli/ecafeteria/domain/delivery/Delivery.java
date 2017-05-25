/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.delivery;

import eapli.ecafeteria.domain.meals.Meal;
import java.util.Objects;

/**
 *
 * @author 1140376@isep.ipp.pt
 */
public class Delivery {
    
    private BookingEvent bookingState;
    
    private Meal meal;
    //Construtor vazio
    protected Delivery(){
    
    }
    
    //Construtor de entrega
    public Delivery(Meal m){
    this.meal = m;
    this.bookingState = BookingEvent.DELIVERY;
    }
    // Retorna o estado da entrega
    public BookingEvent bookingEventState(){
    return this.bookingState;
    }
    //Retorna a refeicao associado com a entrega
    public Meal mealDelivery(){
    return this.meal;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (meal.id() != null ? meal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Delivery other = (Delivery) obj;
        if (this.bookingState != other.bookingState) {
            return false;
        }
        return Objects.equals(this.meal, other.meal);
    }
    
    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.cafeteria.Delivery[ Meal=" + meal.id()+ " State"+ bookingState+" ]";
    }
    
}
