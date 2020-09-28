package fr.umlv.calc;

import java.util.Iterator;
import java.util.Objects;

public abstract class OpOrValueCopy {
  
  public abstract int eval();

  /**
   * Must print new line after calling this recursive method
   * @param oov
   */
  public abstract void display(OpOrValueCopy oov);

  public abstract OpOrValueCopy parse(Iterator<String> iterator);
}
