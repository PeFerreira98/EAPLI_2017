/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.application.booking.BookingController;
import eapli.ecafeteria.application.booking.ShowMealInfoBookingController;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.framework.presentation.console.AbstractUI;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * SRR-06 Classe do UI de escolha e booking de uma unica refeicao SRR-06
 * Observer - de alergias entre o user e uma meal.
 *
 * @author Hugo - Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class BookingUI extends AbstractUI implements Observer {

    private final BookingController controller = new BookingController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {

        /* Meal section */
        Calendar date = Console.readCalendar("Insert meal date: (dd-MM-yyyy)");

        /* Meal type section */
        final Iterable<MealType> mealType = this.controller.listMealType(date);

        if (!mealType.iterator().hasNext()) {
            System.out.println("There are no meal types defined!");
            return false;
        }

        final SelectWidget<MealType> mealTypeSelector = new SelectWidget<>(mealType, new MealTypePrinter());
        mealTypeSelector.show();

        final MealType mealTypeChosen = mealTypeSelector.selectedElement();

        if (mealTypeChosen == null) {
            return false;
        }

        final Iterable<Meal> meals = this.controller.listMeals(date, mealTypeChosen);

        if (meals == null) {
            System.out.println("There are no meals available!");
            return false;
        }

        final SelectWidget<Meal> mealSelector = new SelectWidget<>(meals, new MealPrinter());
        mealSelector.show();

        final Meal mealChosen = mealSelector.selectedElement();

        if (mealChosen != null) {
            try {
                this.controller.bookingMeal(null, null);
            } catch (DataIntegrityViolationException e) {
                System.out.println("This Meal is already booked for this User");
            } catch (DataConcurrencyException e) {
                System.out.println("");
            }
        }

        //------------------------------------------------------
        //INSERIR codigo do bookingUI aqui.
        //Codigo da parte de mostrar info da Meal indicada pelo user. Hugo & Pedro        
        {
            /*
             //Instranciacao do controller.
             ShowMealInfoBookingController showController = new ShowMealInfoBookingController();
        
             //Mostrar as cal+sal da meal:
             NutricionalInfo selectedMealNutricionalInfo = showController.obtainNutricionalInfo( mealChosen );
             System.out.println("Meal Nutritional info: ");
             NutricionalInfoPrinter nutricionalInfoPrinter = new NutricionalInfoPrinter();
             nutricionalInfoPrinter.visit( selectedMealNutricionalInfo);
        
             //Mostrar os alegenicos:
             System.out.println("Allergen present in the meal:");
             List<Allergen> listAlergenInMeal = showController.obtainListAllergen(mealChosen);
             AllergenPrinter dishAllergenPrinter = new AllergenPrinter();
             for(Allergen dishAllergen : listAlergenInMeal){
                dishAllergenPrinter.visit(dishAllergen);
             }
             if( listAlergenInMeal.isEmpty() ) System.out.println(" none ");
            
             //Mostrar warning caso allergico
             boolean isAllergic = showController.isAllergic(mealChosen, this);
             // O padrao observador deve mostrar o alerta automaticamente. "isAllergic fica como debug".
            
             
             
             //Mostrar consumo de calorias/sal dos bookings desta semana incluindo a mealSelecionada.
             NutricionalInfo weekCommulativeNutritional = showController.returnWeekInfo(mealChosen);
             System.out.println("Planed week nutricional consumption:");
             nutricionalInfoPrinter.visit( weekCommulativeNutritional );
             */
        }
        //Fim da parte de mostrar info. Hugo & Pedro

        return false;   //Nao identifiquei uso, pelo consegui constatar. Alguns retornam falso, outros true. O return true if this "scope" should end(HugoB)
    }

    @Override
    public String headline() {
        return "Booking Process ";
    }

    @Override
    public void update(Observable o, Object arg) {
        //Padrao Observador , usado para emetir alerta caso detectada alergia Meal-CafeteriaUser
        System.out.println("WARNING: You are allergic to this meal.");
    }

}
