package syncronization;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev64
 */
public class FinalLocalVars {

    public static void main(String[] args) {
        
        // должно быть с идентификатором final 
        // иначе использование икса во внутреннем классе будет невозможно.
        int x = 10;
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("x = " + x);
//                    x += 10;                         // <-- захват лок. пемеренной
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(FinalLocalVars.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        
        System.out.println("finished");
    }
}
