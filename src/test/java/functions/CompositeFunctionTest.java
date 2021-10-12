package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        SqrFunction sqrFunc = new SqrFunction();
        CosFunction cosFunc = new CosFunction();
        IdentityFunction idFunc = new IdentityFunction();

        CompositeFunction idSqrFunction = new CompositeFunction(idFunc, sqrFunc);
        CompositeFunction sqrCosFunction = new CompositeFunction(sqrFunc, cosFunc);
        CompositeFunction idCosFunction = new CompositeFunction(idFunc, cosFunc);
        CompositeFunction idSqrSqrCosFunction = new CompositeFunction(sqrCosFunction, idSqrFunction);

        double test = 0.56;
        double testX = 0.1;

        assertEquals(sqrCosFunction.apply(test), 0.95122919, 0.0001);
        assertEquals(idSqrSqrCosFunction.apply(testX), 0.999900003, 0.0001);
        assertEquals(sqrCosFunction.apply(Double.POSITIVE_INFINITY), Double.NaN);
        assertEquals(sqrCosFunction.apply(Double.NEGATIVE_INFINITY), Double.NaN);
        assertEquals(sqrCosFunction.apply(Double.NaN), Double.NaN);
    }

}