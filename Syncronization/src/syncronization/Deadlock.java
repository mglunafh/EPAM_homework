package syncronization;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev64
 */
public class Deadlock {

    static class  Human {}
    
    static class Ogre {
        
        private Human human1;
        private Human human2;
        public Ogre (Human man1, Human man2) {
            human1 = man1;
            human2 = man2;
        }
        
        public void snack() {
            synchronized(human1) {
                eatLimb(human1);
                synchronized(human2) {
                    eatLimb(human2);
                }
            }
        }
        
        private void eatLimb(Human human) {
            try {
                System.out.println("Ogre " + this + " eats limb of " + human);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Ogre " + this + " finished eating limb of " + human);
            } catch (InterruptedException ex) {
                Logger.getLogger(Deadlock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static void main(String[] args) {
        
        Human h1 = new Human();
        Human h2 = new Human();
        
        Ogre ogre1 = new Ogre(h1, h2);
        Ogre ogre2 = new Ogre(h1, h2);
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                ogre1.snack();
            }
        }).start();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                ogre2.snack();
            }
        }).start();
    }
    
}
