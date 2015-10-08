package boundedlist;

/**
 *
 * @author dev64
 */
public class Main {

    public static void main(String[] args) {
        
        int size = 5;
        BoundedList<Integer> list = new BoundedList<>(size);
        
        try {
            int result = list.removeFirst();
        } catch(ListUnderflowException e) {
            System.out.println("Oops, list is still empty!");
        }
        
        try {
            for (int i = 1; i < 10; i++) {
                list.add(i * 100 + 11 * i / 3);
                System.out.printf("Number %d was added succesfully\n", i);
            } 
         } catch (ListOverflowException e) {
             System.out.println("Oops, list has already had too much elements");
             System.out.println(e.getMessage());
             
         }
        
    }
}
