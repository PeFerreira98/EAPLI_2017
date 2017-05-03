/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.io.Serializable;

/**
 *
 * @author JoãoPedro
 */
public class DishAllergen implements Serializable {

    Dish dish;
    Allergen allergen;
    boolean active = true;
    String description = "";

    public DishAllergen(Dish dish, Allergen allergen) {
        this.dish = dish;
        this.allergen = allergen;
    }

    public String description() {
        return this.description;
    }

    public boolean isActive() {
        return this.active;
    }

    //@Override
    public boolean is(Dish dish, Allergen allergen) {
        return (dish.equals(this.dish) && allergen.equals(this.allergen));
    }

    //@Override
    public String idDish() {
        return this.dish.id().toString();
    }

    //@Override
    public String idAllergen() {
        return this.allergen.id();
    }

    //@Override
    public boolean sameAs(Object other) {
        final DishAllergen dishAllergen = (DishAllergen) other;
        return this.equals(dishAllergen) && description().equals(dishAllergen.description()) && isActive() == dishAllergen.isActive();
    }

    @Override
    public int hashCode() {
        return this.dish.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DishAllergen)) {
            return false;
        }

        final DishAllergen other = (DishAllergen) o;
        return (idDish().equals(other.idDish()) && idAllergen().equals(other.idAllergen()));
    }
}
