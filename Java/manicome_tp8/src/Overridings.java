class A {
    int x = 1;

    public int getX() {
        return x;
    }

    static void printX(A a) {
        System.out.println("in A.printX");
        System.out.println("x " + a.x);
        System.out.println("getX() " + a.getX());
    }

    int m(A a) { return 1; }
}

class B extends A {
    int x = 2;

    public int getX() {
        return x;
    }

    static void printX(B b) {
        System.out.println("in B.printX");
        System.out.println("x " + b.x);
        System.out.println("getX() " + b.getX());
    }

    int m(B b) { return 2; }
}

public class Overridings {
    public static void main(String[] args) {
        A.printX(new A());     // 1
        B.printX(new B());   // 2
        A.printX(new B());   // 3
        A a = new B();
        a.m(a);              // 4
    }
}