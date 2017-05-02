/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishAllergen;
import eapli.ecafeteria.persistence.DishAllergenRepository;

/**
 *
 * @author JoãoPedro
 */
public class JpaDishAllergenRepository extends CafeteriaJpaRepositoryBase<DishAllergen, Long> implements DishAllergenRepository {

    @Override
    public Iterable<DishAllergen> activeDishAllergens() {
        return match("e.active=true");
    }

    //Por corrigir, provavelmente user o match(); e returnar uma lista
    @Override
    public Iterable<DishAllergen> findByDish(Dish dish) {
        return match(dish.id().toString());
    }
    
    //Por Corrigir
    @Override
    public Iterable<DishAllergen> findByAllergen(Allergen allergen) {
        return match(allergen.id());
    }
}
