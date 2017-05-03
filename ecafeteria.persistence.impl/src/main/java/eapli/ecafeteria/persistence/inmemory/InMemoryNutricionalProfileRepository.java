package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
class InMemoryNutricionalProfileRepository extends InMemoryRepository<NutricionalProfile, CafeteriaUser> implements NutricionalProfileRepository {

    @Override
    protected CafeteriaUser newPK(NutricionalProfile entity) {
        return entity.cafeteriaUser();
    }

    @Override
    public NutricionalProfile findByCafeteriaUser(CafeteriaUser user) {
        return matchOne(e -> e.cafeteriaUser().equals(user));
    }

}
