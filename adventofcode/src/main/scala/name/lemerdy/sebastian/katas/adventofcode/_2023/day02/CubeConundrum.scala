package name.lemerdy.sebastian.katas.adventofcode._2023.day02

import scala.math.max

object CubeConundrum:
  private case class Bag(red: Int, green: Int, blue: Int):
    def isPossible: Boolean = red <= 12 && green <= 13 && blue <= 14
    def power: Int = red * green * blue
  private object Bag:
    private val regex = """(\d+) (.+)""".r
    def apply(bags: String): Bag =
      bags
        .split(',')
        .map(_.trim)
        .map(colorToBag)
        .fold(Bag(0, 0, 0))((b1, b2) => Bag(b1.red + b2.red, b1.green + b2.green, b1.blue + b2.blue))
    private def colorToBag(color: String): Bag = color match
      case regex(count, "red")   => Bag(count.toInt, 0, 0)
      case regex(count, "green") => Bag(0, count.toInt, 0)
      case regex(count, "blue")  => Bag(0, 0, count.toInt)
  private case class Game(id: Int, bags: Seq[Bag]):
    private def maxBag: Bag = bags
      .fold(Bag(0, 0, 0))((b1, b2) => Bag(max(b1.red, b2.red), max(b1.green, b2.green), max(b1.blue, b2.blue)))
    def isPossible: Boolean = maxBag.isPossible
    def power: Int = maxBag.power
  private object Game:
    private val regex = """Game (\d+): (.+)""".r
    def apply(line: String): Game = line match
      case regex(id, bags) => Game(id.toInt, bags.split(';').toIndexedSeq.map(Bag.apply))

  private def games(input: String): Seq[Game] = input.split('\n').map(_.trim).toIndexedSeq.map(Game.apply)

  def sum(input: String): Int = games(input).filter(_.isPossible).map(_.id).sum

  def power(input: String): Int = games(input).map(_.power).sum
