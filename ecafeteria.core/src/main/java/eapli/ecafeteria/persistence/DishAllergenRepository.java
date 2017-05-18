/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishAllergen;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author JoãoPedro
 */
public interface DishAllergenRepository extends DataRepository<DishAllergen, Long> {
    /**
     * returns the active dish types
     *
     * @return
     */
    Iterable<DishAllergen> activeDishAllergens();

    Iterable<DishAllergen> findByDish(String name);
    Iterable<DishAllergen> findByAllergen(String name);
    
}
