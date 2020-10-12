package fr.umlv.set;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.IntStream;

import org.junit.jupiter.api.*;

@SuppressWarnings("static-method")
public class IntHashSetTest {
  // Q3

  @Test @Tag("Q3")
  public void shouldAddOne() {
    var set = new IntHashSet();
    set.add(1);
    assertEquals(1, set.size());
  }
  @Test @Tag("Q3")
  public void shouldAddAnInteger() {
    var set = new IntHashSet();
    set.add(31_133);
    assertEquals(1, set.size());
  }
  @Test @Tag("Q3")
  public void shouldAddWithoutErrors() {
    var set = new IntHashSet();
    IntStream.range(0, 100).map(i -> i * 2 + 1).forEach(set::add);
    assertEquals(100, set.size());
  }
  @Test @Tag("Q3")
  public void shouldNotTakeTooLongToAddTheSameNumberMultipleTimes() {
    var set = new IntHashSet();
    assertTimeoutPreemptively(Duration.ofMillis(5_000), () -> IntStream.range(0, 1_000_000).map(i -> 42).forEach(set::add));
    assertEquals(1, set.size());
  }
  @Test @Tag("Q3")
  public void shouldAnswerZeroWhenAskingForSizeOfEmptySet() {
    var set = new IntHashSet();
    assertEquals(0, set.size());
  }
  @Test @Tag("Q3")
  public void shouldNotAddTwiceTheSameAndComputeSizeAccordingly() {
    var set = new IntHashSet();
    set.add(3);
    assertEquals(1, set.size());
    set.add(-777);
    assertEquals(2, set.size());
    set.add(3);
    assertEquals(2, set.size());
    set.add(-777);
    assertEquals(2, set.size());
  }


  // Q5

  @Test @Tag("Q5")
  public void shouldDoNoThingWhenForEachCalledOnEmptySet() {
    var set = new IntHashSet();
    set.forEach(__ -> fail("should not be called"));
  }
  @Test @Tag("Q5")
  public void shouldCompteTheSumOfAllTheElementsInASetUsingForEachAngGetTheSameAsTheFormula() {
    var length = 100;
    var set = new IntHashSet();
    IntStream.range(0, length).forEach(set::add);
    var sum = new int[] { 0 };
    set.forEach(value -> sum[0] += value);
    assertEquals(length * (length - 1) / 2, sum[0]);
  }
  @Test @Tag("Q5")
  public void shouldComputeIndenticalSetAndHashSetUsingForEachAndHaveSameSize() {
    var set = new IntHashSet();
    IntStream.range(0, 100).forEach(set::add);
    var hashSet = new HashSet<Integer>();
    set.forEach(hashSet::add);
    assertEquals(set.size(), hashSet.size());
  }
  @Test @Tag("Q5")
  public void shouldAddAllTheElementsOfASetToAListUsingForEach() {
    var set = new IntHashSet();
    IntStream.range(0, 100).forEach(set::add);
    var list = new ArrayList<Integer>();
    set.forEach(list::add);
    list.sort(null);
    IntStream.range(0, 100).forEach(i -> assertEquals(i, list.get(i)));
  }
  @Test @Tag("Q5")
  public void shouldNotUseNullAsAParameterForForEach() {
    var set = new IntHashSet();
    assertThrows(NullPointerException.class, () -> set.forEach(null));
  }
  @Test @Tag("Q5")
  public void shouldNotUseNullAsAParameterForForEach2() {
    var set = new IntHashSet();
    set.add(4);
    assertThrows(NullPointerException.class, () -> set.forEach(null));
  }


  // Q6

  @Test @Tag("Q6")
  public void shouldNotFindAnythingContainedInAnEmptySet() {
    var set = new IntHashSet();
    assertFalse(set.contains(4));
    assertFalse(set.contains(7));
    assertFalse(set.contains(1));
    assertFalse(set.contains(0));
    assertEquals(0, set.size());
  }
  @Test @Tag("Q6")
  public void shouldNotFindAnIntegerBeforeAddingItButShouldFindItAfter() {
    var set = new IntHashSet();
    for(int i = 0; i < 10; i++) {
      assertFalse(set.contains(i));
      set.add(i);
      assertTrue(set.contains(i));
    }
    assertEquals(10, set.size());
  }
  @Test @Tag("Q6")
  public void shoulAddAndTestContainsForAnExtremalValue() {
    var set = new IntHashSet();
    assertFalse(set.contains(Integer.MIN_VALUE));
    set.add(Integer.MIN_VALUE);
    assertTrue(set.contains(Integer.MIN_VALUE));
    set.add(Integer.MAX_VALUE);
    assertTrue(set.contains(Integer.MAX_VALUE));
    assertEquals(2, set.size());
  }
}
