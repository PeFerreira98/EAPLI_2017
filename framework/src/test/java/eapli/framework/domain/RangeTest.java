/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.domain;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author pgsou_000
 */
public class RangeTest extends AbstractRangeTest {

    public RangeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Range");
        instance = Range.openRange(START, END);
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

    @Test(expected = IllegalStateException.class)
    public void ensureStartBiggerThanEndIsNotAllowed() {
        System.out.println("ensureStartBiggerThanEndIsNotAllowed");
        @SuppressWarnings("unused")
		Range iut = Range.openRange(END, START);
    }

    @Test
    public void ensureLowerIsNotInRange() {
        System.out.println("ensureLowerIsNotInRange");
        Long target = new Long(START_VALUE - DELTA_VALUE);
        boolean result = instance.includes(target);
        assertFalse("value lower than start cannot be part of an open range", result);
    }

    @Test
    public void ensureUpperIsNotInRange() {
        System.out.println("ensureUpperIsNotInRange");
        Long target = new Long(END_VALUE + DELTA_VALUE);
        boolean result = instance.includes(target);
        assertFalse("value greater than end cannot be part of an open range", result);
    }

    @Test
    public void ensureMiddleIsInRange() {
        System.out.println("ensureMiddleIsInRange");
        Long target = new Long(START_VALUE + DELTA_VALUE / 2);
        boolean result = instance.includes(target);
        assertTrue("value in the middle is part of an open range", result);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureEmptyRangeIsNotAllowed() {
        System.out.println("ensureEmptyRangeIsNotAllowed");
        @SuppressWarnings("unused")
		Range iut = Range.openRange(START, START);
    }
}
