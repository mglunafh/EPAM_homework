package genericstack;

/**
 *
 * @author dev64
 */
public class MyStack<T> {

    private Node<T> first; 
    
    public MyStack() {
    }
    
    public boolean isEmpty() {
        return (first == null);
    }
    
    public void push(T val) {
        Node<T> node = new Node();
        node.data = val;
        if (first != null)
            node.next = first; 
        first = node;
    }
    
    public T pop() {
        T result = first.data;
        first = first.next;
        return result;
    }
            
    class Node<T> {
        
        T data;
        Node<T> next;
    }
    
}
