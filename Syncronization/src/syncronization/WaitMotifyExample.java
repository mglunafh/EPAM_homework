package syncronization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev64
 */
public class WaitMotifyExample {
    
    static class Runner implements Runnable {

        private Object trigger;
        private String name;
        
        public Runner(Object trigger, String name) {
            this.trigger = trigger;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return name;
        }
        
        @Override
        public void run() {
            
            synchronized(trigger) {
                try {
                    trigger.wait();
                    System.out.println(this + " awoke");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            while(true) {
                Utils.sleep(1);
                System.out.println(this + " running");
            }
        }
        
    }

    public static void main(String[] args) {
        
        List<Runner> runners = new ArrayList<>();
        
        Object trigger = new Object();
        for (int i = 0; i < 5; i++) {
            runners.add(new Runner(trigger, ""+ i));
        }
        
        for (Runner r : runners) {
            new Thread(r).start();
        }
        
        Utils.sleep(1);
        
        System.out.println("Ready...");
        Utils.sleep(1);
        System.out.println("Steady..");
        Utils.sleep(1);
        System.out.println("GO");
        
        synchronized(trigger) {
            trigger.notifyAll();
            System.out.println("Notified");
            Utils.sleep(10);
        }
        
    }
}
