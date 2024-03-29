package name.lemerdy.sebastian.katas.adventofcode._2015.day1

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

class NotQuiteLispSpec extends AnyFlatSpec:

  private val notQuiteLisp = new NotQuiteLisp()

  "santaLastLevel" should "goes up" in {
    notQuiteLisp.santaLastLevel("(") shouldBe 1
  }

  it should "goes up twice" in {
    notQuiteLisp.santaLastLevel("((") shouldBe 2
  }

  it should "goes down" in {
    notQuiteLisp.santaLastLevel(")") shouldBe -1
  }

  it should "result in floor 0" in {
    notQuiteLisp.santaLastLevel("(())") shouldBe 0
    notQuiteLisp.santaLastLevel("()()") shouldBe 0
  }

  it should "result in floor 3" in {
    notQuiteLisp.santaLastLevel("(((") shouldBe 3
    notQuiteLisp.santaLastLevel("(()(()(") shouldBe 3
    notQuiteLisp.santaLastLevel("))(((((") shouldBe 3
  }

  it should "result in floor -1" in {
    notQuiteLisp.santaLastLevel("())") shouldBe -1
    notQuiteLisp.santaLastLevel("))(") shouldBe -1
  }

  it should "result in floor -3" in {
    notQuiteLisp.santaLastLevel(")))") shouldBe -3
    notQuiteLisp.santaLastLevel(")())())") shouldBe -3
  }

  "santaPositionForBasement" should "be 1" in {
    notQuiteLisp.santaPositionForBasement(")") shouldBe 1
  }

  it should "be 5" in {
    notQuiteLisp.santaPositionForBasement("()())") shouldBe 5
  }
