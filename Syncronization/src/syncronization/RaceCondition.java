package syncronization;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev64
 */
public class RaceCondition {
    
    public static int count = 0;
    static Object mutex = new Object();
    
    
    static class Counter implements Runnable {

        @Override
        public void run() {
            
            while(true) {
                count++;
                System.out.println(count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RaceCondition.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    static class AdvancedCounter implements Runnable {
        
        @Override
        public void run() {
            while(true) {            
                inc();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RaceCondition.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        private void inc() {
            synchronized(mutex) {
                count++;
                System.out.println(count);
            }
        }
    }
    
    public static void main(String[] args) {
        
        AdvancedCounter c = new AdvancedCounter();
        Counter c1 = new Counter();
        
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        
        t1.start();
        t2.start();
    }
}
