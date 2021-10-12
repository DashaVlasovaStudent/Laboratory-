package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApplySqrFunction() {
        SqrFunction sqr = new SqrFunction();
        double test1 = 2.5;

        assertEquals(sqr.apply(test1), Math.pow(test1, 2), 0.0001);
        assertEquals(sqr.apply(23.8), 566.44, 0.0001);
        assertEquals(sqr.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals(sqr.apply(Double.NEGATIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals(sqr.apply(Double.NaN), Double.NaN);

    }
}