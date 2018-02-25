package name.lemerdy.sebastian.katas.potter

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

    /*
     0  0  0  0      0  0     0  0   0  0      0  0    0  0
     1     1         1        1  1   1  1      1  1    1  1
     2        2      2        2      2         2  2    2  2
                        3     3         3      3       3
                                                  4    4

     .1 0 .05.05    .1  0    .1 .1  .2 .05    .2.2   .25 .1
      .1    .1        .1       .2     .25      .4      .35
    * */

    price(List(
      0, 0, 0, 0, 0,
      1, 1, 1, 1, 1,
      2, 2, 2, 2,
      3, 3, 3, 3, 3,
      4, 4, 4,    4)) should be(3 * (8 * 5 * BigDecimal(.75)) + 2 * (8 * 4 * BigDecimal(.8)))
  }

  private def price(books: List[Int]): BigDecimal = {

    println(books + " " + books.combinations(books.))

    val byBook: Map[Int, List[Int]] = books.groupBy(identity)

    def priceRec(byBook: Map[Int, List[Int]]): List[List[Int]] = {
      val heads = byBook.flatMap { case (_, b) ⇒ b.headOption }.toList
      val tails = byBook.filter { case (_, b) ⇒ b.tail.nonEmpty }.mapValues(_.tail)

      if (heads.isEmpty) {
        Nil
      } else if (tails.isEmpty) {
        List(heads)
      } else {
        heads :: priceRec(tails)
      }
    }

    val shoppingBags = priceRec(byBook)

    def priceForAShoppingBag(books: List[Int]): BigDecimal = {
      val discount: BigDecimal = books.size match {
        case 1 ⇒ 0
        case 2 ⇒ .05
        case 3 ⇒ .1
        case 4 ⇒ .2
        case 5 ⇒ .25
      }
      books.size * 8 * (1 - discount)
    }

    if (shoppingBags.size > 1) {
      val reversed = shoppingBags.reverse
      val beforeLast = reversed.tail.head
      val last = reversed.head
      if (last.size == beforeLast.size - 2) {
        println("EDGE CASE")
      }
    }

    shoppingBags.map(priceForAShoppingBag).sum
  }

}
