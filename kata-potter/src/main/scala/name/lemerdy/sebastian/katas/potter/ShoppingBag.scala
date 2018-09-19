package name.lemerdy.sebastian.katas.potter

case class ShoppingBag(books: List[Int]) {

  def price: BigDecimal = {
    val discount: BigDecimal = books.size match {
      case 1 ⇒ 0
      case 2 ⇒.05
      case 3 ⇒.1
      case 4 ⇒.2
      case 5 ⇒.25
    }
    books.size * 8 * (1 - discount)
  }

  def add(book: Int): ShoppingBag = copy(books = books :+ book)

}
