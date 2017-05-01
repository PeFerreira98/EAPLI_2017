/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.meals.NutricionalInfo;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author AntónioRocha
 */
public class NutricionalInfoTest {

    public NutricionalInfoTest() {
    }

    /**
     * Test of calories method, of class NutricionalInfo.
     */
    @Test(expected = IllegalStateException.class)
    public void testCaloriesMustNotBeNull() {
        System.out.println("calories must not be null");
        NutricionalInfo instance = new NutricionalInfo(null, 0);
    }

    @Test(expected = IllegalStateException.class)
    public void testCaloriesMustNotBeNegative() {
        System.out.println("calories must not be negative");
        NutricionalInfo instance = new NutricionalInfo(-1, 0);
    }

    @Test(expected = IllegalStateException.class)
    public void testSaltMustNotBeNull() {
        System.out.println("Salt must not be null");
        NutricionalInfo instance = new NutricionalInfo(0, null);
    }

    @Test(expected = IllegalStateException.class)
    public void testSaltMustNotBeNegative() {
        System.out.println("Salt must not be negative");
        NutricionalInfo instance = new NutricionalInfo(0, -6);
    }

    @Test
    public void testNormalBehaviour() {
        System.out.println("normal behaviour");
        NutricionalInfo instance = new NutricionalInfo(3, 5);
    }

    @Test
    public void testCalories() {
        System.out.println("calories");
        NutricionalInfo instance = new NutricionalInfo(3, 5);
        Integer expResult = 3;
        assertEquals(expResult, instance.calories());
    }
    
    
    @Test
    public void testSumNutricionalInfo() {      // Hugo & Pedro G3
        System.out.println("sumNutricionalInfo");
        NutricionalInfo n1 = new NutricionalInfo(1, 1);
        NutricionalInfo n2 = new NutricionalInfo(2, 2);
        NutricionalInfo n3 = new NutricionalInfo(3, 3);
        
        NutricionalInfo answer = n1.sumNutricionalInfo(n2).sumNutricionalInfo(n3);
        
        Integer expResult = 6;
        
        assertEquals(expResult, answer.calories());
        assertEquals(expResult, answer.salt());
    }
    
}
