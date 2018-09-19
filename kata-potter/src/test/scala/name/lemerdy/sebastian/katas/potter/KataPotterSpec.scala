package name.lemerdy.sebastian.katas.potter

import name.lemerdy.sebastian.katas.potter.KattaPotter.price
import org.scalatest.{FlatSpec, Matchers}

class KataPotterSpec extends FlatSpec with Matchers {

  "basics" should "works" in {
    price(List.empty) should be(BigDecimal(0))
    price(List(0)) should be(BigDecimal(8))
    price(List(1)) should be(BigDecimal(8))
    price(List(2)) should be(BigDecimal(8))
    price(List(3)) should be(BigDecimal(8))
    price(List(4)) should be(BigDecimal(8))
    price(List(0, 0)) should be(BigDecimal(8) * 2)
    price(List(1, 1, 1)) should be(BigDecimal(8) * 3)
  }

  "simple discounts" should "works" in {
    price(List(0, 1)) should be(8 * 2 * BigDecimal(.95))
    price(List(0, 2, 4)) should be(8 * 3 * BigDecimal(.9))
    price(List(0, 1, 2, 4)) should be(8 * 4 * BigDecimal(.8))
    price(List(0, 1, 2, 3, 4)) should be(8 * 5 * BigDecimal(.75))
  }

  "several discounts" should "works" in {
    price(List(0, 0, 1)) should be(8 + 8 * 2 * BigDecimal(.95))
    price(List(0, 0, 1, 1)) should be(2 * 8 * 2 * BigDecimal(.95))
    price(List(0, 0, 1, 2, 2, 3)) should be((8 * 4 * BigDecimal(.8)) + (8 * 2 * BigDecimal(.95)))
    price(List(0, 1, 1, 2, 3, 4)) should be(8 + (8 * 5 * BigDecimal(.75)))
  }

  "edge cases" should "works" in {
    price(List(0, 0, 1, 1, 2, 2, 3, 4)) should be(2 * (8 * 4 * BigDecimal(.8)))
    price(List(
      0, 0, 0, 0, 0,
      1, 1, 1, 1, 1,
      2, 2, 2, 2,
      3, 3, 3, 3, 3,
      4, 4, 4,    4)) should be(3 * (8 * 5 * BigDecimal(.75)) + 2 * (8 * 4 * BigDecimal(.8)))
  }

}
