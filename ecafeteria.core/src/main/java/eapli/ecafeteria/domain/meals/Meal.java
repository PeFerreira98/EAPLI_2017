/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.domain.ddd.AggregateRoot;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import eapli.util.DateTime;

/**
 *
 * @author zero_
 */
@Entity
public class Meal implements AggregateRoot<CompositeIdMeal>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private CompositeIdMeal id;
    @ManyToOne
    private Dish dish;
    @ManyToOne
    private MealType mealType;
    @ManyToOne
    private Menu menu;
    @Temporal(TemporalType.DATE)
    private Calendar date;
    private String description;

    private MealPlanItem mealPlanItem;

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
        if (!menu.isInBetween(date)) {
            throw new IllegalArgumentException("Meal date does not correspond to menu");
        }

        this.dish = dish;
        this.mealType = mealType;
        this.menu = menu;
        this.date = date;
        this.id = new CompositeIdMeal(menu, dish, mealType, date);
        this.description = "Empty...";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Meal)) {
            return false;
        }

        final Meal other = (Meal) o;
        return this.id.equals(other.id);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Meal)) {
            return false;
        }

        final Meal that = (Meal) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id());
    }

    @Override
    public boolean is(CompositeIdMeal id) {
        return id().equals(id);
    }

    public CompositeIdMeal id() {
        return this.id;
    }

    public String description() {
        return this.description;
    }

    public Menu menu() {
        return this.menu;
    }

    public Dish dish() {
        return this.dish;
    }

    public Calendar date() {
        return this.date;
    }

    public MealType mealType() {
        return this.mealType;
    }

    /**
     * verify if exists available meals only if quantity reserved it´s inferior
     * quantity planned
     *
     * @return boolean
     */
    protected boolean areThereAvailableMeals() {
        return (this.mealPlanItem.quantityReserved() < this.mealPlanItem.quantityPlanned());
    }

    /**
     * verify if current date it´s lower in relation of limit date for
     * reservation
     *
     * @return boolean
     */
    protected boolean isTimeForReserveNotExceed() {
        Calendar now = DateTime.now();

        if (DateTime.isPreviousDate(now, this.date())) {
            return true;
        }

        return DateTime.isSameDate(now, this.date()) && now.
                get(Calendar.HOUR_OF_DAY) <= this.
                mealType().
                limitForReservation();
    }

    private boolean isMealAvailableToReserve() {
        return (areThereAvailableMeals() && isTimeForReserveNotExceed());
    }

    /**
     * verify if a meal is available to reserve and add a reserve if true
     *
     * @return boolean
     */
    public boolean registerReservation() {
        if (isMealAvailableToReserve()) {
            return this.mealPlanItem.addReserve();
        } else {
            return false;
        }
    }
}
