package functions.factory;

import functions.LinkedListTabulatedFunction;
import functions.MathFunction;
import functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
    @Override
    public TabulatedFunction create (MathFunction function, double from, double to, int count){
        return new LinkedListTabulatedFunction(function,from,to,count );
    }

}
