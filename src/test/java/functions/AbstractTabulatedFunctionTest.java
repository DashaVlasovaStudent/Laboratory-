package functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    @Test
    public void testTestToString() {
        double[] xValues = {23, 88, 102};
        double[] yValues = {54, 67, 89};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues,yValues);

        Assert.assertEquals(function.toString(), "LinkedListTabulatedFunction size = 3\n[23.0; 54.0]\n[88.0; 67.0]\n[102.0; 89.0]\n");
    }
}