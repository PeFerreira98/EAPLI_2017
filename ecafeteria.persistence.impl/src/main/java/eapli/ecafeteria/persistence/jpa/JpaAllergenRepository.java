/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;

/**
 *
 * @author JoãoPedro
 */
public class JpaAllergenRepository extends CafeteriaJpaRepositoryBase<Allergen, Long> implements AllergenRepository {

    @Override
    public Iterable<Allergen> activeAllergens() {
        return match("e.active=true");
    }

    @Override
    public Allergen findByName(String name) {
        return matchOne("e.name=:name", "name", name);
    }
}
