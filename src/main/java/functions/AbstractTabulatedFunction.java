package functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double tempX, double tempX1, double tempY, double tempY1) {
        return tempY + ((tempY1 - tempY) * (x - tempX)) / (tempX1 - tempX);
    }

    protected abstract int floorIndexOfX(double x);


    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()){
            return extrapolateRight(x);
        }
        if (indexOfX(x) != -1){
            return getY(indexOfX(x));
        }
        return  interpolate(x, floorIndexOfX(x));
    }


}
