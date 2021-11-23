package conccurent;

import functions.TabulatedFunction;

public class ReadWriteTask implements Runnable {

    private TabulatedFunction tabulatedFunction;

    public ReadWriteTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < tabulatedFunction.getCount(); i++){
            x = tabulatedFunction.getX(i);
            y = tabulatedFunction.getY(i);
            System.out.format("%s, before write: i=%d, x = %f, y=%f", Thread.currentThread().getName(), i, x, y);
            tabulatedFunction.setY(i, ++y);
            y = tabulatedFunction.getY(i);
            System.out.format("%s, after write: i=%d, x = %f, y=%f", Thread.currentThread().getName(), i, x, y);
        }
    }

}
