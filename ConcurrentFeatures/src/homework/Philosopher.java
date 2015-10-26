package homework;

/**
 *
 * @author dev64
 */
public class Philosopher implements Runnable {

    private boolean rightStickTakenFirst;
    private Object leftStick;
    private Object rightStick;
    
    
    public Philosopher(boolean rightFirst, Object leftStick, Object rightStick) {
        this.rightStickTakenFirst = rightFirst;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
    }

    @Override
    public void run() {
        
        
        
    }
    
}
