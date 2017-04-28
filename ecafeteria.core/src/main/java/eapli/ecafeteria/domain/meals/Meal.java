/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author zero_
 */
@Entity
public class Meal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @Id
    @ManyToOne
    private Dish dish;

    @Id
    @ManyToOne
    private MealType mealType;

    @Id
    @ManyToOne
    private Menu menu;

    @Id
    @Temporal(TemporalType.DATE)
    private Calendar date;
    private String description;

    protected Meal() {
        // ORM
    }

    public Meal(Calendar date, Dish dish, MealType mealType, Menu menu, String description) {
        this(date, dish, mealType, menu);

        if (description == null) {
            throw new IllegalArgumentException();
        }

        this.description = description;
    }

    public Meal(Calendar date, Dish dish, MealType mealType, Menu menu) {
        if (menu == null || mealType == null || dish == null || date == null) {
            throw new IllegalArgumentException();
        }

        this.dish = dish;
        this.mealType = mealType;
        this.menu = menu;
        this.date = date;
        this.description = dish.name().toString();
    }

    public String description() {
        return this.description;
    }
    
    public Menu menu(){
        return this.menu;
    }
    
    public Dish dish(){
        return this.dish;
    }
    
    public Calendar date(){
        return this.date;
    }
    
    public MealType mealType(){
        return this.mealType;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DishType)) {
            return false;
        }

        final Meal other = (Meal) o;
        if (!other.menu.equals(this.menu)) {
            return false;
        }

        if (!other.menu.equals(this.menu)) {
            return false;
        }

        if (!other.dish.equals(this.dish)) {
            return false;
        }

        if (!other.mealType.equals(this.mealType)) {
            return false;
        }

        return other.date.equals(this.date);
    }

}
