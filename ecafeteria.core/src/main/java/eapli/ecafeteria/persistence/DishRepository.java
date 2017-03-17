package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.Repository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface DishRepository extends Repository<Dish, Designation> {

    Dish findByName(Designation name);
}
