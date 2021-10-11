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


    MathFunction id = new IdentityFunction();
    MathFunction zero = new ZeroFunction();
    MathFunction sqr = new SqrFunction();
    MathFunction cos = new CosFunction();



    private LinkedListTabulatedFunction function1() {
        return new LinkedListTabulatedFunction(id, 3,57,10);
    }

    private LinkedListTabulatedFunction function2(){
        return new LinkedListTabulatedFunction(zero, 21, 50,8);
    }

    private LinkedListTabulatedFunction function3(){
        return new LinkedListTabulatedFunction(sqr, 20, 500,20);
    }

    private LinkedListTabulatedFunction function4(){
        return new LinkedListTabulatedFunction(cos, 0, 20, 8);
    }



    @Test
    public void applyTest(){
        MathFunction firstFunc = function1();
        MathFunction secondFunc = function2();
        MathFunction thirdFunc = function3();
        MathFunction fourthFunc = function4();

        CompositeFunction cosSqr = new CompositeFunction(cos, sqr);
        CompositeFunction idSqr = new CompositeFunction(id, sqr);
        CompositeFunction sqrZero = new CompositeFunction(sqr, zero);


        assertEquals(cosSqr.apply((Math.PI)/3), 0.25, 0.00000000000001);
        assertEquals(cosSqr.apply((Math.PI)/2), 0.0, 0.00000000000001);
        assertEquals(cosSqr.apply(100), 0.7435938375035028, 0.00000000000001);// cos принимает значения в радианах

        assertEquals(idSqr.apply(10), 100.0, 0.00000000000001);
        assertEquals(idSqr.apply(0), 0.0, 0.00000000000001);
        assertEquals(idSqr.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY, 0.00000000000001);
        assertEquals(idSqr.apply(Double.NEGATIVE_INFINITY), Double.POSITIVE_INFINITY, 0.00000000000001);
        assertEquals(idSqr.apply(Double.NaN), Double.NaN, 0.00000000000001);

        assertEquals(sqrZero.apply(10), 0.0, 0.00000000000001);
        assertEquals(sqrZero.apply(Double.NEGATIVE_INFINITY), 0.0, 0.00000000000001);






    }
}