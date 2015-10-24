package concurrentfeatures;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * Для организации критических секций лучше не стоит использовать 
 * @author dev64
 */
public class Counter {

    private int count;
    
    private Object lock = new Object();
    
    private Lock lockNew = new ReentrantLock();
    
    public void inc() {
        synchronized(lock) {
            count++;
        }
    }
    
    public void incNew() {
        lockNew.lock();
        
        try {
            count++;         // try, потому что часто здесь находится более 
                             // сложный код
        } finally {
            lockNew.unlock();
        }
    }
    
    public int get() {
        synchronized(lock) {
            return count;
        }
    }
    
    public int getNew() {
        
        lockNew.lock();
        
        try {
            return count;
        } finally {
            lockNew.unlock();
        }
    }
}
