package mowitnow.jocelyn.lhommee

import munit.FunSuite

class Multiplication2 extends FunSuite {

  def times2(arg: Int): Int =
    return arg * 2

  def addOne(arg: Int): Int =
    return arg + 1

  def times2addOne(arg: Int): Int =
    return (times2 andThen addOne)(arg)

  test("Multiplication2"):
    assertEquals(
      obtained = times2(2),
      expected = 4
    )

  test("Addition1"):
    assertEquals(
      obtained = addOne(2),
      expected = 3
    )

  test("Composition"):
    assertEquals(
      obtained = times2addOne(2),
      expected = 5
    )
}
