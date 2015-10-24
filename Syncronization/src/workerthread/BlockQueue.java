package workerthread;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author dev64
 */
public class BlockQueue<T> {

    private Queue<T> queue = new LinkedList<>();
    
    public void put(T element) {
        synchronized(queue) {
            queue.offer(element);
            queue.notify();
        }
    }
    
    public int size() {
        return queue.size();
    }
    
    public T take() {
        synchronized(queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException ex) {
                    // ignore
                }
            }
            return queue.poll();
        }
    }
    
}
