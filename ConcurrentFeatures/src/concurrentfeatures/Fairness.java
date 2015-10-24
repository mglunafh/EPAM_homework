package concurrentfeatures;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import syncproblems.Utils;

import static syncproblems.Utils.sleep;

/**
 *
 * @author dev64
 */
public class Fairness {

    static class Runner implements Runnable {
        private final Condition cond;
        private final Lock lock;
        
        
        
        public Runner(Lock lock, Condition cond) {
            this.lock = lock;
            this.cond = cond;
        }
        
        @Override 
        public void run() {
            
            lock.lock();
            
            System.out.println(Thread.currentThread().getName());
            try {
                cond.awaitUninterruptibly(); // позволяет ждать сигнала
                                            // не просыпаясь от прерываний >_<
                System.out.println(Thread.currentThread().getName() + " awoke");
            } finally {
                lock.unlock();
            }
            
        }
    }
    
    
    public static void main(String[] args) {
        
        Lock lock = new ReentrantLock();
        Condition cond = lock.newCondition(); // <- условие тесно связано с локом

        Lock fairLock = new ReentrantLock(true);
        
        int n = 15;
        Runner[] runners = new Runner[n];
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            runners[i] = new Runner(lock, cond);
            threads[i] = new Thread(runners[i], "runner " + i);
            
            threads[i].start();
        }
        
        Utils.sleep(2);
        
        
        // нужно сначала захватить лок, потом сигналить
        // Всё как с мониторами
        lock.lock();
        try {
            cond.signalAll();
        
        }finally {
            lock.unlock();
        }
    }
}
