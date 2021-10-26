package functions;

import exceptions.InterpolationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final MathFunction id = new IdentityFunction();
    private final MathFunction zero = new ZeroFunction();
    private final MathFunction sqr = new SqrFunction();

    private TabulatedFunction createFunctionFromIdentity() {
        return new LinkedListTabulatedFunction(id, 1, 100, 100);
    }

    private TabulatedFunction createFunctionFromZero() {
        return new LinkedListTabulatedFunction(zero, 1, 100, 100);
    }

    private TabulatedFunction createFunctionFromSqr() {
        return new LinkedListTabulatedFunction(sqr, 25, 100, 76);
    }

    @Test
    public void testGetCount() {

        MathFunction cos = new CosFunction();
        TabulatedFunction link = new LinkedListTabulatedFunction(cos, 2, 6, 15);

        assertEquals(link.getCount(), 15);

    }

    @Test
    public void testLeftBound() {
        MathFunction cos = new CosFunction();
        TabulatedFunction link = new LinkedListTabulatedFunction(cos, 2, 6, 15);
        assertEquals(link.leftBound(), 2.0, 0.0001);
    }

    @Test
    public void testRightBound() {
        MathFunction cos = new CosFunction();
        TabulatedFunction link = new LinkedListTabulatedFunction(cos, 2, 6, 15);
        assertEquals(link.rightBound(), 6.0, 0.0001);
    }

    @Test
    public void testGetX() {// не использовать для нулевого элемента
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};

        TabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(link.getX(0), 0.0, 0.0001);
        assertEquals(link.getX(1), 3.0, 0.0001);
        assertEquals(link.getX(2), 6.0, 0.0001);
        assertEquals(link.getX(3), 7.0, 0.0001);

        Assert.assertThrows(IllegalArgumentException.class, () -> link.getX(-100));
        Assert.assertThrows(IllegalArgumentException.class, () -> link.getX(100));
    }

    @Test
    public void testGetY() {// не использовать для нулевого элемента
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};

        TabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(link.getY(0), 1.0, 0.0001);
        assertEquals(link.getY(1), 4.0, 0.0001);
        assertEquals(link.getY(2), 7.0, 0.0001);
        assertEquals(link.getY(3), 8.0, 0.0001);

        Assert.assertThrows(IllegalArgumentException.class, () -> link.getY(-100));
        Assert.assertThrows(IllegalArgumentException.class, () -> link.getY(100));
    }

    @Test
    public void testSetY() {
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};
        TabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        link.setY(1, 8.0);

        assertEquals(link.getY(1), 8.0, 0.0001);

        Assert.assertThrows(IllegalArgumentException.class, () -> link.setY(-100, 10));
        Assert.assertThrows(IllegalArgumentException.class, () -> link.setY(100, 73));
    }

    @Test
    public void testIndexOfX() {
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};
        TabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(link.indexOfX(800), -1);
    }

    @Test
    public void testIndexOfY() {
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};
        TabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(link.indexOfY(800), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        MathFunction function = new SqrFunction();
        AbstractTabulatedFunction link = new LinkedListTabulatedFunction(function, 1, 10, 10);
        assertEquals(link.floorIndexOfX(10.0), 9);

        Assert.assertThrows(IllegalArgumentException.class, () -> link.floorIndexOfX(-100));
        Assert.assertThrows(IllegalArgumentException.class, () -> link.floorIndexOfX(-10));
    }

    @Test
    public void applyTest() {

        MathFunction idSqr = new CompositeFunction(createFunctionFromIdentity(), sqr);
        MathFunction sqrZero = new CompositeFunction(sqr, createFunctionFromZero());

        assertEquals(idSqr.apply(0.5), 0.25, 0.0001);
        assertEquals(idSqr.apply(-10), 100.0, 0.0001);
        assertEquals(idSqr.apply(0), 0.0, 0.0001);

        assertEquals(sqrZero.apply(10), 0.0, 0.0001);
        assertEquals(sqrZero.apply(-10), 0.0, 0.0001);

    }

    @Test
    public void tabulatedFunctionTest() {
        MathFunction sqrFunction = createFunctionFromSqr();

        assertEquals(sqrFunction.apply(30), 900.0, 0.0001);//x  внутри интервала и совпадает с одним их значений
        assertEquals(sqrFunction.apply(29.5), 870.5, 0.0001);// х внутри и между двумя значениями
        assertEquals(sqrFunction.apply(5), -395, 0.0001);// х левее
        assertEquals(sqrFunction.apply(120), 13980, 0.0001);// х правее
    }

    @Test
    public void interpolateTest() {
        MathFunction function = new SqrFunction();
        AbstractTabulatedFunction link = new LinkedListTabulatedFunction(function, 1, 10, 10);

        Assert.assertThrows(InterpolationException.class, () -> link.interpolate(5.5, 5));
        Assert.assertThrows(InterpolationException.class, () -> link.interpolate(6.9, 6));

    }

    @Test
    public void iteratorForEachTest() {
        TabulatedFunction function = createFunctionFromSqr();

        int i = 0;
        int j = 0;
        for (Point point : function){
            assertEquals(point.x, function.getX(i++), 0.0001);
            assertEquals(point.y, function.getY(j++), 0.0001);
        }
    }
}