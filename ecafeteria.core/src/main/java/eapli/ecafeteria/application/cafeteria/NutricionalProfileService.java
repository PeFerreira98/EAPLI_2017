package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class NutricionalProfileService {

    private final NutricionalProfileRepository repo = PersistenceContext.repositories().nutricionalProfiles();

    public NutricionalProfile findCurrentUserNutricionalProfile() {
        NutricionalProfile profile = null;

        try {
            CafeteriaUser user = new CafeteriaUserService().obtainCurrentCafeteriaUser();
            profile = repo.findByCafeteriaUser(user);
        } catch (javax.persistence.PersistenceException ex) {
            String error = "Error getting the CafeteriaUser of logged CafeteriaUser.   " + ex;
            Logger.getGlobal().severe(error);
        }
        return profile;

    }
}
