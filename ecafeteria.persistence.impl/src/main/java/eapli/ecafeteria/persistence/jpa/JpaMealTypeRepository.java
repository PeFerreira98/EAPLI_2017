/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
