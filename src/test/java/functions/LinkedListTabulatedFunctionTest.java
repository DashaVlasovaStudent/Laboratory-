package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {


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
        assertEquals(link.leftBound(), 2.0, 0.000000001);
    }

    @Test
    public void testRightBound() {
        MathFunction cos = new CosFunction();
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(cos, 2, 6, 15);
        assertEquals(link.rightBound(), 6.0, 0.0000000000001);
    }

    @Test
    public void testGetX() {// не использовать для нулевого элемента
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(link.getX(3), 7.0, 0.000000000001);
    }

    @Test
    public void testGetY() {// не использовать для нулевого элемента
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(link.getY(3), 8.0, 0.000000001);
    }

    @Test
    public void testSetY() {
        double[] xValues = {0, 3, 6, 7};
        double[] yValues = {1, 4, 7, 8};
        LinkedListTabulatedFunction link = new LinkedListTabulatedFunction(xValues, yValues);
        link.setY(1, 8.0);
        assertEquals(link.getY(1), 8.0, 0.00000000001);
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
}