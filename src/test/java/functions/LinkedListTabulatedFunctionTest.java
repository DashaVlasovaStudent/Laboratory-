package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    public MathFunction id = new IdentityFunction();
    public MathFunction zero = new ZeroFunction();
    public MathFunction sqr = new SqrFunction();
    public MathFunction cos = new CosFunction();

    private LinkedListTabulatedFunction createFunctionFromIdentity() {
        return new LinkedListTabulatedFunction(id, 1, 100, 100);
    }

    private LinkedListTabulatedFunction createFunctionFromZero() {
        return new LinkedListTabulatedFunction(zero, 1, 100, 100);
    }

    private LinkedListTabulatedFunction createFunctionFromSqr() {
        return new LinkedListTabulatedFunction(sqr, 1, 500, 20);
    }

    @Test
    public void testGetCount() {

        MathFunction cos = new CosFunction();
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(cos, 2, 6, 15);

        assertEquals(link.getCount(), 15);

    }

    @Test
    public void testLeftBound() {
        MathFunction cos = new CosFunction();
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(cos, 2, 6, 15);
        assertEquals(link.leftBound(), 2.0, 0.0001);
    }

    @Test
    public void testRightBound() {
        MathFunction cos = new CosFunction();
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(cos, 2, 6, 15);
        assertEquals(link.rightBound(), 6.0, 0.0001);
    }

    @Test
    public void testGetX() {// не использовать для нулевого элемента
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};

        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(link.getX(0), 0.0, 0.0001);
        assertEquals(link.getX(1), 3.0, 0.0001);
        assertEquals(link.getX(2), 6.0, 0.0001);
        assertEquals(link.getX(3), 7.0, 0.0001);
    }

    @Test
    public void testGetY() {// не использовать для нулевого элемента
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};

        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(link.getY(0), 1.0, 0.0001);
        assertEquals(link.getY(1), 4.0, 0.0001);
        assertEquals(link.getY(2), 7.0, 0.0001);
        assertEquals(link.getY(3), 8.0, 0.0001);
    }

    @Test
    public void testSetY() {
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        link.setY(1, 8.0);
        assertEquals(link.getY(1), 8.0, 0.0001);
    }

    @Test
    public void testIndexOfX() {
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(link.indexOfX(800), -1);
    }

    @Test
    public void testIndexOfY() {
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(link.indexOfY(800), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        double[] xValues = {10, 15, 17, 20};
        double[] yValues = {1, 4, 7, 8};
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(link.floorIndexOfX(6.9), 0);// x меньше всех элементов
        assertEquals(link.floorIndexOfX(800.9), 4);// х больше всех элементов
        assertEquals(link.floorIndexOfX(13.2), 0);
    }

    @Test
    public void applyTest() {

        CompositeFunction idSqr = new CompositeFunction(createFunctionFromIdentity(), sqr);
        CompositeFunction sqrZero = new CompositeFunction(sqr, createFunctionFromZero());

        assertEquals(idSqr.apply(0.5), 0.25, 0.0001);
        assertEquals(idSqr.apply(-10), 100.0, 0.0001);
        assertEquals(idSqr.apply(0), 0.0, 0.0001);

        assertEquals(sqrZero.apply(10), 0.0, 0.0001);
        assertEquals(sqrZero.apply(-10), 0.0, 0.0001);
    }
}