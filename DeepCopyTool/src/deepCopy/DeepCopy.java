package deepCopy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev64
 */
public class DeepCopy {
    
    public static Object deepCopy(Object ob) {
        if (!(ob instanceof Serializable))
            return null;
        
        
        Object copy = null;
        
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {    
            oos.writeObject(ob);
        } 
         catch (IOException ex) {
            System.out.println("We got unexpected problems with underlying stream");
            ex.printStackTrace();           
        }
        
        byte[] array = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(array);
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            copy = ois.readObject();
            
        } catch (IOException ex) {
            System.out.println("Problems with underlying stream during reading phase");
        } catch (ClassNotFoundException ex) {
            System.out.println("Hmmm, somehow we have lost the definition" +
                    "of the class object of we have got to copy");
            ex.printStackTrace();
        }
        
        return copy;
    }
}
