package conccurent;

import functions.Point;
import functions.TabulatedFunction;
import java.util.Iterator;


public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction function;
    private final Object object;

    public SynchronizedTabulatedFunction(TabulatedFunction function, Object object) {
        this.function = function;
        this.object = object;
    }


    @Override
    public double apply(double x) {
        synchronized (object) {
            return function.apply(x);
        }
    }

    @Override
    public int getCount() {
        synchronized (object) {
            return function.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (object) {
            return function.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (object) {
            return function.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (object) {
            function.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (object) {
            return function.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (object) {
            return function.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (object) {
            return function.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (object) {
            return function.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (object) {
           return function.iterator();
        }
    }
}
