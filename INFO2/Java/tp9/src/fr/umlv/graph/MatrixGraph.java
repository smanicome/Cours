package fr.umlv.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

class MatrixGraph<T> implements Graph<T> {
    private T[] array;
    private final int nodeCount;

    @SuppressWarnings("SafeIgnore")
    public MatrixGraph(int nodeCount) {
        if(nodeCount < 0)
            throw new IllegalArgumentException();

        array = (T[]) new Object[nodeCount * nodeCount];
        this.nodeCount = nodeCount;
    }

    @Override
    public Optional<T> getWeight(int src, int dst) {
        Objects.checkIndex(src, nodeCount);
        Objects.checkIndex(dst, nodeCount);
        return Optional.ofNullable( get( src, dst ) );
    }

    @Override
    public void addEdge(int src, int dst, T weight) {
        Objects.requireNonNull( weight );
        Objects.checkIndex(src, nodeCount);
        Objects.checkIndex(dst, nodeCount);
        var index = src * nodeCount + dst;
        array[ index ] = weight;
    }

    private T get( int i, int j ) {
        return array[ i*nodeCount+j ];
    }


    public void edges( int src, EdgeConsumer<? super T> consumer ) {
        Objects.requireNonNull(consumer);
        Objects.checkIndex(src, nodeCount);
        for ( int dst = 0; dst < nodeCount; dst++ ) {
            if ( get( src, dst ) != null ) {
                consumer.edge(src, dst, get( src, dst ) );
            }
        }
    }

    public Iterator<Integer> neighborIterator(int src ) {
        return new Iterator<Integer>() {
            int nextNeighbor = findNext(0);

            private int findNext( int startInclusive ) {
                for ( int i = startInclusive; i < nodeCount; i++ ) {
                    if ( get( src, startInclusive ) != null ) {
                        return startInclusive;
                    }
                }

                return -1;
            }

            @Override
            public boolean hasNext() {
                return nextNeighbor != -1;
            }

            @Override
            public Integer next() {
                var tmp = nextNeighbor;
                if(tmp == -1)
                    throw new NoSuchElementException();
                nextNeighbor = findNext( tmp + 1 );
                return tmp;
            }
        };
    }

    @Override
    public IntStream neighborStream(int src) {
        if(src < 0 || src >= nodeCount)
            throw new IndexOutOfBoundsException();
        return null;
    }
}
