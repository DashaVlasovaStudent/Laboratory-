package functions;



import static org.testng.Assert.*;

public class IdentityFunctionTest {
    public void testApply(){
        IdentityFunction num1 = new IdentityFunction();
        double test1 = 2.5;
        double test2 = 100.9;
        assertEquals(num1.apply(test1), test1);
        assertEquals(num1.apply(test2), test2);
        assertEquals(num1.apply(23.8), 23.8);
        assertEquals(num1.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals(num1.apply(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
        assertEquals(num1.apply(Double.NaN), Double.NaN);
    }
}