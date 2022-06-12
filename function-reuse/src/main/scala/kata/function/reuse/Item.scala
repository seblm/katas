package kata.function.reuse

import java.util.UUID

type ItemId = UUID
type SellerId = UUID

case class Item(id: ItemId, name: String, price: Int, sellerId: SellerId)

case object Item:

  def affordable(item: Item): Boolean = ???
