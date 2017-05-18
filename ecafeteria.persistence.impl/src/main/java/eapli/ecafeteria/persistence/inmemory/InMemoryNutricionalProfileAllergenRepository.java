package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfileAllergen;
import eapli.ecafeteria.persistence.NutricionalProfileAllergenRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class InMemoryNutricionalProfileAllergenRepository extends InMemoryRepository<NutricionalProfileAllergen, String> implements NutricionalProfileAllergenRepository {

    @Override
    protected String newPK(NutricionalProfileAllergen entity) {
        return entity.id();
    }

    @Override
    public Iterable<NutricionalProfileAllergen> findNutricionalProfileAllergenByNutricionalProfile(NutricionalProfile profile) {
        return match(e -> e.nutricionalProfile().equals(profile));
    }

}
