/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.rating;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class RatingTest {
    
    public RatingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Rating constructor with parameters.
     */
    @Test
    public void testRating() {
        System.out.println("Rating");
        
        OrganicUnit unit = new OrganicUnit("ISEP", "Instituto Superior de Engenharia do PORTO", "Good school :");
        Set<RoleType> role = new TreeSet<>();
        SystemUser user = new SystemUser("Password1", "Password1", "John", "Smith", "john@smith.com", role);
        //new MecanographicNumber("900330"); 
        CafeteriaUser cafeteriaUser = new CafeteriaUser(user, unit, new MecanographicNumber("900330") );
        
        DishType dishType = new DishType("pex", "nada");
        //Designation desig = new Designation("ppp");
        NutricionalInfo nutri = new NutricionalInfo( 10, 10);
        Money money = new Money(100, Currency.getInstance("EUR"));
        Dish dish = new Dish(dishType, Designation.valueOf("ppp"), nutri, money);
        MealType mealType = new MealType("lunch", "lunch meal", 10);
        Calendar clBeginning = GregorianCalendar.getInstance();
        Calendar clEnding = GregorianCalendar.getInstance();
        //2017, Calendar.APRIL, 9, 2017, Calendar.APRIL, 15
        int yearBeg = 2017;
        int monthBeg = Calendar.APRIL;
        int dayBeg = 9;
        int yearEnd = 2017;
        int monthEnd = Calendar.APRIL;
        int dayEnd = 15;
        clBeginning.set(yearBeg, monthBeg, dayBeg);
        
        clEnding.set(yearEnd, monthEnd, dayEnd);
        Menu menu = new Menu("MenuSemanaAbril",  clBeginning,  clEnding);
        Meal meal = new Meal(clBeginning, dish, mealType, menu);
        
        Rating rating = null;
        
        // Meal null
        try{
            rating = new Rating( 3, cafeteriaUser, null, null);
            System.out.println("Failed to throw null meal");
            assertTrue(false);
        }
        catch(IllegalArgumentException ex){
            assertTrue(true); 
        }
        // user null
        try{
            rating = new Rating( 3, null , meal , null);
            System.out.println("Failed to throw null cafeteriaUser");
            assertTrue(false);
        }
        catch(IllegalArgumentException ex){
            assertTrue(true); 
        }
        // invalid rating
        try{
            rating = new Rating( -1, cafeteriaUser , meal , null);
            System.out.println("Failed to throw invalid rating number");
            assertTrue(false);
        }
        catch(IllegalArgumentException ex){
            assertTrue(true); 
        }
        // invalid rating
        try{
            rating = new Rating( 0, cafeteriaUser , meal , null);
            System.out.println("Failed to throw invalid rating number");
            assertTrue(false);
        }
        catch(IllegalArgumentException ex){
            assertTrue(true); 
        }
        // invalid rating
        try{
            rating = new Rating( 6, cafeteriaUser , meal , null);
            System.out.println("Failed to throw invalid rating number");
            assertTrue(false);
        }
        catch(IllegalArgumentException ex){
            assertTrue(true); 
        }
        
        // VALID
        try{
            rating = new Rating( 3, cafeteriaUser , meal , new Comment("ola", cafeteriaUser));
            System.out.println("Failed to throw invalid rating number");
            assertTrue(true);
        }
        catch(Exception ex){
            assertTrue(false); System.out.println("Failed to construct a true rating.");
        }
        
        
    }

    
    
}
