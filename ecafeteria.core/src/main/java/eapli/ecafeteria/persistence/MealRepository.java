/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 *
 * @author Marcos
 */
public interface MealRepository extends DataRepository<Meal, Long>, TransactionalContext{
 
    Iterable<Meal> mealsByMenu(Menu menu);
    
}