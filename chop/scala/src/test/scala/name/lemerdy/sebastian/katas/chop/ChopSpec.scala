package name.lemerdy.sebastian.katas.chop

import org.scalatest.{FlatSpec, Matchers}

class ChopSpec extends FlatSpec with Matchers {

  "chop" should "returns -1 if array is empty" in {
    new Chop().chop(3, Array()) shouldBe -1
  }

  it should "returns -1 if not found" in {
    new Chop().chop(3, Array(1)) shouldBe -1
  }

  it should "returns 0 if this is the first element" in {
    new Chop().chop(1, Array(1)) shouldBe 0
  }

  it should "works with an array of 3 elements" in {
    new Chop().chop(1, Array(1, 3, 5)) shouldBe 0
    new Chop().chop(3, Array(1, 3, 5)) shouldBe 1
    new Chop().chop(5, Array(1, 3, 5)) shouldBe 2
    new Chop().chop(0, Array(1, 3, 5)) shouldBe -1
    new Chop().chop(2, Array(1, 3, 5)) shouldBe -1
    new Chop().chop(4, Array(1, 3, 5)) shouldBe -1
    new Chop().chop(6, Array(1, 3, 5)) shouldBe -1
  }

  it should "works with an array of 4 elements" in {
    new Chop().chop(1, Array(1, 3, 5, 7)) shouldBe 0
    new Chop().chop(3, Array(1, 3, 5, 7)) shouldBe 1
    new Chop().chop(5, Array(1, 3, 5, 7)) shouldBe 2
    new Chop().chop(7, Array(1, 3, 5, 7)) shouldBe 3
    new Chop().chop(0, Array(1, 3, 5, 7)) shouldBe -1
    new Chop().chop(2, Array(1, 3, 5, 7)) shouldBe -1
    new Chop().chop(4, Array(1, 3, 5, 7)) shouldBe -1
    new Chop().chop(6, Array(1, 3, 5, 7)) shouldBe -1
    new Chop().chop(8, Array(1, 3, 5, 7)) shouldBe -1
  }

}
