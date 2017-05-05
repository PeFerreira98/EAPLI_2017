/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.MealBatch;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;

/**
 *
 * @author Pedro Tedim
 */
public interface MealBatchRepository extends DataRepository<MealBatch, Long> {
    
    MealBatch findById(String batchCode);

    List<MealBatch> findOne(String batchCode);
}
