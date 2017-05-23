/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.mealplans.MealPlan;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

/**
 *
 * @author Nuno Filipe 1131106
 */
public interface MealPlanRepository extends DataRepository<MealPlan, Long> {

    Iterable<MealPlan> activeMealPlan();

    MealPlan findByDate(Calendar dateStart, Calendar dateEnd);

    MealPlan findByDefault();

}
