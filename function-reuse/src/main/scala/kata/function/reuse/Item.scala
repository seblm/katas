package kata.function.reuse

import kata.function.reuse.FunctionReuse.Functor

import java.util.UUID
import scala.util.{Failure, Success, Try}

type ItemId = UUID
type SellerId = UUID

case class Item(id: ItemId, name: String, price: Int, sellerId: SellerId)

case object Item:

  def affordable(item: Item): Boolean = item.price < 500

  def affordableF[F[_]: Functor](fitem: F[Item]): F[Boolean] = fitem.map(affordable)
