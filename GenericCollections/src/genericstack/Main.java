package genericstack;

/**
 *
 * @author dev64
 */
public class Main {

    public static void main(String[] args) {
        
        MyStack<String> stack = new MyStack<>();
        
        stack.push("Hello");
        stack.push("everyone");
        stack.push("welcome");
        stack.push("to our course!");
        
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
