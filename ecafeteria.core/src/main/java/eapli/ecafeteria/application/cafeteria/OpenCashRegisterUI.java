/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.Calendar;
import javax.persistence.NoResultException;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class OpenCashRegisterUI extends AbstractUI {

    private OpenCashRegisterController controller = new OpenCashRegisterController();

    @Override
    protected boolean doShow() {

        final String number = eapli.util.io.Console.readLine("Insert cash register number");

        System.out.println("Choose option");

        Iterable<MealType> mealTypes = controller.getMealTypes();

        final SelectWidget<MealType> mealTypeSelector = //new SelectWidget<>("xpto", mealTypes, new MealTypePrinter());
                null;
        mealTypeSelector.show();

        if (mealTypeSelector.selectedOption() == 0) {

            MealType mealTypeDefault = controller.mealByDefault();

            try {
                if (this.controller.open(number, mealTypeDefault, Calendar.getInstance())) {
                    System.out.
                            println("Cash Register opened sucessfully for " + mealTypeDefault.description());
                } else {
                    System.out.println("Cash Register is already opened");
                }
            } catch (NoResultException e) {
            }
            return true;
        } else {

            try {

                final MealType mealTypeDefault = mealTypeSelector.selectedElement();

                Calendar date = eapli.util.io.Console.readCalendar("Insert meal date: (dd-MM-yyyy)");

                try {
                    if (this.controller.open(number, mealTypeDefault, date)) {
                        System.out.
                                println("Cash Register opened sucessfully for " + mealTypeDefault.description());
                    } else {
                        System.out.println("Cash Register is already opened");
                    }

                } catch (NoResultException e) {
                }
            } catch (NoResultException e) {
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Open Cash Register";
    }

}
