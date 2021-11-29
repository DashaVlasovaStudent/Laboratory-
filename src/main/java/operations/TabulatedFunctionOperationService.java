package operations;

import exceptions.InconsistentFunctionsException;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    private LinkedListTabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = (LinkedListTabulatedFunctionFactory) factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point iPoint : tabulatedFunction) {
            points[i] = iPoint;
            i++;
        }
        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperator(TabulatedFunction firstFunc, TabulatedFunction secondFunc, BiOperation operation) {
        if (firstFunc.getCount() != secondFunc.getCount()) {
            throw new InconsistentFunctionsException("The functions' sizes are not the same!");
        }

        Point[] firstFuncPoints = asPoints(firstFunc);
        Point[] secondFuncPoints = asPoints(secondFunc);
        double[] xValues = new double[firstFunc.getCount()];
        double[] yValues = new double[secondFunc.getCount()];

        for (int i = 0; i < firstFunc.getCount(); i++) {
            if (firstFuncPoints[i].x != secondFuncPoints[i].x) {
                throw new InconsistentFunctionsException(" The x values of functions are different!");
            }
            xValues[i] = firstFuncPoints[i].x;
            yValues[i] = operation.apply(firstFuncPoints[i].y, secondFuncPoints[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction sum(TabulatedFunction firstFunc, TabulatedFunction secondFunc) {
        return doOperator(firstFunc, secondFunc, (first, second) -> first + second);
    }

    public TabulatedFunction subtract(TabulatedFunction firstFunc, TabulatedFunction secondFunc) {
        return doOperator(firstFunc, secondFunc, (first, second) -> first - second);
    }

    public TabulatedFunction multiplication(TabulatedFunction firstFunc, TabulatedFunction secondFunc) {
        return doOperator(firstFunc, secondFunc, (first, second) -> first * second);
    }

    public TabulatedFunction divide(TabulatedFunction firstFunc, TabulatedFunction secondFunc) {
        return doOperator(firstFunc, secondFunc, (first, second) -> first / second);
    }


}
