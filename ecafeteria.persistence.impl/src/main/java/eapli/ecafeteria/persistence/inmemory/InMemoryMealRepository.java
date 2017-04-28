/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Marcos
 */
public class InMemoryMealRepository extends InMemoryRepositoryWithLongPK<Meal> implements MealRepository{

    @Override
    public Iterable<Meal> mealsByMenu(Menu menu) {
         return match(e -> e.menu().equals(menu));
    }
}
