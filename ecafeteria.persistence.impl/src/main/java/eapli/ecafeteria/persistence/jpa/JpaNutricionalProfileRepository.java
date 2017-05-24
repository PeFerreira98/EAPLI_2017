package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class JpaNutricionalProfileRepository extends CafeteriaJpaRepositoryBase<NutricionalProfile, MecanographicNumber> implements NutricionalProfileRepository {

    @Override
    public NutricionalProfile findByCafeteriaUser(CafeteriaUser user) {
        return findByMecNumber(user.id());
    }

    @Override
    public NutricionalProfile findByMecNumber(MecanographicNumber number) {
        return matchOne("e.id=:number", "number", number);
    }
}
