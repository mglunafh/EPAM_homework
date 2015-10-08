package mylistwithlastreference;

import java.util.Iterator;

public class MyList<T> implements Iterable<T> {
	
    Node<T> first;
    Node<T> last;    
    int size;
    
    public MyList() {
        Node<T> guard = new Node<>();
        first = guard;
        last = guard;
    }
    
    public int getSize() {
        return size;    
    }
    
    public void add(T data) {
    	Node<T> node = new Node<>();
        node.data = data;
	last.next = node;
        last = node;
        size++;
    }
	
    @Override
    public String toString() {
		
	String s = "[";
	
	Node temp = first;
		
        while (temp != null) {
            s += temp.data;
            s += ", ";
            temp = temp.next;
	}
		
	return s + "]";
    }

    @Override
    public Iterator<T> iterator() {
        return this.getNestedIterator();
    }
    
    public Iterator<T> getInnerIterator() {
        return new InnerIterator();
    }
    
    public Iterator<T> getNestedIterator() {
        return new NestedIterator<>(this);
    }
    
    public Iterator<T> getLocalIterator() {

        class LocalIterator implements Iterator<T> {
            
            Node<T> temp;
            
            LocalIterator() {
                temp = MyList.this.first;
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
        Iterator result = new LocalIterator();
        return result;
    } 
    
    static class Node<T> {
        
        T data;
        Node next;
    } 
    
    public class InnerIterator implements Iterator<T> {

        private Node<T> temp;
        
        public InnerIterator() {
            temp = MyList.this.first;
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
    
    public static class NestedIterator<T> implements Iterator<T> {

        MyList<T> list;
        Node<T> temp;
        
        public NestedIterator(MyList<T> list) {
            this.list = list;
            temp = list.first;
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
}
