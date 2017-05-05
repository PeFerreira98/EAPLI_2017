/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.rating;

import eapli.ecafeteria.application.rating.MealRatingController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.user.consoleapp.presentation.meals.MealPrinter;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;
import java.util.List;

/**
 *
 * @author Hugo & Pedro
 */
public class MealRatingUI extends AbstractUI{

    
    private static final int MIN_RATING_UI = 1;
    private static final int MAX_RATING_UI = 5;
    private static final int EXIT_RATING_UI = 0;
    
    private final MealRatingController theController = new MealRatingController();
    
    @Override
    protected boolean doShow() {
        
        //Obter lista de meals a mostar.
        List<Meal> mealList = theController.listMeals();
        
        //Mostrar meals com numero.
        System.out.println("Avaiable meals:");
        int position = 0;
        for(Meal meal : mealList){
            position++;
            System.out.print("["+position+"] --- ");
            new MealPrinter().visit(meal);
        }
        
        //Se nao existirem meals saimos.
        if(mealList.isEmpty()){
            System.out.println("No meals to rate. Returning.");
            return false;
        }
        
        //Questionar numero da meal.
        int low = 1;
        int high = mealList.size();
        int exit = EXIT_RATING_UI;
        
        System.out.println("Select a  meal ["+low+"-"+high+"] or " + exit + " to exit:");
        int selection = Console.readOption(low, high, exit);
         
        if(selection == 0){
            System.out.println("Exit selected. Returning.\n");
            return false;
        }
        
        Meal selectedMeal = mealList.get(selection - 1 );
        
        //Perguntar o rating.
        low = MIN_RATING_UI;
        high = MAX_RATING_UI;
        exit = EXIT_RATING_UI;
        System.out.println("Select a rating for the meal ["+low+"-"+high+"] or " + exit + " to exit:");
        int rate = Console.readOption(low, high, exit);
        
        //Questionar o comentario.
        String comment = Console.readLine("Please comment the meal. Empty line for no comment.");
        
        
        //Criar o rating.
        boolean sucess = false;
        try{ 
            sucess = this.theController.registerNewMealRating(selectedMeal, rate, comment);
        }
        catch(IllegalArgumentException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        
        if(sucess) System.out.println("Rating created with sucess.");
        else System.out.println("Rating creation failed. Exiting.");
        
        
        return false;   //Nao sei pk retornamos falso.
    }

    @Override
    public String headline() {
        return "Rate Meal";
    }
    
}
