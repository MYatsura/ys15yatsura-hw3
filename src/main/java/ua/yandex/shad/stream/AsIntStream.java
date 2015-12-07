package ua.yandex.shad.stream;

import ua.yandex.shad.function.IntUnaryOperator;
import ua.yandex.shad.function.IntToIntStreamFunction;
import ua.yandex.shad.function.IntPredicate;
import ua.yandex.shad.function.IntConsumer;
import ua.yandex.shad.function.IntBinaryOperator;
import ua.yandex.shad.function.IntFunc;
import ua.yandex.shad.lists.ListFunction;
import ua.yandex.shad.lists.ListInteger;

public class AsIntStream implements IntStream {

    private static int MIN_INT = Integer.MIN_VALUE;
    private static int MAX_INT = Integer.MAX_VALUE;
    
    private ListInteger vals = new ListInteger();
    private ListFunction intermediateOps = new ListFunction();
    
    private AsIntStream() {
    }

    public static IntStream of(int... values) {
        AsIntStream stream = new AsIntStream();
        stream.vals = new ListInteger(values);
        return stream;
    }

    @Override
    public double average() {
        performIntermediateOperations();
        if (vals.empty()) {
            throw new IllegalArgumentException();
        }
        return (double) sum() / count();
    }
    
    @Override
    public int max() {
        performIntermediateOperations();
        if (vals.empty()) {
            throw new IllegalArgumentException();
        }
        return reduce(MIN_INT, 
                (max, x) -> max = Math.max(max, x));
    }

    @Override
    public int min() {
        performIntermediateOperations();
        if (vals.empty()) {
            throw new IllegalArgumentException();
        }
        return reduce(MAX_INT, 
                (min, x) -> min = Math.min(min, x));
    }

    @Override
    public long count() {
        performIntermediateOperations();
        return vals.length();
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        intermediateOps.push(predicate);
        return this;
    }

    @Override
    public void forEach(IntConsumer action) {
        performIntermediateOperations();
        for (int d: vals) {
            action.accept(d);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        intermediateOps.push(mapper);
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        performIntermediateOperations();
        int result = identity;
        for (int d: vals) {
            result = op.apply(result, d);
        }
        return result;
    }

    @Override
    public int sum() {
        if (vals.empty()) {
            throw new IllegalArgumentException();
        }
        performIntermediateOperations();
        return reduce (0, (sum, d) -> sum +=d);
    }

    @Override
    public int[] toArray() {
        performIntermediateOperations();
        return vals.toArray();
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        intermediateOps.push(func);
        return this;
    }
    
    private void performIntermediateOperations() {
        for (IntFunc func : intermediateOps) {
            if (func instanceof IntPredicate) {
                performFilter((IntPredicate) func);
            } else if (func instanceof IntUnaryOperator) {
                performMap((IntUnaryOperator) func);
            } else {
                performFlatMap((IntToIntStreamFunction) func);
            }
        }
        intermediateOps.clear();
    }    
    
    private void performFilter(IntPredicate predicate) {
        ListInteger result = new ListInteger();
        for (int d : vals) {
            if (predicate.test(d)) {
                result.push(d);
            }
        }
        vals = result;
    }

    private void performMap(IntUnaryOperator mapper) {
        ListInteger result = new ListInteger();
        for (int d : vals) {
            result.push(mapper.apply(d));
        }
        vals = result;
    }
    
    private void performFlatMap(IntToIntStreamFunction function) {
        ListInteger result = new ListInteger();
        for (int d : vals) {
            AsIntStream newStream = 
                    (AsIntStream) function.applyAsIntStream(d);
            result.pushList(newStream.vals);
        }
        vals = result;
    }
    
}
