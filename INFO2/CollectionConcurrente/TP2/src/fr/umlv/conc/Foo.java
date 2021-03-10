package fr.umlv.conc;

public class Foo {
    private String value;

    public Foo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static Foo foo;
    public static void main(String[] args) {
        new Thread(() -> {
            if (foo != null) {
                System.out.println(foo.value);
            }
        }).start();
        foo = new Foo("value");
    }
}