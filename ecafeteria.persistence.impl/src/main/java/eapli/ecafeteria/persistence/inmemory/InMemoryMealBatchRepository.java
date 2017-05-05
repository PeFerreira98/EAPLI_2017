/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.MealBatch;
import eapli.ecafeteria.persistence.MealBatchRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.List;

/**
 *
 * @author Pedro Tedim
 */
public class InMemoryMealBatchRepository extends InMemoryRepositoryWithLongPK<MealBatch> implements MealBatchRepository {

    @Override
    public MealBatch findById(String batchCode) {
        return matchOne(e -> e.id().equals(batchCode));
    }

    @Override
    public List<MealBatch> findOne(String batchCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
