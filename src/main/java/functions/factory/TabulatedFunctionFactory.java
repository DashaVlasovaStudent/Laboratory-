package functions.factory;

import functions.MathFunction;
import functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
    TabulatedFunction create (MathFunction function, double from, double to, int count);
}
