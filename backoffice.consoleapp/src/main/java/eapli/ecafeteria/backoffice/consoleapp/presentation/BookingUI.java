/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.ecafeteria.application.*;


/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class BookingUI extends AbstractUI {

//    private final BookingController controller = new BookingController();

    protected Controller controller() {
//        return this.controller;
return null;
    }

    @Override
    protected boolean doShow() {

//        final String string = Console.readLine("Insert Date (EX.: 02-10-2000): "); //"January 2, 2010";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
//        Calendar date = DateTime.parseDate(string);
//         //System.out.println(date);
//        LocalDate localDate = LocalDate.parse(string, formatter);
//        final Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        final Iterable<Meal> listMeals = this.controller.listMeals(date);
//        //Remove this after Create Meal operations are done
//        final Iterable<Meal> listMeals = this.controller.listAllMeals();
//        System.out.println("Meal List:\n");
//        final SelectWidget<Meal> selector = new SelectWidget<>(listMeals, new MealPrinter());
//        selector.show();
//        final Meal meal = selector.selectedElement();

//        try {
//            this.controller.bookingMeal(meal);
//        } catch (DataIntegrityViolationException e) {
//            System.out.println("This Meal is already booked for this User");
//        }
        return false;
    }

    @Override
    public String headline() {
        return "Booking Process";
    }

}
