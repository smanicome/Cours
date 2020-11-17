package fr.umlv.columnar;

import fr.umlv.columnar.Columnar.Column;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ColumnarTest {
  @Test @Tag("Q1")
  public void columnOfString() {
    var column = Columnar.column("name", String.class);
    assertEquals("name of java.lang.String", column.toString());
  }
  @Test @Tag("Q1")
  public void columnOfInt() {
    var column = Columnar.column("count", Integer.class);
    assertEquals("count of java.lang.Integer", column.toString());
  }
  @Test @Tag("Q1")
  public void columnClassIsPublic() {
    assertTrue(Modifier.isPublic(Column.class.getModifiers()));
  }
  @Test @Tag("Q1")
  public void columnIsParameterized() {
    Column<String> column1 = Columnar.column("string", String.class);
    Column<Integer> column2 = Columnar.column("integer", Integer.class);
    assertNotNull(column1);
    assertNotNull(column2);
  }
  @Test @Tag("Q1")
  public void columnPreconditions() {
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> Columnar.column(null, String.class)),
        () -> assertThrows(NullPointerException.class, () -> Columnar.column("name", null))
    );
  }


  @Test @Tag("Q2")
  public void columnar() {
    var name = Columnar.column("name", String.class);
    var count = Columnar.column("count", Integer.class);
    var columnar = Columnar.of(name, count);
    assertEquals("name | count", columnar.toString());
  }
  @Test @Tag("Q2")
  public void columnarOneColumn() {
    var one = Columnar.column("one", String.class);
    var columnar = Columnar.of(one);
    assertEquals("one", columnar.toString());
  }
  @Test @Tag("Q2")
  public void columnarSharedColumn() {
    var shared = Columnar.column("shared", String.class);
    var columnar = Columnar.of(shared);
    assertThrows(IllegalStateException.class, () -> Columnar.of(shared));
  }
  @Test @Tag("Q2")
  public void columnarDuplicatedColumns() {
    var column = Columnar.column("column", Double.class);
    assertThrows(IllegalStateException.class, () -> Columnar.of(column, column));
  }
  @Test @Tag("Q2")
  public void columnarSideEffect() {
    var column = Columnar.column("column", Integer.class);
    var columns = new Column<?>[] { column };
    var columnar = Columnar.of(columns);
    columns[0] = Columnar.column("anotherColumn", Integer.class);
    assertEquals("column", columnar.toString());
  }
  @Test @Tag("Q2")
  public void columnarPreconditions() {
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> Columnar.of((Column<?>[]) null)),
        () -> assertThrows(NullPointerException.class, () -> Columnar.of(Columnar.column("name", Object.class), null)),
        () -> assertThrows(IllegalArgumentException.class, Columnar::of)
    );
  }


  @Test @Tag("Q3")
  public void addValues() {
    var name = Columnar.column("pet", String.class);
    var count = Columnar.column("count", Integer.class);
    var columnar = Columnar.of(name, count);
    columnar.addValues("dog", 1);
    columnar.addValues("cat", 2);
    assertEquals(2, columnar.size());
  }
  @Test @Tag("Q3")
  public void addValuesALot() {
    var value = Columnar.column("value", Integer.class);
    var text = Columnar.column("text", String.class);
    var columnar = Columnar.of(value, text);
    range(0, 1_000_000).forEach(i -> columnar.addValues(i, "" + i));
    assertEquals(1_000_000, columnar.size());
  }
  @Test @Tag("Q3")
  public void addValuesWithNull() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    columnar.addValues(new Object[] { null });
  }
  @Test @Tag("Q3")
  public void addValuesWrongNumberOfColumn() {
    var column = Columnar.column("column", String.class);
    var columnar = Columnar.of(column);
    assertThrows(IllegalArgumentException.class, () -> columnar.addValues("arg1", "arg2"));
  }
  @Test @Tag("Q3")
  public void addValuesWrongType() {
    var value = Columnar.column("count", Integer.class);
    var columnar = Columnar.of(value);
    assertThrows(ClassCastException.class, () -> columnar.addValues("oops"));
  }
  @Test @Tag("Q3")
  public void addValuesPreconditions() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    assertThrows(NullPointerException.class, () -> columnar.addValues((Object[])null));
  }


  @Test @Tag("Q4")
  public void get() {
    var name = Columnar.column("pet", String.class);
    var count = Columnar.column("count", Integer.class);
    var columnar = Columnar.of(name, count);
    columnar.addValues("dog", 1);
    columnar.addValues("cat", 2);
    assertEquals("dog", columnar.get(name, 0));
    assertEquals("cat", columnar.get(name, 1));
    assertEquals(1, columnar.get(count, 0));
    assertEquals(2, columnar.get(count, 1));
  }
  @Test @Tag("Q4")
  public void getRightTypeString() {
    var string = Columnar.column("string", String.class);
    var columnar = Columnar.of(string);
    columnar.addValues("foo");
    columnar.addValues("bar");
    String text = columnar.get(string, 0);
    assertEquals("foo", text);
  }
  @Test @Tag("Q4")
  public void getRightTypeInteger() {
    var integer = Columnar.column("integer", Integer.class);
    var columnar = Columnar.of(integer);
    columnar.addValues(407);
    columnar.addValues(774);
    int value = columnar.get(integer, 0);
    assertEquals(407, value);
  }
  @Test @Tag("Q4")
  public void getALot() {
    var value = Columnar.column("value", Integer.class);
    var text = Columnar.column("text", String.class);
    var columnar = Columnar.of(value, text);
    range(0, 1_000_000).forEach(i -> columnar.addValues(i, "" + i));
    range(0, 1_000_000).forEach(i -> {
      assertEquals(i, columnar.get(value, i));
      assertEquals("" + i, columnar.get(text, i));
    });
  }
  @Test @Tag("Q4")
  public void getSignature() {
    var data = Columnar.column("data", Double.class);
    var columnar = Columnar.of(data);
    columnar.addValues(32.5);
    var number = columnar.<Number>get(data, 0);
  }
  @Test @Tag("Q4")
  public void getUnattachedColumn() {
    var attached = Columnar.column("attached", String.class);
    var notAttached = Columnar.column("notAttached", String.class);
    var columnar = Columnar.of(attached);
    columnar.addValues("foo");
    assertThrows(IllegalStateException.class, () -> columnar.get(notAttached, 0));
  }
  @Test @Tag("Q4")
  public void getColumnFromAnotherColumnar() {
    var col1 = Columnar.column("col1", String.class);
    var col2 = Columnar.column("col2", String.class);
    var columnar1 = Columnar.of(col1);
    var columnar2 = Columnar.of(col2);
    columnar1.addValues("foo");
    assertThrows(IllegalStateException.class, () -> columnar1.get(col2, 0));
  }
  @Test @Tag("Q4")
  public void getPreconditions() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    columnar.addValues(42);
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> columnar.get(null, 0)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> columnar.get(value, -1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> columnar.get(value, 1))
    );
  }


  @Test @Tag("Q5")
  public void addValuesList() {
    var name = Columnar.column("pet", String.class);
    var count = Columnar.column("count", Integer.class);
    var columnar = Columnar.of(name, count);
    columnar.addValues(List.of("dog", 1));
    columnar.addValues(List.of("cat", 2));
    assertEquals(2, columnar.size());
  }
  @Test @Tag("Q5")
  public void addValuesOfDouble() {
    var data = Columnar.column("data", Double.class);
    var columnar = Columnar.of(data);
    columnar.addValues(List.of(45.7));
    columnar.addValues(List.of(-121.7));
    columnar.addValues(List.of(564.4));
    assertEquals(3, columnar.size());
  }
  @Test @Tag("Q5")
  public void addValuesListWithNull() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    columnar.addValues(singletonList(null));
  }
  @Test @Tag("Q5")
  public void addValuesListALot() {
    var value = Columnar.column("value", Integer.class);
    var text = Columnar.column("text", String.class);
    var columnar = Columnar.of(value, text);
    range(0, 1_000_000).forEach(i -> columnar.addValues(List.of(i, "" + i)));
    assertEquals(1_000_000, columnar.size());
  }
  @Test @Tag("Q5")
  public void addValuesListWrongNumberOfColumn() {
    var column = Columnar.column("column", String.class);
    var columnar = Columnar.of(column);
    assertThrows(IllegalArgumentException.class, () -> columnar.addValues(List.of("arg1", "arg2")));
  }
  @Test @Tag("Q5")
  public void addValuesListWrongType() {
    var value = Columnar.column("count", Integer.class);
    var columnar = Columnar.of(value);
    assertThrows(ClassCastException.class, () -> columnar.addValues(List.of("oops")));
  }
  @Test @Tag("Q5")
  public void addValuesListPreconditions() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    assertThrows(NullPointerException.class, () -> columnar.addValues((List<Object>)null));
  }
  @Test @Tag("Q5")
  public void addValuesLinkedList() {
    var columns = range(0, 1_000_000)
        .mapToObj(i -> Columnar.column("" + i, Integer.class))
        .toArray(Column[]::new);
    var columnar = Columnar.of(columns);
    var list = range(0, 1_000_000).boxed().collect(toCollection(LinkedList::new));
    assertTimeoutPreemptively(Duration.ofMillis(2_000), () -> columnar.addValues(list));
  }


  @Test @Tag("Q6")
  public void getValues() {
    var name = Columnar.column("pet", String.class);
    var count = Columnar.column("count", Integer.class);
    var columnar = Columnar.of(name, count);
    var dogs = List.of("dog", 1);
    var cats = List.of("cat", 2);
    columnar.addValues(dogs);
    columnar.addValues(cats);
    assertAll(
        () -> assertEquals(dogs, columnar.getValues(0)),
        () -> assertEquals(cats, columnar.getValues(1))
    );
  }
  @Test @Tag("Q6")
  public void getValuesALot() {
    var value = Columnar.column("value", Integer.class);
    var text = Columnar.column("text", String.class);
    var columnar = Columnar.of(value, text);
    range(0, 1_000_000).forEach(i -> columnar.addValues(i, "" + i));
    range(0, 1_000_000).forEach(i -> assertEquals(List.of(i, "" + i), columnar.getValues(i)));
  }
  @Test @Tag("Q6")
  public void getValuesNonMutable() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    columnar.addValues(42);
    var list = columnar.getValues(0);
    assertThrows(UnsupportedOperationException.class, () -> list.add(null));
  }
  @Test @Tag("Q6")
  public void getValuesRandomAccess() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    columnar.addValues(42);
    var list = columnar.getValues(0);
    assertTrue(list instanceof RandomAccess);
  }
  @Test @Tag("Q6")
  public void getValuesPreconditions() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    columnar.addValues(42);
    var list = columnar.getValues(0);
    assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> columnar.getValues(-1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> columnar.getValues(1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(1))
    );
  }


  @Test  @Tag("Q7")
  public void filter() {
    var name = Columnar.column("name", String.class);
    var age = Columnar.column("age", Integer.class);
    var columnar = Columnar.of(name, age);
    columnar.addValues("bob", 32);
    columnar.addValues("ana", 23);
    columnar.addValues("maria", 44);
    var filtered = columnar.filter(name, s -> s.endsWith("a"));
    assertAll(
        () -> assertEquals(2, filtered.size()),
        () -> assertEquals(List.of("ana", 23), filtered.getValues(0)),
        () -> assertEquals(List.of("maria", 44), filtered.getValues(1))
    );
  }
  @Test  @Tag("Q7")
  public void filterFilter() {
    var name = Columnar.column("name", String.class);
    var age = Columnar.column("age", Integer.class);
    var columnar = Columnar.of(name, age);
    columnar.addValues("bob", 32);
    columnar.addValues("ana", 23);
    columnar.addValues("maria", 44);
    var filtered = columnar
        .filter(name, s -> s.endsWith("a"))
        .filter(age, a -> a == 44);
    assertAll(
        () -> assertEquals(1, filtered.size()),
        () -> assertEquals(List.of("maria", 44), filtered.getValues(0))
    );
  }
  @Test  @Tag("Q7")
  public void filterALot() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    range(0, 1_000_000).forEach(columnar::addValues);
    var filtered = columnar.filter(value, v -> v % 2 == 0);
    assertEquals(
        range(0, 1_000_000).filter(v -> v % 2 == 0).boxed().collect(toList()),
        range(0, filtered.size()).map(i -> filtered.get(value, i)).boxed().collect(toList()));
  }
  @Test @Tag("Q7")
  public void filterSignature1() {
    var data = Columnar.column("data", Double.class);
    var columnar = Columnar.of(data);
    columnar.addValues(32.5);
    var filtered = columnar.<Number>filter(data, __ -> true);
    assertNotNull(filtered);
  }
  @Test @Tag("Q7")
  public void filterSignature2() {
    var data = Columnar.column("data", Double.class);
    var columnar = Columnar.of(data);
    columnar.addValues(32.5);
    var filtered = columnar.<Double>filter(data, (Object o) -> true);
    assertNotNull(filtered);
  }
  @Test  @Tag("Q7")
  public void filterNonMutableValue() {
    var name = Columnar.column("name", String.class);
    var age = Columnar.column("age", Integer.class);
    var columnar = Columnar.of(name, age);
    columnar.addValues("bob", 32);
    columnar.addValues("ana", 23);
    columnar.addValues("maria", 44);
    var filtered = columnar.filter(age, a -> a % 2 == 0);
    assertThrows(UnsupportedOperationException.class, () -> filtered.addValues("Ella", 24));
  }
  @Test  @Tag("Q7")
  public void filterNonMutableValueList() {
    var name = Columnar.column("name", String.class);
    var age = Columnar.column("age", Integer.class);
    var columnar = Columnar.of(name, age);
    columnar.addValues("bob", 32);
    columnar.addValues("ana", 23);
    columnar.addValues("maria", 44);
    var filtered = columnar.filter(age, a -> a % 2 == 0);
    assertThrows(UnsupportedOperationException.class, () -> filtered.addValues(List.of("Ella", 24)));
  }
  @Test  @Tag("Q7")
  public void filterNonAttachedColumn() {
    var attached = Columnar.column("attached", Integer.class);
    var nonAttached = Columnar.column("nonAttached", Integer.class);
    var columnar = Columnar.of(attached);
    columnar.addValues(747);
    assertThrows(IllegalStateException.class, () -> columnar.filter(nonAttached, __ -> true));
  }
  @Test  @Tag("Q7")
  public void filterColumnFromAnotherColumnar() {
    var col1 = Columnar.column("col1", Integer.class);
    var col2 = Columnar.column("col2", Integer.class);
    var columnar1 = Columnar.of(col1);
    var columnar2 = Columnar.of(col2);
    columnar1.addValues(747);
    assertThrows(IllegalStateException.class, () -> columnar1.filter(col2, __ -> true));
  }
  @Test  @Tag("Q7")
  public void filterDoNotDoSideEffect() {
    var value = Columnar.column("value", Integer.class);
    var columnar1 = Columnar.of(value);
    range(0, 10).forEach(columnar1::addValues);
    var columnar2 = columnar1.filter(value, v -> v % 2 == 0);
    assertNotNull(columnar2);
    for(var i = 0; i < columnar1.size(); i++) {
      assertEquals(i, columnar1.get(value, i));
    }
  }
  @Test  @Tag("Q7")
  public void filterPreconditions() {
    var value = Columnar.column("value", Integer.class);
    var columnar = Columnar.of(value);
    columnar.addValues(747);
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> columnar.filter(null, __ -> true)),
        () -> assertThrows(NullPointerException.class, () -> columnar.filter(value, null))
    );
  }


