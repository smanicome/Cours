package fr.umlv.set;

import java.util.Objects;
import java.util.function.IntConsumer;

public class IntHashSet {
    private static final int bucketsNumber = 2;
    private final Entry[] entries = new Entry[bucketsNumber];

    protected static record Entry(int value, Entry next) {}

    private static int getIndex(int hashcode) {
        return hashcode & (bucketsNumber - 1);
    }

    public boolean contains(int value) {
        Entry entry = entries[getIndex(value)];
        for (Entry i = entry; i != null ; i = i.next()) {
            if(i.value() == value)
                return true;
        }

        return false;
    }

    public void add(int value) {
        if(!contains(value)) {
            int index = getIndex(value);
            Entry entry = entries[index];
            entries[index] = new Entry(value, entry);
        }
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < bucketsNumber; i++)
            for (Entry j = entries[i]; j != null ; j = j.next())
                size++;

        return size;
    }

    public void forEach(IntConsumer entryConsumer) {
        Objects.requireNonNull(entryConsumer);
        for (int i = 0; i < bucketsNumber; i++)
            for (Entry entry = entries[i]; entry != null ; entry = entry.next())
                entryConsumer.accept(entry.value());
    }
}
