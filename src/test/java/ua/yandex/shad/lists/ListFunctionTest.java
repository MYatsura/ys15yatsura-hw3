/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.shad.lists;

import java.util.function.IntPredicate;
import org.junit.Test;
import static org.junit.Assert.*;
import ua.yandex.shad.function.IntFunc;

/**
 *
 * @author user
 */
public class ListFunctionTest {
    

    @Test
    public void testIterator() {
    }

    @Test
    public void testLength_EmptyList() {
        ListFunction list = new ListFunction();
        int expResult = 0;
        int actualResult = list.length();
        assertEquals(expResult, actualResult);        
    }
    
    class Function implements IntFunc {

    }
    
    @Test
    public void testLength_NotEmptyList() {
        ListFunction list = new ListFunction();
        IntFunc func = new Function();
        list.push(func);
        int expResult = 1;
        int actualResult = list.length();
        assertEquals(expResult, actualResult);    
    }
    
    @Test
    public void testEmpty_False() {
        ListFunction list = new ListFunction();
        IntFunc func = new Function();
        list.push(func);
        assertFalse(list.empty());  
    }
    
    @Test
    public void testEmpty_True() {
        ListFunction list = new ListFunction();
        assertTrue(list.empty()); 
    }

    @Test
    public void testClear() {
        ListFunction list = new ListFunction();
        IntFunc func = new Function();
        list.push(func);
        list.clear();
        assertTrue(list.empty()); 
    }

    @Test
    public void testPush() {
        ListFunction list = new ListFunction();
        IntFunc func = new Function();
        list.push(func);
        int expResult = 1;
        int actualResult = list.length();
        assertFalse(list.empty());    
    }
    
}
