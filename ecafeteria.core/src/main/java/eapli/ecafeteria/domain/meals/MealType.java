/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ddd.AggregateRoot;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author zero_
 */
@Entity
public class MealType implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    private String acronym;
    private String description;
    private int reservationHourLimit;
    private boolean active;

    protected MealType() {
        //ORM
    }

    public MealType(String name, String description, int reservationHour) {

        if (Strings.isNullOrEmpty(name) || reservationHour < 0 || reservationHour > 24) {
            throw new IllegalArgumentException();
        }

        setName(name);
        setDescription(description);
        this.reservationHourLimit = reservationHour;
        this.active = true;
    }

    /**
     *
     * @return limit hour for reservation
     */
    public int limitForReservation() {
        return this.reservationHourLimit;
    }

    /**
     * Sets and validates newDescription.
     *
     * @param newDescription
     */
    private void setDescription(String newDescription) {
        if (descriptionMeetsMinimumRequirements(newDescription)) {
            this.description = newDescription;
        } else {
            throw new IllegalArgumentException("Invalid Description");
        }
    }

    /**
     * Sets and validates newName.
     *
     * @param newName
     */
    private void setName(String newName) {
        if (nameMeetsMinimumRequirements(newName)) {
            this.acronym = newName;
        } else {
            throw new IllegalArgumentException("Invalid Name");
        }
    }

    /**
     * Ensure name is not null or empty.
     *
     * @param name
     * @return True if name meets minimum requirements. False if name does not
     * meet minimum requirements.
     */
    private boolean nameMeetsMinimumRequirements(String name) {
        return !Strings.isNullOrEmpty(name);
    }

    /**
     * Ensure description is not null or empty.
     *
     * @param description
     * @return True if description meets minimum requirements. False if
     * description does not meet minimum requirements.
     */
    private boolean descriptionMeetsMinimumRequirements(String description) {
        return !Strings.isNullOrEmpty(description);
    }

    public String description() {
        return this.description;
    }

    public boolean isActive() {
        return this.active;
    }

    /**
     * Toggles the state of the dishtype, activating it or deactivating it
     * accordingly.
     *
     * @return whether the dishtype is active or not
     */
    public boolean toogleState() {

        this.active = !this.active;
        return isActive();
    }

    /**
     * Change DishType description
     *
     * @param newDescription New description.
     */
    public void changeDescriptionTo(String newDescription) {
        if (!descriptionMeetsMinimumRequirements(newDescription)) {
            throw new IllegalArgumentException();
        }
        this.description = newDescription;
    }

    @Override
    public boolean is(String id) {
        return id.equalsIgnoreCase(this.acronym);
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean sameAs(Object other) {
        final DishType dishType = (DishType) other;
        return this.equals(dishType) && description().equals(dishType.description()) && isActive() == dishType.isActive();
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DishType)) {
            return false;
        }

        final MealType other = (MealType) o;
        return id().equals(other.id());
    }

}
