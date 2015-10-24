package concurrentfeatures;

/**
 *
 * Мьютексы реентерабельны. Вызов а() по факту приводит к двойному входу в 
 * крититечкую секцию  по монитору this, но это не приводит к блокировке.
 * 
 * Этим же же свойством обладают объекты класса Lock.
 * 
 * @author dev64
 */
public class Reentrant {

    void a() {
        synchronized(this)  {
            b(); 
        }
    }
    
    
    void b() {
        synchronized(this) { 
        System.out.println("inside b");
        }
    }
    
    public static void main(String[] args){
        
        Reentrant r = new Reentrant();
        
        r.a();
    }
}
