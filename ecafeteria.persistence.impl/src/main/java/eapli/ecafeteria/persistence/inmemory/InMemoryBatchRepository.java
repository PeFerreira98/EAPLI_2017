/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Pedro Tedim
 */
public class InMemoryBatchRepository extends InMemoryRepositoryWithLongPK<Batch> implements BatchRepository {

    @Override
    public Batch findByCode(String batchCode) {
        return matchOne(e -> e.id().equals(batchCode));
    }

}
