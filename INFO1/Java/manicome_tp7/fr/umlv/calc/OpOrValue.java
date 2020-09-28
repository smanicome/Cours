package fr.umlv.calc;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class OpOrValue {
  public static final int OP_NONE = 0;
  public static final int OP_ADD = 1;
  public static final int OP_SUB = 2;
  
  private final int operator;
  private final int value;
  private final OpOrValue left;
  private final OpOrValue right;
  
  private OpOrValue(int operator, int value, OpOrValue left, OpOrValue right) {
    this.operator = operator;
    this.value = value;
    this.left = left;
    this.right = right;
  }
  public OpOrValue(int value) {
    this(OP_NONE, value, null, null);
  }
  public OpOrValue(int operator, OpOrValue left, OpOrValue right) {
    this(operator, 0, Objects.requireNonNull(left), Objects.requireNonNull(right));
  }
  
  public int eval() {
    switch(operator) {
    case OP_ADD:
      return left.eval() + right.eval();
    case OP_SUB:
      return left.eval() - right.eval();
    default: // case OP_NONE:
      return value;
    }
  }

  /**
   * Must print new line after calling this recursive method
   * @param expression
   */
  public static void display(OpOrValue expression) {
    if (expression.operator != OP_NONE) {
      System.out.print("( ");
      display(expression.left);

      String operand = switch (expression.operator) {
        case OP_ADD -> " + ";
        case OP_SUB -> " - ";
        default -> "";
      };

      System.out.print( operand );

      display(expression.right);
      System.out.print(" )");
    } else {
      System.out.print(expression.value);
    }
  }

  public static OpOrValue parse(Iterator<String> iterator) {
    String stringValue = iterator.next();

    try {

      int value = Integer.parseInt(stringValue);
      return new OpOrValue(value);

    } catch (NumberFormatException e) {

      int operation = switch (stringValue) {
        case "+" -> OP_ADD;
        case "-" -> OP_SUB;
        default -> OP_NONE;
      };

      return new OpOrValue(operation, 0, parse(iterator), parse(iterator));

    }
  }
}
