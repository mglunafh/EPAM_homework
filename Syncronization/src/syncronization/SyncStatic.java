package syncronization;

/**
 *
 * @author dev64
 */
public class SyncStatic {

    private static long count;
    
    synchronized public static long getCount() {
        return count;
    }
    
    // 
    synchronized public static void inc() {
        count++;
    }
    
    // works the same as method above :-O
    public static void inc(int amount) {
        synchronized (SyncStatic.class) {
            count += amount;
        }
    }
}
