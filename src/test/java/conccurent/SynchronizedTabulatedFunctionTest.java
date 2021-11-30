package conccurent;

import functions.LinkedListTabulatedFunction;
import functions.MathFunction;
import functions.Point;
import functions.SqrFunction;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {
    public double[] x = {1, 2, 3, 4};
    public double[] y = {5, 6, 7, 8};
    public Object obj = new Object();
    public MathFunction sqr = new SqrFunction();
    public SynchronizedTabulatedFunction func1 = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(x, y), obj);
    public SynchronizedTabulatedFunction func2 = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(sqr, 1, 10, 10), obj);


    @Test
    public void testApply() {
        Assert.assertEquals(func1.apply(3), 7.0);
        Assert.assertEquals(func2.apply(9), 81.0);

    }

    @Test
    public void testGetCount() {
        Assert.assertEquals(func1.getCount(), 4);
        Assert.assertEquals(func2.getCount(), 10);
    }

    @Test
    public void testGetX() {
        Assert.assertEquals(func1.getX(3), 4.0);
        Assert.assertEquals(func2.getX(0), 1.0);
    }

    @Test
    public void testGetY() {
        Assert.assertEquals(func1.getY(2), 7.0);
        Assert.assertEquals(func2.getY(5), 36.0);
    }

    @Test
    public void testIndexOfX() {
        Assert.assertEquals(func1.indexOfX(2.0), 1);
        Assert.assertEquals(func2.indexOfX(4.0), 3);
    }

    @Test
    public void testIndexOfY() {
        Assert.assertEquals(func1.indexOfY(8.0), 3);
        Assert.assertEquals(func2.indexOfY(16.0), 3);
    }

    @Test
    public void testLeftBound() {
        Assert.assertEquals(func1.leftBound(), 1.0);
        Assert.assertEquals(func2.leftBound(), 1.0);
    }

    @Test
    public void testRightBound() {
        Assert.assertEquals(func1.rightBound(), 4.0);
        Assert.assertEquals(func2.rightBound(), 10.0);
    }

    @Test
    public void testIterator() {

    }

    @Test
    public void testSetY() {
        func1.setY(1, 5.5);
        func2.setY(5, 6.6);

        Assert.assertEquals(func1.getY(1), 5.5);
        Assert.assertEquals(func2.getY(5), 6.6);

    }
}