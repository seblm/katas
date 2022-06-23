package kata.function.reuse

import kata.function.reuse.FunctionReuse.{Applicative, Apply, Functor}

import java.util.UUID
import scala.util.{Failure, Success, Try}

type ItemId = UUID
type SellerId = UUID

case class Item(id: ItemId, name: String, price: Int, sellerId: SellerId)

case object Item:

  def affordable(item: Item): Boolean = item.price < 500

  def affordableF[F[_]: Functor](fitem: F[Item]): F[Boolean] = fitem.map(affordable)

  def cheapest(item1: Item, item2: Item): Item = if item1.price > item2.price then item2 else item1

  def cheapestF[F[_]: Apply](fitem1: F[Item], fitem2: F[Item]): F[Item] = (fitem1, fitem2).map2(cheapest)

  def totalCost(items: List[Item]): Int = items match
    case head :: tail => head.price + totalCost(tail)
    case Nil          => 0

  def totalCostF[F[_]: Applicative](fitems: List[F[Item]]): F[Int] = fitems.flip.map(totalCost)