//  @Test @Tag("Q8")
//  public void forEachLoop() {
//    var name = Columnar.column("name", String.class);
//    var age = Columnar.column("age", Integer.class);
//    var columnar = Columnar.of(name, age);
//    columnar.addValues("bob", 32);
//    columnar.addValues("ana", 23);
//    columnar.addValues("maria", 44);
//
//    var name2 = Columnar.column("name", String.class);
//    var age2 = Columnar.column("age", Integer.class);
//    var columnar2 = Columnar.of(name2, age2);
//    for(var values: columnar) {
//      columnar2.addValues(values);
//    }
//
//    assertEquals(List.of("bob", 32), columnar2.getValues(0));
//    assertEquals(List.of("ana", 23), columnar2.getValues(1));
//    assertEquals(List.of("maria", 44), columnar2.getValues(2));
//  }
//  @Test  @Tag("Q8")
//  public void forEachLoopALot() {
//    var value = Columnar.column("value", Integer.class);
//    var columnar = Columnar.of(value);
//    range(0, 1_000_000).forEach(columnar::addValues);
//    var i = 0;
//    for(var list: columnar) {
//      assertEquals(i++, list.get(0));
//    }
//  }
//  @Test @Tag("Q8")
//  public void forEachLoopEmpty() {
//    var data = Columnar.column("data", Double.class);
//    var columnar = Columnar.of(data);
//    for(var values: columnar) {
//      fail();
//    }
//  }
//  @Test @Tag("Q8")
//  public void forEachLoopDoeNotDoSideEffect() {
//    var value = Columnar.column("value", Integer.class);
//    var columnar = Columnar.of(value);
//    range(0, 10).forEach(columnar::addValues);
//    var i = 0;
//    for(var values: columnar) {
//      assertEquals(i++, values.get(0));
//    }
//    for(i = 0; i < columnar.size(); i++) {
//      assertEquals(i, columnar.getValues(i).get(0));
//    }
//  }
//  @Test @Tag("Q8")
//  public void forEachLoopFiltered() {
//    var name = Columnar.column("name", String.class);
//    var age = Columnar.column("age", Integer.class);
//    var columnar = Columnar.of(name, age);
//    columnar.addValues("bob", 32);
//    columnar.addValues("ana", 23);
//    columnar.addValues("maria", 44);
//    var columnar2 = columnar.filter(age, a -> a < 30);
//    for(var values: columnar2) {
//     assertEquals(List.of("ana", 23), values);
//    }
//  }
//  @Test @Tag("Q8")
//  public void forEachLoopFilteredEmpty() {
//    var value = Columnar.column("value", Integer.class);
//    var columnar = Columnar.of(value);
//    range(0, 10).forEach(columnar::addValues);
//    var columnar2 = columnar.filter(value, v -> v > 10);
//    for(var values: columnar2) {
//      fail();
//    }
//  }
//  @Test  @Tag("Q8")
//  public void forEachIteratorNonMutable() {
//    var value = Columnar.column("value", Double.class);
//    var columnar = Columnar.of(value);
//    columnar.addValues(45.0);
//    columnar.addValues(-121.0);
//    columnar.addValues(78.0);
//    var it = columnar.iterator();
//    assertEquals(List.of(45.0), it.next());
//    assertThrows(UnsupportedOperationException.class, it::remove);
//  }
//  @Test  @Tag("Q8")
//  public void forEachIteratorSnapshotAtTheBeginning() {
//    var value = Columnar.column("value", Double.class);
//    var columnar = Columnar.of(value);
//    columnar.addValues(145.0);
//    var it = columnar.iterator();
//    assertEquals(List.of(145.0), it.next());
//    columnar.addValues(-21.0);
//    assertFalse(it.hasNext());
//    assertThrows(NoSuchElementException.class, it::next);
//  }
//
//
//  @Test  @Tag("Q9")
//  public void columnarOfPrimitiveType() {
//    var name = Columnar.column("name", String.class);
//    var age = Columnar.column("age", int.class);  // primitive type !!
//    var columnar = Columnar.of(name, age);
//    columnar.addValues("bob", 32);
//    columnar.addValues("ana", 23);
//    columnar.addValues("maria", 44);
//    var filtered = columnar.filter(name, s -> s.endsWith("a"));
//    assertAll(
//        () -> assertEquals(2, filtered.size()),
//        () -> assertEquals(List.of("ana", 23), filtered.getValues(0)),
//        () -> assertEquals(List.of("maria", 44), filtered.getValues(1))
//    );
//  }
//  @Test @Tag("Q9")
//  public void columnarOfPrimitiveTypeOfDouble() {
//    var data = Columnar.column("data", double.class);
//    var columnar = Columnar.of(data);
//    columnar.addValues(45);
//    columnar.addValues(-121);
//    columnar.addValues(564);
//    assertAll(
//        () -> assertEquals(3, columnar.size()),
//        () -> assertEquals(45, columnar.get(data, 0)),
//        () -> assertEquals(-121, columnar.get(data, 1)),
//        () -> assertEquals(564, columnar.get(data, 2))
//    );
//  }
//  @Test  @Tag("Q9")
//  public void columnarOfPrimitiveTypeALot() {
//    var value = Columnar.column("value", int.class);
//    var columnar = Columnar.of(value);
//    range(0, 1_000_000).forEach(columnar::addValues);
//    var filtered = columnar.filter(value, v -> v % 7 == 0);
//    var i = 0;
//    for(var values: filtered) {
//      assertEquals(i, values.get(0));
//      i += 7;
//    }
//  }
//  @Test  @Tag("Q9")
//  public void columnarOfPrimitiveTypeDoNotAcceptNull() {
//    var data = Columnar.column("data", double.class);
//    var columnar = Columnar.of(data);
//    assertThrows(NullPointerException.class, () -> columnar.addValues((Object)null));
//  }
}
