package name.lemerdy.sebastian.katas.potter

object KattaPotter {

  def price(books: List[Int]): BigDecimal = {
    val shoppingBags = createShoppingBags(books, List.empty)
    val optimized = threeAndFiveToTwoFour(shoppingBags)
    optimized.map(_.price).sum
  }

  private def createShoppingBags(books: List[Int], shoppingBags: List[ShoppingBag]): List[ShoppingBag] =
    books match {
      case Nil ⇒ shoppingBags
      case book :: rest ⇒
        val (with_, without) = shoppingBags.partition(_.books.contains(book))
        val shoppingBagsWithBook = without match {
          case Nil ⇒ List(ShoppingBag(List(book)))
          case shoppingBag :: others ⇒ List(shoppingBag.add(book)) ++ others
        }
        createShoppingBags(rest, with_ ++ shoppingBagsWithBook)
    }

  private def threeAndFiveToTwoFour(bags: List[ShoppingBag]): List[ShoppingBag] = {
    val three = bags.find(bag => bag.books.size == 3)
    val five = bags.find(bag => bag.books.size == 5)
    (three, five) match {
      case (Some(t), Some(f)) ⇒ bags.diff(List(t, f)) ++ moveFirstBook(f, t)
      case _ ⇒ bags
    }
  }

  private def moveFirstBook(from: ShoppingBag, to: ShoppingBag): List[ShoppingBag] =
    from.books match {
      case Nil ⇒ List(from, to)
      case book :: rest ⇒ List(from.copy(books = rest), to.add(book))
    }

}
