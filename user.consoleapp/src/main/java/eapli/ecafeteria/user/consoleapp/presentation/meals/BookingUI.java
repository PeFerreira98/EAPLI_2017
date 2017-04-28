/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.application.booking.BookingController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;
import java.util.Calendar;

/**
 * SRR-06 Classe do UI de escolha e booking de uma unica refeicao SRR-06
 *
 * @author Hugo - Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class BookingUI extends AbstractUI {

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
            
        }

        try {
            this.controller.bookingMeal(null,null);
        } catch (DataIntegrityViolationException e) {
            System.out.println("This Meal is already booked for this User");
        } catch (DataConcurrencyException e) {
            System.out.println("");
        }

        //------------------------------------------------------
        //INSERIR codigo do bookingUI aqui.
        //Codigo da parte de mostrar info da Meal indicada pelo user. Hugo & Pedro        
        Meal mealSelecionada = null;  //meal que o utilizador indicou no codigo acima.
        {
            /*
             //Instranciacao do controller.
             ShowMealInfoBookingController showController = new ShowMealInfoBookingController();
        
             //Mostrar as cal+sal da meal:
             NutricionalInfo informacaoNutricionalDaMealSelecionada = showController.obtainNutricionalInfo(mealSelecionada);
             System.out.println("Meal Nutritional info: ");
             NutricionalInfoPrinter nutricionalInfoPrinter = new NutricionalInfoPrinter();
             nutricionalInfoPrinter.visit(informacaoNutricionalDaMealSelecionada);
        
             //Mostrar os alegenicos:
             System.out.println("Allergen present in the meal:");
             //List<DishAllergen> listaDishAlergenicos = showController.obtainListAllergen(Meal meal);
             DishAllergenPrinter dishAllergenPrinter = new DishAllergenPrinter();
             for(DishAllergen dishAllergen : listaDishAllergenicos){
             dishAllergenPrinter.visit(dishAllergen);
             }
            
             //Mostrar warning caso allergico
             if(showController.isAllergic(mealSelecionada)) System.out.println("WARNING you are allergic to some meal contents!!!");
            
             //Mostrar consumo de calorias/sal dos bookings desta semana incluindo a mealSelecionada.
             NutritionalInfo weekCommulativeNutritional = showController.returnWeakInfoMeal(meal)
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

}
