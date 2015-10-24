package syncproblems;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * В этом примере дополнительный поток thread невозможно остановить, прервать, 
 * вывести из состояния, в котором он находится. Он ждёт, пока монитор освободится,
 * и с этим ничего не поделать
 * 
 * @author dev64
 */
public class SyncProblems {

    public static void main(String[] args) {
        
        Object mutex = new Object();
        
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                
                System.out.println("Running");
                synchronized(mutex) {
                    System.out.println("Synched in");
                    
                    try {
                        mutex.wait();
                    } catch (InterruptedException ex) {
                        System.out.println("interrupted");
                    }
                    System.out.println("Woke up");
                }
                System.out.println("Synched out");
            }
        });
        
        synchronized(mutex) {
            thread.start();
            while (true) {
                Utils.sleep(2);
                thread.interrupt();
                thread.stop();
            }
        }
    }
}
