package eapli.ecafeteria.user.consoleapp.presentation.meals;

import java.util.logging.Level;
import java.util.logging.Logger;
import eapli.ecafeteria.application.booking.CancelBookingController;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CancelBookingUI extends AbstractUI {

	private final CancelBookingController controller = new CancelBookingController();

	protected Controller controller() {
		return this.controller;
	}

	@Override
	protected boolean doShow() {
		final CafeteriaUser cafeteriaUser = this.controller.returnActiveCafeteriaUser();
		final Iterable<Booking> bookings = this.controller.listPayedUserBookings(cafeteriaUser);

		System.out.println("\nChoose a Booking to Cancel:");
		final SelectWidget<Booking> bookingSelector = new SelectWidget<>("Bookings", bookings, new BookingPrinter());
		bookingSelector.show();
		final Booking booking = bookingSelector.selectedElement();

		if (booking != null) {
			try {
				if (this.controller.cancelBooking(booking) != null) {
					System.out.println("Booking Cancelled.");
				}
				
			} catch (DataConcurrencyException ex) {
				System.out.println("Data was changed meanwhile. Please try again.");
				Logger.getLogger(CancelBookingUI.class.getName()).log(Level.SEVERE, null, ex);
				
			} catch (DataIntegrityViolationException ex) {
				System.out.println("Data error Occured!");
				Logger.getLogger(CancelBookingUI.class.getName()).log(Level.SEVERE, null, ex);

			}
		}
		
		return false;
	}

	@Override
	public String headline() {
		return "Booking Cancelation";
	}

}
