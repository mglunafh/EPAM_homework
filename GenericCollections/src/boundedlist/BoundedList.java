package boundedlist;

import java.util.Iterator;

/**
 *
 * @author dev64
 */
public class BoundedList<T> implements Iterable<T>{

    private Node<T> first;
    private Node<T> last;    
    private int capacity;
    private int size;
    
    public BoundedList(int capacity) {
        Node<T> guard = new Node<>();
        first = guard;
        last = guard;
        this.capacity = capacity;
    }
    
    public int getSize() {
        return size;    
    }
    
    public void add(T data) {
        if (size >= capacity) { 
            throw new ListOverflowException("trying to exceed capacity " + capacity); 
        }
    	Node<T> node = new Node<>();
        node.data = data;
	last.next = node;
        last = node;
        size++;
    }
    
    public T removeFirst() {
        if (size == 0)
            throw new ListUnderflowException();
        
        T result = (T) first.next.data;
        first.next = first.next.next;
        size--;
        return result;
    }
            
    @Override
    public Iterator<T> iterator() {

        class LocalIterator implements Iterator<T> {
            
            BoundedList.Node<T> temp;
            
            LocalIterator() {
                temp = BoundedList.this.first;
            }
            
            @Override
            public boolean hasNext() {
                return (temp.next != null);
            }

            @Override
            public T next() {
                temp = temp.next;
                return temp.data;
            }
            
        }
        
        return new LocalIterator();
    } 
    
    
    static class Node<T> {
        T data;
        Node next;
    } 
    
}
