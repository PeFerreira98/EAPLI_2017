package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
class InMemoryNutricionalProfileRepository extends InMemoryRepositoryWithLongPK<NutricionalProfile> implements NutricionalProfileRepository {

    @Override
    public NutricionalProfile findByCafeteriaUser(CafeteriaUser user) {
        return matchOne(e -> e.cafeteriaUser().equals(user));
    }

}
