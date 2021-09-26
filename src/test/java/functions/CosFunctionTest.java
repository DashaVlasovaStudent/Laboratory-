package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CosFunctionTest {

    @Test
    public void testApplyCosFunction() {
        CosFunction num3 = new CosFunction();
        double test1 = Math.PI/3;

        assertEquals(num3.apply(test1), 0.5000000000000001, 0.0000000000000001);
        assertEquals(num3.apply(0), 1.0 );

    }
}