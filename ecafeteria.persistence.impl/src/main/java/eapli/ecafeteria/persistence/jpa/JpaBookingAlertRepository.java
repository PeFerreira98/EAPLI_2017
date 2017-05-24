package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.alert.Alert;
import eapli.ecafeteria.domain.alert.BookingAlert;
import eapli.ecafeteria.persistence.BookingAlertRepository;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class JpaBookingAlertRepository extends CafeteriaJpaRepositoryBase<BookingAlert, Alert> implements BookingAlertRepository{

    @Override
    public BookingAlert findBookingAlertByAlert(Alert alert) {
        return matchOne("e.id=:alert", "alert", alert);
    }

}
