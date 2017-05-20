package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfileAllergen;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfileAllergenId;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public interface NutricionalProfileAllergenRepository extends DataRepository<NutricionalProfileAllergen, NutricionalProfileAllergenId> {

    Iterable<NutricionalProfileAllergen> findNutricionalProfileAllergenByNutricionalProfile(NutricionalProfile profile);
}
