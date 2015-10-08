package stackiterator;

import java.util.Iterator;

/**
 *
 * @author dev64
 */
public class MyStack implements Iterable<String>{
    
    private String[] data;
    private int top;
    
    public MyStack(int capacity) {
        data = new String[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return (top < 0);
    }
    
    @Override
    public Iterator<String> iterator() {
        class StackIterator implements Iterator<String> {

            int temp;
            StackIterator() {
            }
            
            @Override
            public boolean hasNext() {
                return (temp <= MyStack.this.top);
            }

            @Override
            public String next() {
                return MyStack.this.data[temp++];
            }            
        }
        
        return new StackIterator();
    }

    public void push(String str) {
        data[++top] = str;
    }
    
    public String pop() {
        String result = data[top];
        data[top--] = null;
        return result;
    }
}
