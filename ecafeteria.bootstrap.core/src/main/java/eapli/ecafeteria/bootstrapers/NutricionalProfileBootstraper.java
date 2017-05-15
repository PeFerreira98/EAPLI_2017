package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cafeteria.RegisterNutricionalProfileController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
class NutricionalProfileBootstraper implements Action {

    @Override
    public boolean execute() {
        final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers(PersistenceContext.repositories().buildTransactionalContext());
        final CafeteriaUser user1 = userRepo.findByUsername(new Username("900330"));
        final CafeteriaUser user2 = userRepo.findByUsername(new Username("900331"));

        register(user1, 500, 25, 3000, 150);
        register(user2, 1000, 50, 5000, 300);

        return false;
    }

    private void register(final CafeteriaUser user, final Integer dailyCalories, final Integer dailySalt, final Integer weeklyCalories, final Integer weeklySalt) {
        final RegisterNutricionalProfileController controller = new RegisterNutricionalProfileController();
        try {
            controller.registerNutricionalProfile(user, dailyCalories, dailySalt, weeklyCalories, weeklySalt);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
