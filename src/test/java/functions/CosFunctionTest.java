package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CosFunctionTest {

    @Test
    public void testApplyCosFunction() {
        CosFunction cos = new CosFunction();
        double test1 = Math.PI / 3;
        double test2 = Math.PI / 2;

        assertEquals(cos.apply(test1), 0.5, 0.0001);
        assertEquals(cos.apply(test2), 0.0, 0.0001);
        assertEquals(cos.apply(0), 1.0, 0.0001);
        assertEquals(cos.apply(Double.POSITIVE_INFINITY), Double.NaN);
        assertEquals(cos.apply(Double.NEGATIVE_INFINITY), Double.NaN);
        assertEquals(cos.apply(Double.NaN), Double.NaN);

    }
}