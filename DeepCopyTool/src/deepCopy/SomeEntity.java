package deepCopy;

import java.io.Serializable;

/**
 *
 * @author dev64
 */
public class SomeEntity implements Serializable {

    private int intData;
    private String stringData;
    private double doubleData;
    
    public SomeEntity(int i, String str, double f) {
        intData = i;
        stringData = str;
        doubleData = f;
    }
    
    @Override
    public String toString() {
        return (String.format("Entity: %d %s %f", intData, stringData, doubleData));
    }
    
}
