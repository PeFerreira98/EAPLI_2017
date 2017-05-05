/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.MealBatch;
import eapli.ecafeteria.persistence.MealBatchRepository;
import java.util.List;

/**
 *
 * @author Pedro Tedim
 */
public class JpaMealBatchRepository extends CafeteriaJpaRepositoryBase<MealBatch, Long> implements MealBatchRepository  {
    
    @Override
    public MealBatch findById(String batchCode) {
        // TODO use parameters instead of string concatenation
        return matchOne("e.batchCode='" + batchCode + "'");
    }

    @Override
    public List<MealBatch> findOne(String batchCode) {
        
        return match("e.mbk.batchCode = '"+batchCode+"'");
    }

}
