/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author zero_
 */
public class InMemoryMealTypeRepository extends InMemoryRepositoryWithLongPK<MealType> implements MealTypeRepository {

    @Override
    public Iterable<MealType> activeMealTypes() {
        return match(e -> e.isActive());
    }

    @Override
    public MealType findByAcronym(String acronym) {
        return matchOne(e -> e.id().equals(acronym));
    }

}
