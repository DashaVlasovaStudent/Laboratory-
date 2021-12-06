package conccurent;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.ZeroFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ZeroFunction(), 1,10, 10);
        List <Thread> threads = new ArrayList<>();
        ReadWriteTask task = new ReadWriteTask(function);

        for (int i = 0; i< 20; i++){
            threads.add (new Thread(task));
        }
        for (Thread thread: threads){
            thread.start();
        }
        Thread.sleep(2500);
        System.out.println(function);
    }
}
