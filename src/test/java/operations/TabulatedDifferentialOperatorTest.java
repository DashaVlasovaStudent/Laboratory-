package operations;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {

    @Test
    public void testDerive() {
        double[] xValues = new double[]{ 1, 2, 3};
        double[] yValues = new double[]{ 4, 5, 6};

        TabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction listFunction = listFactory.create(xValues, yValues);
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(listFactory);
        TabulatedFunction listFunctionDerived = operator.derive(listFunction);

        for (int i=0; i< xValues.length; i++){
            Assert.assertEquals(listFunction.getX(i), (double)i+1 );
            Assert.assertEquals(listFunction.getY(i), (double)i+4 );
        }





    }
}