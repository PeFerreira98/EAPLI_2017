package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.NutricionalProfile;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class RegisterNutricionalProfileController {

    private final NutricionalProfileRepository repository = PersistenceContext.repositories().nutricionalProfiles();

    public NutricionalProfile registerNutricionalProfile(CafeteriaUser user, Integer dailyCalories, Integer dailySalt, Integer weeklyCalories, Integer weeklySalt)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_PROFILE);

        final NutricionalProfile newNutricionalProfile = new NutricionalProfile(user, dailyCalories, dailySalt, weeklyCalories, weeklySalt);
        return this.repository.save(newNutricionalProfile);
    }

}
