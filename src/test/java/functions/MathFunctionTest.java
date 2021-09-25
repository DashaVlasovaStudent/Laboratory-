package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {


    @Test
    public void testAndThen() {
        double test = 0.1;
        SqrFunction func1 = new SqrFunction();
        CosFunction func2 = new CosFunction();
        MathFunction composite = func1.andThen(func2);
        assertEquals(composite.apply(test), 0.99995, 0.000001);
        assertEquals(composite.apply(Double.POSITIVE_INFINITY), Double.NaN);
        assertEquals(composite.apply(Double.NEGATIVE_INFINITY), Double.NaN);
        assertEquals(composite.apply(Double.NaN), Double.NaN);
    }
}