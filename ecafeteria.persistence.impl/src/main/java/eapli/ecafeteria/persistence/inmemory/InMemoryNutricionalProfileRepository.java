package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class InMemoryNutricionalProfileRepository extends InMemoryRepository<NutricionalProfile, MecanographicNumber> implements NutricionalProfileRepository {

    @Override
    public NutricionalProfile findByCafeteriaUser(CafeteriaUser user) {
        return matchOne(e -> e.id().equals(user.id()));
    }

    @Override
    protected MecanographicNumber newPK(NutricionalProfile entity) {
        return entity.id();
    }

}
