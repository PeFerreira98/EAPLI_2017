/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishAllergen;
import eapli.ecafeteria.persistence.DishAllergenRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author JoãoPedro
 */
public class InMemoryDishAllergenRepository extends InMemoryRepositoryWithLongPK<DishAllergen> implements DishAllergenRepository {

    @Override
    public Iterable<DishAllergen> activeDishAllergens() {
        return match(e -> e.isActive());
    }

    @Override
    public Iterable<DishAllergen> findByDish(Dish dish) {
        return match(e -> e.idDish().equals(dish.id().toString()));
    }
    
    @Override
    public Iterable<DishAllergen> findByAllergen(Allergen Allergen) {
        return match(e -> e.idAllergen().equals(Allergen.id()));
    }
}
