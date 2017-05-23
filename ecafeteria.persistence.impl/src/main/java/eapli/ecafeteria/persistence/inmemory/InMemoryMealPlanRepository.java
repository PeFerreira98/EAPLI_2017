/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.mealplans.MealPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Calendar;

/**
 *
 * @author Nuno Filipe 1131106
 */
public class InMemoryMealPlanRepository extends InMemoryRepositoryWithLongPK<MealPlan> implements MealPlanRepository {

    @Override
    public Iterable<MealPlan> activeMealPlan() {
        return match(e -> e.equals(e));
    }

    @Override
    public MealPlan findByDate(Calendar dateStart, Calendar dateEnd) {
        return matchOne(e -> (e.getPeriodStart().equals(dateStart) && e.getPeriodEnd().equals(dateEnd)));

    }

    @Override
    public MealPlan findByDefault() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
