package concurrentfeatures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author dev64
 */
public class BlockQueue<T> {

    private Queue<T> queue = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private int capacity;
    
    public BlockQueue(int max) {
        capacity = max;
    }
    
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.awaitUninterruptibly();
            }
            notFull.signal();       // очередь как бы еще полная, полл не сделан,
                                    // но положить туда можно будет только после
                                    // выхода из критической секции
                                    // поэтому посылка сигнала до удаления элемента
                                    // легитимна.
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }
    
    public void put(T item) {
        lock.lock();
        
        try {
            while (capacity <= queue.size()) {
                notFull.awaitUninterruptibly();
            }
            queue.offer(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public int size() {
        return queue.size();
    }
    
}
