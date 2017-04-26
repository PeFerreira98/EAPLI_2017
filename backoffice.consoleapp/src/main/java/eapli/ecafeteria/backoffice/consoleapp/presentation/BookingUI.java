/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.ecafeteria.application.booking.BookingController;


/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class BookingUI extends AbstractUI {

    private final BookingController controller = new BookingController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {

//        final Iterable<Meal> listMeals = this.controller.listAllMeals();
//        System.out.println("Meal List:\n");
//        final SelectWidget<Meal> selector = new SelectWidget<>(listMeals, new MealPrinter());
//        selector.show();
//        final Meal meal = selector.selectedElement();
//
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
