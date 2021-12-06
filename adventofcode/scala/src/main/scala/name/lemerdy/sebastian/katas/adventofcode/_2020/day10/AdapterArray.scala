package name.lemerdy.sebastian.katas.adventofcode._2020.day10

import scala.annotation.tailrec
import scala.collection.mutable

object AdapterArray {

  def computeChain(adapters: String): Int = computeChain(adapters.split('\n').map(_.toInt).sorted.toList).product

  case class Differences(diff1: Int, diff3: Int) {
    def addDiff1(): Differences = Differences(diff1 + 1, diff3)
    def addDiff3(): Differences = Differences(diff1, diff3 + 1)
    lazy val product: Int = diff1 * (diff3 + 1)
  }

  @tailrec
  private def computeChain(adapters: List[Int], differences: Differences = Differences(0, 0)): Differences =
    adapters match {
      case Nil       => differences
      case 1 :: tail => computeChain(tail.map(_ - 1), differences.addDiff1())
      case 3 :: tail => computeChain(tail.map(_ - 3), differences.addDiff3())
      case _         => Differences(-1, -1)
    }

  def count(adapters: String): Long = count(adapters.split('\n').map(_.toInt).sorted.toList)

  private def memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O]() {
    override def apply(key: I): O = getOrElseUpdate(key, f(key))
  }

  private lazy val count: List[Int] => Long = memoize {
    case 1 :: 2 :: 3 :: tail => count(1 :: 2 :: tail.map(_ - 1)) + count(1 :: tail.map(_ - 2)) + count(tail.map(_ - 3))
    case 1 :: 3 :: tail      => count(2 :: tail.map(_ - 1)) + count(tail.map(_ - 3))
    case 1 :: 2 :: tail      => count(1 :: tail.map(_ - 1)) + count(tail.map(_ - 2))
    case 1 :: tail           => count(tail.map(_ - 1))
    case 3 :: tail           => count(tail.map(_ - 3))
    case Nil                 => 1
    case _                   => 0
  }

}
