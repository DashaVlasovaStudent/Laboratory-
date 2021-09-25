package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApplySqrFunction() {
        SqrFunction num2 = new SqrFunction();
        double test1 = 2.5;

        assertEquals(num2.apply(test1), Math.pow(test1, 2));
        assertEquals(num2.apply(23.8), 566.44);

    }
}