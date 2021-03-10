package fr.umlv.newxl;

import fr.umlv.newxl.Calc.Cell;
import fr.umlv.newxl.Calc.Shift;
import fr.umlv.newxl.Calc.Computation;
//import fr.umlv.newxl.Calc.SpanDirection;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalcTest {

  @Test @Tag("Q1")
  public void newCell() {
    var cell = new Cell(12, 3);
    assertEquals(12, cell.row());
    assertEquals(cell.column(), 3);
  }
  @Test @Tag("Q1")
  public void newCellPrecondition() {
    assertAll(
        () -> assertThrows(IllegalArgumentException.class, () -> new Cell(-1, 10)),
        () -> assertThrows(IllegalArgumentException.class, () -> new Cell(10, -1))
    );
  }
  @Test @Tag("Q1")
  public void newCellToString() {
    var cell = new Cell(12, 3);
    assertEquals("D12", cell.toString());
  }
  @Test @Tag("Q1")
  public void newCellNoLetterToString() {
    assertEquals("Cell(12,26)", new Cell(12, 26).toString());
    assertEquals("Cell(1,27)", new Cell(1, 27).toString());
  }


  @Test @Tag("Q2")
  public void cellParseValid() {
    assertAll(
        () -> assertEquals(Cell.parse("A3").orElseThrow(), new Cell(3, 0)),
        () -> assertEquals(Cell.parse("E25").orElseThrow(), new Cell(25, 4))
    );
  }
  @Test @Tag("Q2")
  public void cellParseNotValid() {
    assertAll(
        () -> assertTrue(Cell.parse("").isEmpty()),
        () -> assertTrue(Cell.parse("A").isEmpty()),
        () -> assertTrue(Cell.parse("AA").isEmpty()),
        () -> assertTrue(Cell.parse("AZ10").isEmpty()),
        () -> assertTrue(Cell.parse("A10.3").isEmpty())
    );
  }
  @Test @Tag("Q2")
  public void cellParsePreconditions() {
    assertThrows(NullPointerException.class, () -> Cell.parse(null));
  }


  @Test @Tag("Q3")
  public void cellResolve() {
    var cell = new Cell(1, 3);
    var cell2 = cell.resolve(new Shift(1, 2));
    assertEquals(2, cell2.row());
    assertEquals(5, cell2.column());
  }
  @Test @Tag("Q3")
  public void cellRelativize() {
    var cell = new Cell(5, 9);
    var shift = cell.relativize(new Cell(1, 3));
    assertEquals(4, shift.dx());
    assertEquals(6, shift.dy());
  }
  @Test @Tag("Q3")
  public void cellResolvePreconditions() {
    var cell = new Cell(1, 1);
    assertThrows(NullPointerException.class, () -> cell.resolve(null));
  }
  @Test @Tag("Q3")
  public void cellRelativizePreconditions() {
    var cell = new Cell(1, 1);
    assertThrows(NullPointerException.class, () -> cell.relativize(null));
  }


  @Test @Tag("Q4")
  public void parseFormulaConstant() {
    var calc = new Calc();
    var cell = new Cell(1, 1);
    var computation = calc.parse(cell, "3.2");
    assertEquals(3.2, computation.compute(cell));
  }
  @Test @Tag("Q4")
  public void parseFormulaAdd() {
    var calc = new Calc();
    var cell = new Cell(1, 1);
    var computation = calc.parse(cell, "+ 2 4");
    assertEquals(6.0, computation.compute(cell));
  }
  @Test @Tag("Q4")
  public void parseFormulaAddSeveral() {
    var calc = new Calc();
    var cell = new Cell(1, 1);
    var computation = calc.parse(cell, "+ + 2 4 4");
    assertEquals(10.0, computation.compute(cell));
  }
  @Test @Tag("Q4")
  public void computationNotPublic() {
    assertFalse(Modifier.isPublic(Computation.class.getModifiers()));
  }
  @Test @Tag("Q4")
  public void parseIllFormedFormula() {
    var calc = new Calc();
    var cell = new Cell(1, 1);
    assertThrows(RuntimeException.class, () -> calc.parse(cell, "+ + 2 4"));
  }
  @Test @Tag("Q4")
  public void parseInvalidFormula() {
    var calc = new Calc();
    var cell = new Cell(1, 1);
    assertThrows(RuntimeException.class, () -> calc.parse(cell, "NOT_VALID"));
  }


  @Test @Tag("Q5")
  public void parseFormulaSub() {
    var calc = new Calc();
    var cell = new Cell(1, 1);
    var computation = calc.parse(cell, "- 8 2");
    assertEquals(6.0, computation.compute(cell));
  }
  @Test @Tag("Q5")
  public void parseFormulaMul() {
    var calc = new Calc();
    var cell = new Cell(1, 1);
    var computation = calc.parse(cell, "* 2 4");
    assertEquals(8.0, computation.compute(cell));
  }
  @Test @Tag("Q5")
  public void parseFormulaAddAndMul() {
    var calc = new Calc();
    var cell = new Cell(1, 1);
    var computation = calc.parse(cell, "+ * 3 4 5");
    assertEquals(17.0, computation.compute(cell));
  }
  @Test @Tag("Q5")
  public void parseFormulaDiv() {
    var calc = new Calc();
    var cell = new Cell(1, 1);
    var computation = calc.parse(cell, "/ 4 2");
    assertEquals(2.0, computation.compute(cell));
  }
  @Test @Tag("Q5")
  public void parseNotPublic() {
    assertTrue(Arrays.stream(Calc.class.getDeclaredMethods()).filter(m -> m.getName().equals("parse")).noneMatch(m -> Modifier.isPublic(m.getModifiers())));
  }


  @Test @Tag("Q6")
  public void evalFormulaConstant() {
    var calc = new Calc();
    var a2 = Cell.parse("A2").orElseThrow();
    calc.set(a2, "7.0");
    assertEquals(7.0, calc.eval(a2).orElseThrow());
  }
  @Test @Tag("Q6")
  public void evalFormulaAdd() {
    var calc = new Calc();
    var c77 = Cell.parse("C77").orElseThrow();
    calc.set(c77, "+ 2.0 1.0");
    assertEquals(3.0, calc.eval(c77).orElseThrow());
  }
  @Test @Tag("Q6")
  public void evalFormulaSub() {
    var calc = new Calc();
    var d12 = Cell.parse("D12").orElseThrow();
    calc.set(d12, "- 1.0 2.0");
    assertEquals(-1, calc.eval(d12).orElseThrow());
  }
  @Test @Tag("Q6")
  public void evalFormulaMul() {
    var calc = new Calc();
    var y23 = Cell.parse("Y23").orElseThrow();
    calc.set(y23, "* 2.0 15.0");
    assertEquals(30.0, calc.eval(y23).orElseThrow());
  }
  @Test @Tag("Q6")
  public void evalFormulaDiv() {
    var calc = new Calc();
    var f140 = Cell.parse("F140").orElseThrow();
    calc.set(f140, "/ 20.0 2.0");
    assertEquals(10.0, calc.eval(f140).orElseThrow());
  }
  @Test @Tag("Q6")
  public void calcEvalEmpty() {
    var calc = new Calc();
    var e55 = Cell.parse("E55").orElseThrow();
    assertTrue(calc.eval(e55).isEmpty());
  }
  @Test @Tag("Q6")
  public void calcSetPreconditions() {
    var calc = new Calc();
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> calc.set(new Cell(0, 0), null)),
        () -> assertThrows(NullPointerException.class, () -> calc.set(null, "0.0")),
        () -> assertThrows(IllegalArgumentException.class, () -> calc.set(new Cell(-1, -1), "0.0"))
    );
  }
  @Test @Tag("Q6")
  public void calcEvalPreconditions() {
    var calc = new Calc();
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> calc.eval(null)),
        () -> assertThrows(IllegalArgumentException.class, () -> calc.eval(new Cell(-1, -1)))
    );
  }
  @Test @Tag("Q6")
  public void evalFormulaRefCell() {
    var calc = new Calc();
    var a2 = Cell.parse("A2").orElseThrow();
    var b12 = Cell.parse("B12").orElseThrow();
    calc.set(b12, "2.0");
    calc.set(a2, "B12");
    assertEquals(2.0, calc.eval(a2).orElseThrow());
  }
  @Test @Tag("Q6")
  public void parseFormulaCellRelative() {
    var calc = new Calc();
    var a2 = Cell.parse("A2").orElseThrow();
    var computation = calc.parse(a2, "B2");
    var a3 = Cell.parse("A3").orElseThrow();
    var b3 = Cell.parse("B3").orElseThrow();
    calc.set(b3, "42.0");
    assertEquals(42.0, computation.compute(a3));
  }


  @Test @Tag("Q7")
  public void setAllSOUTH() {
    var calc = new Calc();
    IntStream.range(0, 10).forEach(i -> calc.set(new Cell(i, 1), "" + i));
    var a3 = Cell.parse("A3").orElseThrow();
    calc.setAll(a3, SpanDirection.SOUTH, 10, "B0");
    IntStream.range(0, 10).forEach(i -> assertEquals(i, calc.eval(a3.resolve(new Shift(i, 0))).orElseThrow()));
  }
  @Test @Tag("Q7")
  public void setAllNORTH() {
    var calc = new Calc();
    IntStream.range(0, 10).forEach(i -> calc.set(new Cell(i, 1), "" + i));
    var a4 = Cell.parse("A4").orElseThrow();
    var a13 = Cell.parse("A13").orElseThrow();
    calc.setAll(a13, SpanDirection.NORTH, 10, "B9");
    IntStream.range(0, 10).forEach(i -> assertEquals(i, calc.eval(a4.resolve(new Shift(i, 0))).orElseThrow()));
  }
  @Test @Tag("Q7")
  public void setAllEAST() {
    var calc = new Calc();
    IntStream.range(0, 10).forEach(i -> calc.set(new Cell(1, i), "" + i));
    var c0 = Cell.parse("C0").orElseThrow();
    calc.setAll(c0, SpanDirection.EAST, 10, "A1");
    IntStream.range(0, 10).forEach(i -> assertEquals(i, calc.eval(c0.resolve(new Shift(0, i))).orElseThrow()));
  }
  @Test @Tag("Q7")
  public void setAllWEST() {
    var calc = new Calc();
    IntStream.range(0, 10).forEach(i -> calc.set(new Cell(1, i), "" + i));
    var d0 = Cell.parse("D0").orElseThrow();
    var m0 = Cell.parse("M0").orElseThrow();
    calc.setAll(m0, SpanDirection.WEST, 10, "J1");
    IntStream.range(0, 10).forEach(i -> assertEquals(i, calc.eval(d0.resolve(new Shift(0, i))).orElseThrow()));
  }
  @Test @Tag("Q7")
  public void setAllPreconditions() {
    var calc = new Calc();
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> calc.setAll(null, SpanDirection.EAST, 2, "A12")),
        () -> assertThrows(NullPointerException.class, () -> calc.setAll(new Cell(0, 0), null, 2, "A12")),
        () -> assertThrows(NullPointerException.class, () -> calc.setAll(new Cell(0, 0), SpanDirection.EAST, 2, null)),
        () -> assertThrows(IllegalArgumentException.class, () -> calc.setAll(new Cell(0, 0), SpanDirection.EAST, 0, "A12")),
        () -> assertThrows(IllegalArgumentException.class, () -> calc.setAll(new Cell(0, 0), SpanDirection.EAST, -2, "A12")),
        () -> assertThrows(IllegalArgumentException.class, () -> calc.setAll(new Cell(-1, -1), SpanDirection.EAST, 2, "A12"))
    );
  }
  @Test @Tag("Q7")
  public void publicMethods() {
    var publicMethods = Arrays.stream(Calc.class.getDeclaredMethods())
        .filter(m -> Modifier.isPublic(m.getModifiers()))
        .map(m -> m.getName() + '/' + m.getParameterCount())
        .sorted()
        .collect(toList());
    assertEquals(List.of("eval/1", "set/2", "setAll/4"), publicMethods);
  }
  @Test @Tag("Q7")
  public void publicConstructor() {
    var publicConstructors = Arrays.stream(Calc.class.getDeclaredConstructors())
        .filter(m -> Modifier.isPublic(m.getModifiers()))
        .map(c -> "<init>/" + c.getParameterCount())
        .collect(toList());
    assertEquals(List.of("<init>/0"), publicConstructors);
  }


//  @Test @Tag("Q8")
//  public void evalCircularity() {
//    var calc = new Calc();
//    var a3 = Cell.parse("A3").orElseThrow();
//    var b4 = Cell.parse("B4").orElseThrow();
//    var c5 = Cell.parse("C5").orElseThrow();
//    calc.set(a3, "B4");
//    calc.set(b4, "C5");
//    calc.set(c5, "A3");
//    assertThrows(IllegalStateException.class, () -> calc.eval(a3));
//  }
//  @Test @Tag("Q8")
//  public void evalNoCircularity() {
//    var calc = new Calc();
//    var a3 = Cell.parse("A3").orElseThrow();
//    var b4 = Cell.parse("B4").orElseThrow();
//    calc.set(a3, "+ B4 B4");
//    calc.set(b4, "2.0");
//    assertEquals(4.0, calc.eval(a3).orElseThrow());
//  }
}
