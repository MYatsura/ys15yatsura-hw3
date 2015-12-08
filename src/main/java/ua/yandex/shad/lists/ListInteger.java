package ua.yandex.shad.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Maksym Yatsura
 */
public class ListInteger implements Iterable<Integer> {
    
    private int length;
    private Node first;
    private Node last;
    
    private static class Node {
        private final int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }
    }
    
    private class ListIterator implements Iterator<Integer> {
        private Node node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int value = node.val;
            node = node.next;
            return value;
        }
    }
    
    public ListInteger(int... vals) {
        for (int val : vals) {
            push(val);
        }
    }   
    
    @Override
    public Iterator<Integer> iterator() {
        return new ListIterator();
    }
        
    public int length() {
        return length;
    }
    
    public boolean empty() {
        return length == 0;
    } 
    
    public void push(int val) {
        Node oldLast = last;
        last = new Node(val);
        last.next = null;
        if (empty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        length++;
    }
    
    public void pushList(ListInteger list) {
        if (empty()) {
             first = list.first;
        } else {
            last.next = list.first;
        }
        last = list.last;
        length += list.length;
    }
    
    public int[] toArray() {
        int[] array = new int[length];
        Iterator<Integer> iter = iterator();
        int ind = 0;
        while (iter.hasNext()) {
            array[ind++] = iter.next();
        }
        return array;
    }
    
}
