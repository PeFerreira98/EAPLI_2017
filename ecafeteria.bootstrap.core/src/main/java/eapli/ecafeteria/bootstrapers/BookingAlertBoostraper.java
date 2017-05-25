package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.alert.RegisterBookingAlertController;
import eapli.ecafeteria.domain.alert.Alert;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class BookingAlertBoostraper implements Action {

    @Override
    public boolean execute() {

        register(Alert.RED, 90);
        register(Alert.YELLOW, 75);

        return false;
    }

    private void register(final Alert alert, final Integer value) {
        final RegisterBookingAlertController controller = new RegisterBookingAlertController();
        try {
            controller.registerBookingAlert(alert, value);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
