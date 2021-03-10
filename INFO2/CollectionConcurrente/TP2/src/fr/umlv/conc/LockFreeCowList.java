package fr.umlv.conc;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LockFreeCowList<E> {
    private E[] array;
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private static final VarHandle ARRAY_REF;

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            ARRAY_REF = lookup.findVarHandle(
                LockFreeCowList.class, // classe contenant le champ
                "array", // nom du champ
                Object[].class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public LockFreeCowList() {
        ARRAY_REF.setVolatile(this, (E[]) EMPTY_ARRAY);
    }

    public void add(E element) {
        for(;;) {
            var array = ((E[])ARRAY_REF.getVolatile(this));
            var newArray = Arrays.copyOf(array, array.length + 1);
            newArray[array.length] = element;
            if(ARRAY_REF.compareAndSet(this, array, newArray)) {
                break;
            }
        }
    }

        public int size() {
            return ((E[])ARRAY_REF.getVolatile(this)).length;
        }

    public static void main(String[] args) throws InterruptedException {
        var list = new LockFreeCowList<Integer>();
        var threads = IntStream.range(0, 4)
                .mapToObj(__ -> new Thread(() -> {
                    for(var i = 0; i < 5_000; i++) {
                        list.add(i);
                    }
                }))
                .collect(Collectors.toList());

        for(var thread: threads) {
            thread.start();
        }

        for(var thread: threads) {
            thread.join();
        }

        System.out.println("size " + list.size());
    }
}
