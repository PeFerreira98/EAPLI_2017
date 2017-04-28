/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.framework.persistence.repositories.DataRepository;
/**
 *
 * @author JoãoPedro
 */
public interface AllergenRepository extends DataRepository<Allergen, Long> {
    /**
     * returns the active dish types
     *
     * @return
     */
    Iterable<Allergen> activeAllergens();

    Allergen findByName(String name);
}
