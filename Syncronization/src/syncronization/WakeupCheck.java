package syncronization;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev64
 */
public class WakeupCheck {

    public static void main(String[] args) {
        
        final Object starter = "starter";

        class Runner implements Runnable {
            
            volatile boolean started;       // <-- меняется и считывается
                                            // только в крит.секциях
                                            // volatile можно опустить

            @Override
            public void run() {
                synchronized(starter) { // <-- starter locked
                    while (!started) {
                        System.out.println(Thread.currentThread() + "is waiting");
                        try {
                            starter.wait(); // <-- starter released again
                        } catch (InterruptedException ex) {
                            System.err.println("woke up, but are we started?");
                        }
                    }
                }
                
                System.out.println("Running :-O");
            }
            
            public void startRunning() {
                
                synchronized(starter) {
                    started = true;
                    starter.notify();
                }
            }
        }
    
        Runner runner = new Runner();
        
        Thread thread = new Thread(runner);
        thread.start();
        
        Utils.sleep(2);
        thread.interrupt();
        Utils.sleep(1);
        runner.startRunning();
        
    }
}
