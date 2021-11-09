package operations;

import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    public TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public double apply(double x) {
        return x;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        TabulatedFunctionOperationService servicePoint = new TabulatedFunctionOperationService();
        Point[] points = servicePoint.asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];

        for (int i = 0; i < points.length - 1; i++) {
            xValues[i] = points[i].x;
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }
        yValues[yValues.length - 1] = yValues[yValues.length - 2];
        return factory.create(xValues, yValues);
    }
}
