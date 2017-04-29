/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.CompositeIdMeal;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import java.util.Calendar;

/**
 *
 * @author Marcos
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, CompositeIdMeal> implements MealRepository{

    @Override
    public Iterable<Meal> mealsByMenu(Menu menu) {
        return match("e.menu='" + menu +"'");
    }

    @Override
    public Iterable<Meal> mealsOfCertainDate(Calendar date) {
        return match("e.date='" + date +"'");
    }
    
}
