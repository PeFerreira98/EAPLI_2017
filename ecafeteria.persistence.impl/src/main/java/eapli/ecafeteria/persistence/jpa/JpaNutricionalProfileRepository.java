package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
class JpaNutricionalProfileRepository extends CafeteriaJpaRepositoryBase<NutricionalProfile, Long> implements NutricionalProfileRepository {

    @Override
    public NutricionalProfile findByCafeteriaUser(CafeteriaUser user) {
        // TODO use parameters instead of string concatenation
        return matchOne("e.cafeteriaUser='" + user + "'");
    }

}
