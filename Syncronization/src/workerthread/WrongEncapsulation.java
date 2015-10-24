package workerthread;


import java.util.Vector;
import syncronization.Utils;
import static syncronization.Utils.sleep;
/**
 *
 * @author dev64
 */
public class WrongEncapsulation {

    public static void main(String[] args) {

        Vector<String> vector = new Vector<>();


        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    vector.addElement("one");
                    System.out.println(vector);
                    Utils.sleep(1);
                    vector.removeElementAt(0);
                    System.out.println(vector); 
                    Utils.sleep(1);
                }
            }
            
        }).start();
        
        Utils.sleep(5);
        while (true) {
            synchronized(vector) {
                System.out.println("kek");
                Utils.sleep(1);

            }
        }
        
    }
    
}
