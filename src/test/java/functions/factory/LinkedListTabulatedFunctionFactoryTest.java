package functions.factory;

import functions.CompositeFunction;
import functions.LinkedListTabulatedFunction;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {
    public double[] xValues = {1,2,3,4,5};
    public double[] yValues = {6,7,8,9,10};

    public LinkedListTabulatedFunctionFactory temp = new LinkedListTabulatedFunctionFactory();


    @Test
    public void testCreate() {
        assertTrue(temp.create(xValues, yValues) instanceof LinkedListTabulatedFunction);
        assertFalse(temp.create(xValues, yValues) instanceof CompositeFunction);
    }
}