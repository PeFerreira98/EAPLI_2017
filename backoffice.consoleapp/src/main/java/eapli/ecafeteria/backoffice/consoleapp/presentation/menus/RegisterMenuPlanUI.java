/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.application.menus.RegisterMenuPlanController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;

/**
 *
 * @author Fernando
 */
public class RegisterMenuPlanUI extends AbstractUI{
    private final RegisterMenuPlanController theController = new RegisterMenuPlanController();
    private Menu selectMenu = null;
    private Meal selectMeal = null;

        
    protected Controller controller(){
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        SelectWidget<Menu> swMenu = new SelectWidget("Choose a menu:", theController.getPublishedMenus() );
        swMenu.show();
        
        selectMenu = swMenu.selectedElement();
        
        // falta testar se desisteu.
        
        
        SelectWidget<Meal> swMeal = new SelectWidget("Choose a Meal:", theController.getMealByMenu(selectMenu));
        
        
        swMeal.show();
        
        selectMeal = swMeal.selectedElement();
        
        final int quantidade = Console.readInteger("Quantity:");
        try{
            this.theController.CreateMenuPlan( selectMenu, selectMeal, quantidade );
        }catch( final DataIntegrityViolationException | DataConcurrencyException e ){
            System.out.println( "You tried to enter a meal plan which already exists in the data base");
        }

        
        return false;
    }

    @Override
    public String headline() {
        return "Menu Plan";
    }
    
}
