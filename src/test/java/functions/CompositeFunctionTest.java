package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        SqrFunction sqrFunc = new SqrFunction();
        CosFunction cosFunc = new CosFunction();
        IdentityFunction idFunc = new IdentityFunction();

        CompositeFunction identitySqrFunction = new CompositeFunction(idFunc, sqrFunc);
        CompositeFunction sqrCosFunction = new CompositeFunction(sqrFunc, cosFunc);
        CompositeFunction identitySqrSqrCosFunction = new CompositeFunction(sqrCosFunction, identitySqrFunction);

        double test = 0.56;
        double testX = 0.1;

        assertEquals(sqrCosFunction.apply(test), 0.95122919, 0.0001);
        assertEquals(identitySqrSqrCosFunction.apply(testX), 0.999900003, 0.0001);
        assertEquals(sqrCosFunction.apply(Double.POSITIVE_INFINITY), Double.NaN);
        assertEquals(sqrCosFunction.apply(Double.NEGATIVE_INFINITY), Double.NaN);
        assertEquals(sqrCosFunction.apply(Double.NaN), Double.NaN);
    }

}