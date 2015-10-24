package workerthread;

import java.util.Random;
import syncronization.Utils;

/**
 *
 * @author dev64
 */
public class UseOfBlockingQueue {
    
    static Random rand = new Random();
    
    
    static class Producer implements Runnable {
        private final BlockQueue<Integer> queue;

        public Producer(BlockQueue<Integer> queue) {
            this.queue = queue;
        }
        
        @Override
        public void run() {
            while(true) {
                Utils.sleep(1 + rand.nextInt(3));
                int t = rand.nextInt(50);
                queue.put(t);
                
                System.out.printf("produced %d, total is %d\n", t, queue.size());
            }
        }
    }
    
    
    static class Consumer implements Runnable {
        private final BlockQueue<Integer> queue;

        public Consumer(BlockQueue<Integer> queue) {
            this.queue = queue;
        }
        
        @Override
        public void run() {
            while(true) {
                Utils.sleep(1 + rand.nextInt(5));
                int t = queue.take();
                System.err.printf("Consumed %d, total is %d\n", t, queue.size());
            }
        }
    }
    
    public static void main(String[] args) {
        
        BlockQueue<Integer> queue = new BlockQueue<>();
        
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        
        new Thread(consumer).start();
        new Thread(producer).start();
        
    }
}
