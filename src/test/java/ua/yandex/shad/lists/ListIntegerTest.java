package ua.yandex.shad.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maksym Yatsura
 */
public class ListIntegerTest {

    /**
     * Test of length method, of class ListInteger.
     */
    @Test
    public void testLength() {
        ListInteger list = new ListInteger(15, -5, 20, 18);
        int expResult = 4;
        int actualResult = list.length();
        assertEquals(expResult, actualResult);
    }

    /**
     * Test of empty method, of class ListInteger.
     */
    @Test
    public void testEmpty_False() {
        ListInteger list = new ListInteger(111);
        assertFalse(list.empty());        
    }

    @Test
    public void testEmpty_True() {
        ListInteger list = new ListInteger();
        assertTrue(list.empty());        
    }
    
    /**
     * Test of iterator method, of class ListInteger.
     */
    @Test
    public void testIterator() {
        ListInteger list = new ListInteger(5, 19, 21);
        int[] res = { 5, 19, 21};
        Iterator<Integer> iter = list.iterator();
        boolean equal = true;
        int index = 0;
        while(iter.hasNext()) {
            if(iter.next() != res[index++]) {
                equal = false;
                break;
            }
        }
        
        assertTrue(equal && index == 3);  
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testIterator_Exception() {
        ListInteger list = new ListInteger();
        Iterator<Integer> iter = list.iterator();
        Integer item = iter.next();
    }
    
    /**
     * Test of push method, of class ListInteger.
     */
    @Test
    public void testPush_ToEmpty() {
        ListInteger list = new ListInteger();
        list.push(999);
        assertFalse(list.empty());
    }
    
    @Test
    public void testPush_ToNotEmpty() {
        ListInteger list = new ListInteger(95);
        list.push(96);
        int expResult = 2;
        int actualResult = list.length();
        assertEquals(expResult, actualResult);
    }

    /**
     * Test of pushList method, of class ListInteger.
     */
    @Test
    public void testPushList_ToEmpty() {
        ListInteger src = new ListInteger(98, 99, 100);
        ListInteger dest = new ListInteger();
        dest.pushList(src);
        int expResult = 3;
        int actualResult = dest.length();
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testPushList_ToNotEmpty() {
        ListInteger src = new ListInteger(-7, -12);
        ListInteger dest = new ListInteger(-17);
        dest.pushList(src);
        int expResult = 3;
        int actualResult = dest.length();
        assertEquals(expResult, actualResult);
    }

    /**
     * Test of toArray method, of class ListInteger.
     */
    @Test
    public void testToArray() {
        int[] array = { 0, 9, -5, 4, 81};
        ListInteger list = new ListInteger(array);
        assertArrayEquals(array, list.toArray());
    }
    
}
