/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.application.booking.ConsultReservesController;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class ConsultReservesUI extends AbstractUI {

    private final ConsultReservesController controller = new ConsultReservesController();

    @Override
    protected boolean doShow() {

        try {
            Calendar dateInitial = Calendar.getInstance();
            Calendar dateFinal = Console.
                    readCalendar(" Please insert final date (dd-MM-yyyy):", "dd-MM-yyyy");

            final Iterable<Booking> reserves = controller.
                    getReservesBetweenDates(null, dateInitial, dateFinal);

            System.out.println("\nReserves: ");
            for (final Booking r : reserves) {
                System.out.println("Date: " + new SimpleDateFormat("dd-MM-yyyy").
                        format(r.meal().date().getTime())
                        + " Meal type: " + r.meal().mealType().id() + "\n");
            }

        } catch (NullPointerException ex) {
            System.out.println("There is no reserves to the user" + "\n");
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid reserve date" + "\n");
        }

        return false;
    }

    @Override
    public String headline() {
        return ("Consult reserves in next n days");
    }

}
