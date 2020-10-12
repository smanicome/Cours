package fr.umlv.set;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class DynamicHashSet<T> {
    private int bucketsNumber = 2;
    private int size = 0;

    @SuppressWarnings("SafeIgnore")
    private Entry<T>[] entries = (Entry<T>[]) new Entry[bucketsNumber];

    protected static final record Entry<T>(T value, Entry<T> next) {}

    private int getIndex(int hashcode) {
        return hashcode & (bucketsNumber - 1);
    }

    public <E> boolean contains(E value) {
        Objects.requireNonNull(value);
        Entry<T> entry = entries[getIndex(value.hashCode())];
        for (Entry<T> i = entry; i != null ; i = i.next()) {
            if(i.value().equals(value))
                return true;
        }

        return false;
    }

    public void add(T value) {
        Objects.requireNonNull(value);
        if(!contains(value)) {
            int index = getIndex(value.hashCode());
            Entry<T> entry = entries[index];
            entries[index] = new Entry<T>(value, entry);
            increaseSize();
        }
    }

    @SuppressWarnings("SafeIgnore")
    private void increaseSize() {
        size++;
        if(size < (bucketsNumber / 2))
            return;

        ArrayList<T> valueList = new ArrayList<>();
        forEach(valueList::add);

        bucketsNumber = bucketsNumber * 2;
        entries = (Entry<T>[]) new Entry[bucketsNumber];

        size = 0;
        valueList.forEach(this::add);
    }

    public int size() {
        return size;
    }

    public void forEach(Consumer<? super T> entryConsumer) {
        Objects.requireNonNull(entryConsumer);
        for (int i = 0; i < bucketsNumber; i++)
            for (Entry<T> entry = entries[i]; entry != null ; entry = entry.next())
                entryConsumer.accept(entry.value());
    }
}


// Max size of bucket log(log(bucketsNumber))