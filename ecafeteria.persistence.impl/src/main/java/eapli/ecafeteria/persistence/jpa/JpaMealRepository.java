/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.CompositeIdMeal;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Marcos
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, CompositeIdMeal> implements MealRepository {

    @Override
    public Iterable<Meal> mealsByMenu(Menu menu) {
        return match("e.menu.name='" + menu.id() + "'");
    }

    @Override
    public Iterable<Meal> mealsOfPublishedMenuFromCertainDate(Calendar date) {
        return match("e.date='" + new Date(date.getTimeInMillis()) + "'");
    }

    @Override
    public Iterable<Meal> mealsByDateAndMealType(Calendar date, MealType mealType) {
        return match("e.date='" + new Date(date.getTimeInMillis()) + 
                "' AND e.mealType.acronym='" + mealType.id() + "'");
    }

    @Override
    public Iterable<Meal> mealsByMealType(MealType mealType) {
        return match("e.mealType.acronym='" + mealType.id() + "'");
    }
}
