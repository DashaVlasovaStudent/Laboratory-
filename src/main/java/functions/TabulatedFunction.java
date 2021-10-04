package functions;

public interface TabulatedFunction extends MathFunction {
    int getCount();// количество табулированных значений

    double getX(int index);

    double getY(int index);

    void setY(int index, double value);

    int indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();

}
