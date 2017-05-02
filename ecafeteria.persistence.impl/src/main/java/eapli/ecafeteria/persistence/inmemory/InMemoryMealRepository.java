/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.CompositeIdMeal;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Calendar;

/**
 *
 * @author Marcos
 */
public class InMemoryMealRepository extends InMemoryRepository<Meal, CompositeIdMeal> implements MealRepository {

    @Override
    public Iterable<Meal> mealsByMenu(Menu menu) {
        return match(e -> e.menu().equals(menu));
    }

    @Override
    public Iterable<Meal> mealsOfPublishedMenuFromCertainDate(Calendar date) {
        return match(e -> e.date().equals(date));
    }

	@Override
	protected CompositeIdMeal newPK(Meal entity) {
		return entity.id();
	}

    @Override
    public Iterable<Meal> mealsOfMenuByDateMealType(Calendar date, MealType mealType, Menu menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
