package stackiterator;

/**
 *
 * @author dev64
 */
public class Main {

    public static void main(String[] arg) {
        
        int capacity = 20;
        MyStack stack = new MyStack(capacity);
        
        stack.push("Hello");
        stack.push("everyone");
        stack.push("welcome");
        stack.push("to our course!");
        
        System.out.println("There is some info contained in stack");
        
        for (String str : stack) {
            System.out.println(str);
        }
        
        System.out.println("Ok, let's retrieve all the info from it");
        
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
