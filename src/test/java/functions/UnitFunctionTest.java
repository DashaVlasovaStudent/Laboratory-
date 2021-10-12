package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {

    @Test
    public void testUnitFunction() {
        UnitFunction num8 = new UnitFunction();
        double test1 = 8.7;
        assertEquals(num8.apply(test1), 1.0, 0.0001);
        assertEquals(num8.apply(3.14), 1.0, 0.0001);
        assertEquals(num8.apply(Double.POSITIVE_INFINITY), 1.0, 0.0001);
        assertEquals(num8.apply(Double.NEGATIVE_INFINITY), 1.0, 0.0001);
        assertEquals(num8.apply(Double.NaN), 1.0, 0.0001);
    }
}