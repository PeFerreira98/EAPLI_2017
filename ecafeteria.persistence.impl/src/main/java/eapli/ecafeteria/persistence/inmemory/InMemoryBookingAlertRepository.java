package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.alert.Alert;
import eapli.ecafeteria.domain.alert.BookingAlert;
import eapli.ecafeteria.persistence.BookingAlertRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Rafael Vieira <1141233@isep.ipp.pt>
 */
public class InMemoryBookingAlertRepository extends InMemoryRepository<BookingAlert, Alert> implements BookingAlertRepository {

    @Override
    protected Alert newPK(BookingAlert entity) {
        return entity.id();
    }

    @Override
    public BookingAlert findBookingAlertByAlert(Alert alert) {
        return matchOne(e -> e.id().equals(alert));
    }

}
