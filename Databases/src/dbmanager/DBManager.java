package dbmanager;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev64
 */
public class DBManager {

    public static<T extends DBEntity> String getTableName(Class<T> clazz) {
        
        String result = null;
        try {
            result = clazz.newInstance().getTableName();
        } catch (InstantiationException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Field field = clazz.getField("tableName");
            System.out.println(field.getType());
            
            result = (String)field.get(null);
            
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    
    
    public static void main(String[] args) {
        
        System.out.println(getTableName(Student.class));
    }
    
}
