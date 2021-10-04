package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApplySqrFunction() {
        SqrFunction num2 = new SqrFunction();
        double test1 = 2.5;

        assertEquals(num2.apply(test1), Math.pow(test1, 2), 0.000000000000001);
        assertEquals(num2.apply(23.8), 566.44, 0.00000000000001);
        assertEquals(num2.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals(num2.apply(Double.NEGATIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals(num2.apply(Double.NaN), Double.NaN);

    }
}