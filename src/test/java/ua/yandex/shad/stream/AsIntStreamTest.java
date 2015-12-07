/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.shad.stream;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class AsIntStreamTest {

    private static final double EPS = 1e-6;
    
    @Test
    public void testOf_EmptyArray() {
        IntStream stream = AsIntStream.of();
        int[] expArray = {};
        int[] actualArray = stream.toArray();
        assertArrayEquals(expArray, actualArray);
    }
    
    @Test
    public void testOf_NotEmptyArray() {
        IntStream stream = AsIntStream.of(2, 8, 12);
        int[] expArray = {2, 8, 12};
        int[] actualArray = stream.toArray();
        assertArrayEquals(expArray, actualArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverage_EmptyArray() {
        IntStream stream = AsIntStream.of();
        double actualResult = stream.average();
    }
    
    @Test
    public void testAverage_ZeroSum() {
        IntStream stream = AsIntStream.of(-25, 25);
        double expResult = 0.0;
        double actualResult = stream.average();
        assertEquals(expResult, actualResult, EPS);
    }
    
    @Test
    public void testAverage_FractialResult() {
        IntStream stream = AsIntStream.of(-9, 4, 2, 0, -5, 2, 14);
        double expResult = 1.14285714;
        double actualResult = stream.average();
        assertEquals(expResult, actualResult, EPS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMax_EmptyArray() {
        IntStream stream = AsIntStream.of();
        int actualResult = stream.max();        
    }
    
    @Test
    public void testMax_NotEmptyArray() {
        IntStream stream = AsIntStream.of(16, 19, 17);
        int expResult = 19;
        int actualResult = stream.max();
        assertEquals(expResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMin_EmptyArray() {
        IntStream stream = AsIntStream.of();
        double actualResult = stream.min();        
    }
    
    @Test
    public void testMin_NotEmptyArray() {
        IntStream stream = AsIntStream.of(16, 19, 17);
        int expResult = 16;
        int actualResult = stream.min();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testCount_EmptyArray() {
        IntStream stream = AsIntStream.of();
        long expResult = 0;
        long actualResult = stream.count();
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testCount_NotEmptyArray() {
        IntStream stream = AsIntStream.of(14, 5, -9, 4, 82, 35, 2);
        long expResult = 7;
        long actualResult = stream.count();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testFilter_EmptyStream() {
        IntStream stream = AsIntStream.of();
        stream.filter(x -> (x&1) == 0);
        int[] expResult = {};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);
    }
    
    @Test
    public void testFilter_EmptyResult() {
        IntStream stream = AsIntStream.of(15, 17, 19, 21);
        stream.filter(x -> (x&1) == 0);
        int[] expResult = {};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);
    }
    
    @Test
    public void testFilter_NotEmptyResult() {
        IntStream stream = AsIntStream.of(1, 2, 3, 4);
        stream.filter(x -> (x&1) == 0);
        int[] expResult = {2, 4};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);
    }
    
    @Test
    public void testFilter_AllElementsOk() {
        IntStream stream = AsIntStream.of(1, -8, 7, 0, 0);
        stream.filter(x -> x < 10);
        int[] expResult = {1, -8, 7, 0, 0};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);
    }
    
    @Test
    public void testFilter_Twice() {
        IntStream stream = AsIntStream.of(5, 8, -9, 4, 3);
        stream.filter(x -> x > 2).filter( x -> x < 5);
        int[] expResult = {4, 3};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);
    }
    
    class Adder {
        private int val = 0;
        
        public void addToVal(int d) {
            val += d;
        }
    }

    @Test
    public void testForEach_EmptyArray() {
        IntStream stream = AsIntStream.of();
        Adder adder = new Adder();
        stream.forEach(adder::addToVal);
        int expResult = 0;
        int actualResult = adder.val;
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testForEach_NotEmptyArray() {
        IntStream stream = AsIntStream.of(12, 16, 20);
        Adder adder = new Adder();
        stream.forEach(adder::addToVal);
        int expResult = 48;
        int actualResult = adder.val;
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testMap_EmptyStream() {
        IntStream stream = AsIntStream.of();
        stream.map(x -> x + 1);
        int[] expResult = {};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);
    }
    
    @Test
    public void testMap_NotEmptyStream() {
        IntStream stream = AsIntStream.of(-2, -3, -4);
        stream.map(x -> x + 1);
        int[] expResult = {-1, -2, -3};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);
    }
    
    @Test
    public void testMap_ResultEqualToStream() {
        IntStream stream = AsIntStream.of(2, 3, 4);
        stream.map(x -> x);
        int[] expResult = {2, 3, 4};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);
    }

    @Test
    public void testReduce_EmptyStream() {
        IntStream stream = AsIntStream.of();
        int expResult = 0;
        int actualResult = stream.reduce(0, (sum, d) -> sum + d);
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testReduce_NotEmptyStream() {
        IntStream stream = AsIntStream.of(12, 17, 11);
        int expResult = 40;
        int actualResult = stream.reduce(0, (sum, d) -> sum + d);
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testReduce_Factorial() {
        IntStream stream = AsIntStream.of(1, 2, 3, 4, 5, 6, 7);
        int expResult = 5040;
        int actualResult = stream.reduce(1, (f, d) -> f * d);
        assertEquals(expResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSum_EmptyStream() {
        IntStream stream = AsIntStream.of();
        int actualResult = stream.sum();    
    }
    
    @Test
    public void testSum_NotEmptyStream() {
        IntStream stream = AsIntStream.of(2, 4, 8);
        int actualResult = stream.sum();    
        int expResult = 14;
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testSum_ZeroResult() {
        IntStream stream = AsIntStream.of(-2, 2,  -4, 4, -8, 8);
        int actualResult = stream.sum();    
        int expResult = 0;
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testToArray_EmptyStream() {
        IntStream stream = AsIntStream.of();
        int[] expArray = {};
        int[] actualArray = stream.toArray();
        assertArrayEquals(expArray, actualArray);
    }
    
    @Test
    public void testToArray_NotEmptyStream() {
        IntStream stream = AsIntStream.of(4, 5);
        int[] expArray = {4, 5};
        int[] actualArray = stream.toArray();
        assertArrayEquals(expArray, actualArray);
    }

    @Test
    public void testFlatMap_EmptyArray() {
        IntStream stream = AsIntStream.of();
        stream.flatMap(x -> AsIntStream.of(2*x));
        int[] expResult = {};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);           
    }
    
    @Test
    public void testFlatMap_NotEmptyArray() {
        IntStream stream = AsIntStream.of(7, 6, 4);
        stream.flatMap(x -> AsIntStream.of(2*x));
        int[] expResult = {14, 12, 8};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);           
    }
    
    @Test
    public void testDifferentOperations_NotEmptyArray() {
        IntStream stream = AsIntStream.of(9, -2, 4, 1);
        stream.filter(x -> (x & 1) == 0)
              .map(x -> x+1)
              .flatMap(x -> AsIntStream.of(x, 2*x));
        
        
        int[] expResult = {-1, -2, 5, 10};
        int[] actualResult = stream.toArray();
        assertArrayEquals(expResult, actualResult);           
    }
    
    @Test
    public void testDifferentOperations_WithTerminalOperation() {
        IntStream stream = AsIntStream.of(0, 1, 0, 5, 2, 5, 0);
        double actualResult = stream.filter(x ->  x == 0 || x == 5)
                                 .map(x -> 10 - x)
                                 .flatMap(x -> AsIntStream.of(x-1, x, x+1))
                                 .average();
        
        
        double expResult = 8.0;
        assertEquals(expResult, actualResult, EPS);           
    }


}
