package CustomSort;

/**
 *
 * @author dev64
 */
public class CustomSort {
    
    public static void sort(int[] arr) {
    
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int indexMin = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    indexMin = j;
                }
            }
            arr[indexMin] = arr[i];
            arr[i] = min;
        }
    }
    
    public static void main(String[] args) {
        
        
        
    }
}
