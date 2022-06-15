package kata.function.reuse

import java.util.UUID

type ItemId = UUID
type SellerId = UUID

case class Item(id: ItemId, name: String, price: Int, sellerId: SellerId)

case object Item:

  def affordable(item: Item): Boolean = item.price < 500

  def affordable(oitem: Option[Item]): Option[Boolean] = oitem match
    case Some(item) => Some(affordable(item))
    case None       => None
