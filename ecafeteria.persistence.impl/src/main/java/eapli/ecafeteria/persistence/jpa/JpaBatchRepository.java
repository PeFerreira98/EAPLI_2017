/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.persistence.BatchRepository;

/**
 *
 * @author Pedro Tedim
 */
public class JpaBatchRepository extends CafeteriaJpaRepositoryBase<Batch, Long> implements BatchRepository {
    
    @Override
    public Batch findByCode(String batchCode) {
        // TODO use parameters instead of string concatenation
        return matchOne("e.batchCode.batchCode='" + batchCode + "'");
    }
    
}
