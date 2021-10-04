package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CosFunctionTest {

    @Test
    public void testApplyCosFunction() {
        CosFunction num3 = new CosFunction();
        double test1 = Math.PI / 3;
        double test2 = Math.PI / 2;

        assertEquals(num3.apply(test1), 0.5000000000000001, 0.0000000000000001);
        assertEquals(num3.apply(test2), 0.0, 0.0000000000000001);
        assertEquals(num3.apply(0), 1.0, 0.000000000000001);
        assertEquals(num3.apply(Double.POSITIVE_INFINITY), Double.NaN);
        assertEquals(num3.apply(Double.NEGATIVE_INFINITY), Double.NaN);
        assertEquals(num3.apply(Double.NaN), Double.NaN);

    }
}