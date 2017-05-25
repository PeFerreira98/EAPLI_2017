/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.booking.BookingController;
import eapli.ecafeteria.application.booking.ShowMealInfoBookingController;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.framework.presentation.console.AbstractUI;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
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
        final CafeteriaUser cafeteriaUser = this.controller.returnActiveCafeteriaUser();

        Iterable<Meal> mealsDate = controller.getMeals();
        System.out.println("Available dates of meals\n");
        for (Meal meal : mealsDate) {
            System.out.println("Date: " + meal.date().getTime().toString());
        }

        final Calendar date = Console.readCalendar("Insert meal date: (dd-MM-yyyy)");
        
        final Iterable<MealType> mealTypes = this.controller.listMealTypes();

        System.out.println("\nChoose a meal type:");
        final SelectWidget<MealType> mealTypeSelector = new SelectWidget<>("xpto", mealTypes, new MealTypePrinter());
        mealTypeSelector.show();
        final MealType mealType = mealTypeSelector.selectedElement();

        if (mealType == null) {
            return false;
        }
        
        final Iterable<Meal> meals = this.controller.listMealsByDateAndMealType(date, mealType);
        if (meals == null) {
            System.out.println("There are no meals available!");
            return false;
        }
        
        System.out.println("\nChoose a meal:");
        final SelectWidget<Meal> mealSelector = new SelectWidget<>("xpto", meals, new MealPrinter());
        mealSelector.show();
        final Meal mealChosen = mealSelector.selectedElement();

        //------------------------------------------------------
        // 
        // SRR-06B  Codigo da parte de mostrar info da Meal indicada pelo user. Hugo & Pedro    
        //
        boolean showInfo = false;
        showInfo = true;          // Descomentar para correr a parte de mostar a informacao sobre os allergenios.
        if (showInfo) {

            //Instranciacao do controller.
            ShowMealInfoBookingController showController = new ShowMealInfoBookingController();

            //Mostrar as cal+sal da meal:
            NutricionalInfo selectedMealNutricionalInfo = null;
            NutricionalInfoPrinter nutricionalInfoPrinter = new NutricionalInfoPrinter();
            try {
                selectedMealNutricionalInfo = showController.obtainNutricionalInfo(mealChosen);
                System.out.println("Meal Nutritional info: ");
                nutricionalInfoPrinter.visit(selectedMealNutricionalInfo);
            } catch (IllegalArgumentException ex) {    //apanha o meal == null.
                System.out.println("ERROR: " + ex.getMessage() + "\n");
            }

            //Mostrar os alegenicos:
            System.out.println("\nAllergen present in the meal:");
            List<Allergen> listAlergenInMeal = showController.obtainListAllergen(mealChosen);
            AllergenPrinter dishAllergenPrinter = new AllergenPrinter();
            for (Allergen dishAllergen : listAlergenInMeal) {
                System.out.print("  - ");
                dishAllergenPrinter.visit(dishAllergen);
            }
            if (listAlergenInMeal.isEmpty()) {
                System.out.println(" none ");
            }

            //Mostrar warning caso allergico
            System.out.println("\nChecking if user is allergic...");
            boolean isAllergic = showController.isAllergic(mealChosen, this);
            // O padrao observador deve mostrar o alerta automaticamente. "isAllergic fica como debug".

            //Mostrar consumo de calorias/sal dos bookings desta semana incluindo a mealSelecionada.
            System.out.println("\nPlaned week nutricional consumption:");
            NutricionalInfo weekCommulativeNutritional = showController.returnWeekInfo(mealChosen);
            nutricionalInfoPrinter.visit(weekCommulativeNutritional);

        }
        //Fim da parte de mostrar info. Hugo & Pedro
        // subi a parte de mostrar os alergenicos, para o local correcto, antes de de criar o booking..        

        if (mealChosen != null) {
            try {
                if (this.controller.bookingMeal(cafeteriaUser, mealChosen) == null) {
                    System.out.println("Reserve not registered.");
                } else {
                    System.out.println("Reserve registerd.");
                }
            } catch (DataConcurrencyException ex) {
                System.out.println("Data was changed meanwhile. Please try again.");
            } catch (DataIntegrityViolationException ex) {
                System.out.println("Reservation already registered!");
            }
        }

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
