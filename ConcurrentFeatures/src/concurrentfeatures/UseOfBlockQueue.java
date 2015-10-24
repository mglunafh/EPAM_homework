package concurrentfeatures;


import syncproblems.Utils;
import static syncproblems.Utils.sleep;


/**
 *
 * @author dev64
 */
public class UseOfBlockQueue {

    
    public static void main(String[] args) {
        
        final BlockQueue<Integer> queue = new BlockQueue<>(3);
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                
                for (int i = 0; i < 6; i++) {
                    queue.put(i);
                    Utils.sleep(1);
                    System.out.println("putting");
                }
            }
        }).start();
        
        Utils.sleep(5);
        do {
            Utils.sleep(3);
            System.out.println("taking "+ queue.take());
        } while (queue.size() > 0);
    }
}
