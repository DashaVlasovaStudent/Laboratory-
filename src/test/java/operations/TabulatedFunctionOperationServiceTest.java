package operations;

import functions.LinkedListTabulatedFunction;
import functions.Point;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    public void testAsPoints() {
        double[] xValues = new double[]{1, 2, 3};
        double[] yValues = new double[]{4, 5, 6};

        LinkedListTabulatedFunction linkFunc = new LinkedListTabulatedFunction(xValues, yValues);

        Point[] points = TabulatedFunctionOperationService.asPoints(linkFunc);
        int i = 0;
        for (Point resultPoints : linkFunc) {
            assertEquals(xValues[i], linkFunc.getX(i), 0.001);
            assertEquals(yValues[i], linkFunc.getY(i), 0.001);
        }


    }
}