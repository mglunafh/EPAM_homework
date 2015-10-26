package homework;

import java.util.Random;
import syncproblems.Utils;

/**
 *
 * @author dev64
 */
public class Dinner {

    public static void syso(String s) {
        System.out.println(s);
    }
    
    
    public static void main(String[] args) {
        
        Random rand = new Random();
        
        Object[] sticks = new Object[5];
        for (int i = 0; i < sticks.length; i++) {
            sticks[i] = new Object();
        }
        
        final int minEatingTime = 2;
        final int maxDinnerDuration = 5;
        
        final int minWaitingTime = 5;
        final int maxWaitingDuration = 5;
                
        int RIPTime = 20;
        
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            int j = i;
            threads[i] = new Thread(new Runnable() {

                @Override
                public void run() {
               
                    while(true) {
                        syso("Philosopher " + j + " is sleeping...\n");
                        Utils.sleep(minWaitingTime + rand.nextInt(maxWaitingDuration));
                        
                        int first = j < 4 ? j : 0;
                        int second = j < 4 ? j + 1 : j;
                        
                        syso("Philosopher " + j + " is trying to take " + first);
                        synchronized(sticks[first]) {
                            syso("Philosopher " + j + " is trying to take " + second);
                            synchronized(sticks[second]) {
                                syso("Philosopher " + j + " is eating now...");
                                Utils.sleep(minEatingTime + rand.nextInt(maxDinnerDuration));
                            }
                        }
                    }
                }
                
            });
            threads[i].start();
        }
        
    }
    
}
