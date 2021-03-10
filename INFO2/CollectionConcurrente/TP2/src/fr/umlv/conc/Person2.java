package fr.umlv.conc;

public class Person2 {
    private String name;
    private volatile int age; // Les écritures précédentes sont accessibles

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }
}