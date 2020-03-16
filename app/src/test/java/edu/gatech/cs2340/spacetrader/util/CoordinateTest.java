package edu.gatech.cs2340.spacetrader.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Coordinate class
 */
public class CoordinateTest {

    private Coordinate c1;

    @Before
    public void setup() {
        c1 = new Coordinate(0, 0);
    }

    @Test
    public void equalsReturnsFalseNoncoord() {
        Object fakeCoord = new Object();

        Assert.assertNotEquals(c1, fakeCoord);
    }

    @Test
    public void equalsReturnsFalseWrongX() {
        Coordinate wrongX = new Coordinate(1, 0);

        Assert.assertNotEquals(c1, wrongX);
    }

    @Test
    public void equalsReturnsFalseWrongY() {
        Coordinate wrongY = new Coordinate(0, 1);

        Assert.assertNotEquals(c1, wrongY);
    }

    @Test
    public void equalsReturnsFalseBothWrong() {
        Coordinate wrongBoth = new Coordinate(1, 1);

        Assert.assertNotEquals(c1, wrongBoth);
    }

    @Test
    public void equalsReturnsTrueBothRight() {
        Coordinate rightBoth = new Coordinate(0, 0);

        Assert.assertEquals(c1, rightBoth);
        Assert.assertEquals(c1, c1);
    }

    @Test
    public void calculateDistanceReturnsCorrectDistance() {
        Coordinate c2 = new Coordinate(2, 2);
        double expected = Math.sqrt( 8 );

        Assert.assertEquals(expected, c1.calculateDistance(c2), 0.0001);
    }

    @Test
    public void toStringReturnsAccurateCoords() {
        String expected = "{x=0,y=0}";

        Assert.assertEquals(expected, c1.toString());
    }
}