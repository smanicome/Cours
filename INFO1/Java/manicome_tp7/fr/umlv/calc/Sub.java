package fr.umlv.calc;

public class Sub extends Operation implements Expr {
    private final Expr left;
    private final Expr right;

    public Sub(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }


    @Override
    public int eval() {
        return left.eval() - right.eval();
    }

    @Override
    public String toString() {
        return "( " + left.toString() + " - " + right.toString() + " )";
    }
}
