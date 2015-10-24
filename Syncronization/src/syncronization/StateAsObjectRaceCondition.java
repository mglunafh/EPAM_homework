package syncronization;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev64
 */
public class StateAsObjectRaceCondition {
    
    static class A {
        
        int x = 0;
        int y = 100;
    }

    // Хотя и методы get и change синхронизированы, не синхронизирован доступ 
    // к полям объескта А, поэтому, единожды получив ссылку на объект А,
    // можно сколько угодно раз менять его сотояние.
    static class B {
        

        A a = new A();
        
        synchronized public A get() {
            return a;
        }
        
        synchronized public void change(int amount) {
            try {
                System.out.println("change start");
                TimeUnit.SECONDS.sleep(3);
                a.x += amount;
                TimeUnit.SECONDS.sleep(3);
                a.y -= amount;
                TimeUnit.SECONDS.sleep(3);
                System.out.println("change end");
            } catch (InterruptedException ex) {
                Logger.getLogger(StateAsObjectRaceCondition.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        
        B b = new B();
        
        A a = b.get();
        
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    try {
                        System.out.printf("a.x = %d a.y = %d\n", a.x, a.y);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StateAsObjectRaceCondition.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
        b.change(15);
    } 
    
}
