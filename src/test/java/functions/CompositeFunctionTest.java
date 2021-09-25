package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        SqrFunction SqrFunc = new SqrFunction();
        CosFunction CosFunc = new CosFunction();
        IdentityFunction IdFunc = new IdentityFunction();

        CompositeFunction Func1 = new CompositeFunction(SqrFunc, CosFunc);
        CompositeFunction Func2 = new CompositeFunction(IdFunc, SqrFunc);

        CompositeFunction num4 = new CompositeFunction(SqrFunc, CosFunc);
        CompositeFunction num5 = new CompositeFunction(Func1, Func2);
        double test = 0.56;
        double testX = 0.1;
        assertEquals(num4.apply(test), 0.95122919, 0.000001);
        assertEquals(num5.apply(testX), 0.999900003, 0.000001);//SqrFunc(IdFunc(CosFunc(SqrFunc(x))))
        assertEquals(num4.apply(Double.POSITIVE_INFINITY), Double.NaN);
        assertEquals(num4.apply(Double.NEGATIVE_INFINITY), Double.NaN);
        assertEquals(num4.apply(Double.NaN), Double.NaN);
    }
}