package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    @Test
    public void testZeroFunction() {
        ZeroFunction num7 = new ZeroFunction();
        double test1 = 8.7;
        assertEquals(num7.apply(test1), 0.0);
        assertEquals(num7.apply(3.14), 0.0);
        assertEquals(num7.apply(Double.POSITIVE_INFINITY), 0.0, 0.000000000001);
        assertEquals(num7.apply(Double.NEGATIVE_INFINITY), 0.0, 0.000000000001);
        assertEquals(num7.apply(Double.NaN), 0.0);
    }
}