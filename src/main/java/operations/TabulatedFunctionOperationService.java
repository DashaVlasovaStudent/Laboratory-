package operations;

import functions.Point;
import functions.TabulatedFunction;

public class TabulatedFunctionOperationService {

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point iPoint : tabulatedFunction) {
            points[i] = iPoint;
            i++;
        }
        return points;
    }


}
