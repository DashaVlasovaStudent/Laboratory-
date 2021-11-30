package operations;

import functions.LinkedListTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    public void testAsPoints() {
        double[] xValues = new double[]{1, 2, 3};
        double[] yValues = new double[]{4, 5, 6};
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction linkFunc = factory.create(xValues, yValues);

        Point[] points = TabulatedFunctionOperationService.asPoints(linkFunc);
        int i = 0;
        for (Point resultPoints : points) {
            assertEquals(resultPoints.x, linkFunc.getX(i), 0.001);
            assertEquals(resultPoints.y, linkFunc.getY(i++),  0.001);
        }


    }

    @Test
    public void sumTest() {
        double[] xValues = new double[]{1, 2, 3};
        double[] yValues = new double[]{4, 5, 6};
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunctionFactory linkedFactory1 = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction res = service.sum(linkedFactory1.create(xValues,yValues), linkedFactory1.create(xValues,yValues));

        for (int i=0; i<3; i++){
            Assert.assertEquals(res.getX(i), (double) i+1);
        }

        Assert.assertEquals(res.getY(0),  8.0);
        Assert.assertEquals(res.getY(1),  10.0);
        Assert.assertEquals(res.getY(2),  12.0);
        Assert.assertTrue(res instanceof LinkedListTabulatedFunction);

    }
    @Test
    public void subtractTest() {
        double[] xValues = new double[]{1, 2, 3};
        double[] yValues = new double[]{4, 5, 6};
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunctionFactory linkedFactory1 = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedFactory2 = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction res = service.subtract(linkedFactory1.create(xValues,yValues), linkedFactory2.create(xValues,yValues));

        for (int i=0; i<3; i++){
          Assert.assertEquals(res.getX(i), (double) i+1);
         }

        Assert.assertEquals(res.getY(0),  0.0);
        Assert.assertEquals(res.getY(1),  0.0);
        Assert.assertEquals(res.getY(2),  0.0);
        Assert.assertTrue(res instanceof LinkedListTabulatedFunction);

    }
}