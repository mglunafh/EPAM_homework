package deepCopy;

import java.io.IOException;

/**
 *
 * @author dev64
 */
public class Main {

    public static void main(String[] arg) throws IOException, ClassNotFoundException {
        
        SomeEntity a = new SomeEntity(1, "15", 60);
        SomeEntity b = (SomeEntity) DeepCopy.deepCopy(a);
        
        System.out.println("copy of serializable object: " + b);
        
        BadEntity a1 = new BadEntity();
        BadEntity b1 = (BadEntity) DeepCopy.deepCopy(a1);
        
        System.out.println("copy of non-serializable object: " + b1);
        
    }
    
}
