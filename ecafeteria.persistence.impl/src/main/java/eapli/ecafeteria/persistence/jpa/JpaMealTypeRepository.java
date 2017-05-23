/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import java.util.Calendar;
import javax.persistence.Query;

/**
 *
 * @author zero_
 */
public class JpaMealTypeRepository extends CafeteriaJpaRepositoryBase<MealType, Long> implements MealTypeRepository {

    @Override
    public Iterable<MealType> activeMealTypes() {
        return match("e.active=true");
    }

    @Override
    public MealType findByAcronym(String acronym) {
        return matchOne("e.acronym=:acronym", "acronym", acronym);
    }

    @Override
    public MealType findByDefault() {
        if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 15
                || (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 15 && Calendar.
                getInstance().get(Calendar.MINUTE) == 0)) {
            final Query q = entityManager().
                    createQuery("select mt from MealType mt where mt.acronym like 'lunch'", MealType.class);

            return (MealType) q.getSingleResult();
            
        } else {
            final Query q = entityManager().
                    createQuery("select mt from MealType mt where mt.acronym like 'dinner'", MealType.class);
          
            return (MealType) q.getSingleResult();
        }
    }

}
