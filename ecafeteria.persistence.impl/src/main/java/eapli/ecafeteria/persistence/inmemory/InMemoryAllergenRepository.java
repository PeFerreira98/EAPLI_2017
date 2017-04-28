/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
/**
 *
 * @author JoãoPedro
 */
public class InMemoryAllergenRepository extends InMemoryRepositoryWithLongPK<Allergen> implements AllergenRepository {

    @Override
    public Iterable<Allergen> activeAllergens() {
        return match(e -> e.isActive());
    }

    @Override
    public Allergen findByName(String name) {
        return matchOne(e -> e.id().equals(name));
    }
}
