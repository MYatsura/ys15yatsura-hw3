package ua.yandex.shad.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import ua.yandex.shad.function.IntFunc;

/**
 *
 * @author Maksym Yatsura
 */
public class ListFunction implements Iterable<IntFunc> {
    
    private int length;
    private Node first;
    private Node last;
    
    private static class Node {
        private final IntFunc func;
        private Node next;

        public Node(IntFunc func) {
            this.func = func;
        }
    }    
    
    private class ListIterator implements Iterator<IntFunc> {
        private Node node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public IntFunc next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            IntFunc func = node.func;
            node = node.next;
            return func;
        }
    }  
    
    @Override
    public Iterator<IntFunc> iterator() {
        return new ListFunction.ListIterator();
    }
    
    public int length() {
        return length;
    }
    
    public boolean empty() {
        return length == 0;
    } 
    
    public void clear() {
        length = 0;
        first = null;
        last = null;
    }
    
    public void push(IntFunc func) {
        Node oldLast = last;
        last = new Node(func);
        last.next = null;
        if (empty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        length++;
    }
    
}
