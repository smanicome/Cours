package fr.umlv.data;

class Link {
    private final int value;

    protected Link(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        Link a = new Link(13);
        Link b = new Link(144);
    }
}