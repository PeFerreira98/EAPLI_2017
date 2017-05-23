/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.mealplans.MealPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import java.util.Calendar;
import javax.persistence.Query;

/**
 *
 * @author Nuno Filipe 1131106
 */
public class JpaMealPlanRepository extends CafeteriaJpaRepositoryBase<MealPlan, Long> implements MealPlanRepository {

    @Override
    public Iterable<MealPlan> activeMealPlan() {
        return match("e.active=true");
    }

    @Override
    public MealPlan findByDate(Calendar dateStart, Calendar dateEnd) {
        return matchOne("e.date=:dateStart & dateEnd", "dateStart", "dateEnd", dateStart, dateEnd);
    }

    @Override
    public MealPlan findByDefault() {

        Calendar date = Calendar.getInstance();

        final Query q = entityManager().
                createQuery("select mt from MealPlan mp "
                        + "where mp.designation.mealPlanDate like '" + date + "'", MealPlan.class);

        return (MealPlan) q.getSingleResult();
    }

}
