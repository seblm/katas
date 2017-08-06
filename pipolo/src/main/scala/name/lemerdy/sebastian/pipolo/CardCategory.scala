package name.lemerdy.sebastian.pipolo

sealed trait CardCategory

object CardCategory {
  val category0: CardCategory = new CardCategory {
    override val toString: String = "category0"
  }
  val category1: CardCategory = new CardCategory {
    override val toString: String = "category1"
  }
  val category2: CardCategory = new CardCategory {
    override val toString: String = "category2"
  }
  val category3: CardCategory = new CardCategory {
    override val toString: String = "category3"
  }
}
