package com.seattlesolvers.solverslib.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InterpLUTTest {

    InterpLUT lut;

    @Test
    public void testAddDuplicate() {
        lut = new InterpLUT();
        lut.add(1, 1);
        lut.add(1, 3);
        lut.add(4, 1);
        try {
            lut.createLUT();
            //computing the spline
        } catch (IllegalArgumentException ex) {
            assertEquals("The control points must all have strictly increasing X values.", ex.getMessage());
        }
    }

    @Test
    public void testCreateLUT() {
        lut = new InterpLUT();
        lut.add(1, 1);
        lut.add(2, 2);
        lut.add(3, 3);
        lut.add(4, 4);
        lut.createLUT();
    }

    @Test
    public void testGet() {
        lut = new InterpLUT();
        lut.add(1, 1);
        lut.add(2, 2);
        lut.add(3, 3);
        lut.add(4, 4);
        lut.createLUT();
        assertEquals(2, lut.get(2), 0.000001);
    }

    @Test
    public void testInvalidCreation() {
        lut = new InterpLUT();
        try {
            lut.createLUT();
        } catch (IllegalArgumentException ex) {
            assertEquals("There must be at least two control points and the arrays must be of equal length.",
                    ex.getMessage());
        }
    }

    @Test
    public void testLargeCase() {
        lut = new InterpLUT();
        for (int i = -100; i <= 100; i++) {
            lut.add(i, i + 1);
        }
        lut.createLUT();
        // see https://en.wikipedia.org/wiki/Machine_epsilon
        assertEquals(lut.get(85.5), 86.5, 2.22e-16);
    }

}