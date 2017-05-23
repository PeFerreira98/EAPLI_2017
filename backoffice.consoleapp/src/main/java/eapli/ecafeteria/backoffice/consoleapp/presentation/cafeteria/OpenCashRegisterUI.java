/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.application.cafeteria.OpenCashRegisterController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.MealTypePrinter;
import eapli.ecafeteria.domain.cashregister.CashRegister;
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

//        //impressao das caixas disponiveis
//        for (CashRegister cashR : controller.getCashRegisters()) {
//            System.out.println(cashR);
//        }

        final String number = eapli.util.io.Console.readLine("Insert cash register number");

        System.out.println("Choose option");

        Iterable<MealType> mealTypes = controller.getMealTypes();

        final SelectWidget<MealType> mealTypeSelector
                = new SelectWidget<>("xpto", mealTypes, new MealTypePrinter());
                //null;
        mealTypeSelector.show();

        if (mealTypeSelector.selectedOption() == 0) {

            MealType mealTypeDefault = controller.mealByDefault();

            if (!this.controller.hasMeal(Calendar.getInstance(), mealTypeDefault)) {
                System.out.println("No meal scheduled for now");
                return false;
            }

            try {
                if (this.controller.open(number, mealTypeDefault, Calendar.getInstance())) {
                    System.out.
                            println("Cash Register opened sucessfully for " + mealTypeDefault.description());
                } else {
                    System.out.println("Cash Register is already opened");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return true;
        } else {

            try {

                final MealType mealType = mealTypeSelector.selectedElement();

                Calendar date = eapli.util.io.Console.readCalendar("Insert meal date: (dd-MM-yyyy)");

                if (!this.controller.hasMeal(date, mealType)) {
                    System.out.println("There's no meal in the date selected");
                    return false;
                }

                try {
                    if (this.controller.open(number, mealType, date)) {
                        System.out.
                                println("Cash Register opened sucessfully for " + mealType.description());
                    } else {
                        System.out.println("Cash Register is already opened");
                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (NoResultException e) {
                System.out.println("There's no such Meal");
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Open Cash Register";
    }

}
