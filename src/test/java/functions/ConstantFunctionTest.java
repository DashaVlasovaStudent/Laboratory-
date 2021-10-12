package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testApply() {
        ConstantFunction num6 = new ConstantFunction(5);
        double test1 = 8.7;
        assertEquals(num6.apply(test1), 5.0, 0.0001);
        assertEquals(num6.apply(3.14), 5.0, 0.0001);
        assertEquals(num6.apply(Double.POSITIVE_INFINITY), 5.0, 0.0001);
        assertEquals(num6.apply(Double.NEGATIVE_INFINITY), 5.0, 0.0001);
        assertEquals(num6.apply(Double.NaN), 5.0, 0.0001);
    }
}