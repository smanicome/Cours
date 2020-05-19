package fr.umlv.data;

public class LinkedList {
    private final Link link;
    private LinkedList next;

    public LinkedList(int value) {
        this.link = new Link(value);
        next = null;
    }

    public LinkedList next() {
        return next;
    }

    public int getValue() {
        return link.getValue();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getValue());
        builder.append(' ');

        for (LinkedList i = next; i != null; i = i.next()) {
            builder.append(i.getValue());
            builder.append(' ');
        }

        return builder.toString();
    }

    public void add(int value) {
        if (next != null) {
            next.add(value);
        } else {
            next = new LinkedList(value);
        }
    }
}
