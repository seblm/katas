package name.lemerdy.sebastian.katas.adventofcode._2023.day03

import scala.annotation.tailrec

object GearRatios:
  private case class Number(value: Int):
    def isAdjacent(symbols: Seq[MapElement], x: Int, y: Int): Boolean =
      val top = Range(x - 1, x + 1 + value.toString.length).map((_, y - 1))
      val bottom = Range(x - 1, x + 1 + value.toString.length).map((_, y + 1))
      val left = (x - 1, y)
      val right = (x + value.toString.length, y)
      val coords = top ++ bottom :+ left :+ right
      symbols.exists(element => coords.contains(element.x, element.y))

  private case class Symbol(char: Char):
    def adjacentNumbers(numbers: Seq[MapElement], x: Int, y: Int): Seq[Number] =
      numbers.flatMap(element =>
        element.element match
          case number: Number =>
            if number.isAdjacent(Seq(MapElement(this, x, y)), element.x, element.y) then Some(number) else None
          case _ => None
      )

  private case class MapElement(element: Number | Symbol, x: Int, y: Int)

  private val number = """(\d+)(.*)""".r
  private val period = """\.(.*)""".r
  private val other = """(.)(.*)""".r

  @tailrec
  private def lineToMapElements(line: String, y: Int)(x: Int = 0, acc: Seq[MapElement] = Seq.empty): Seq[MapElement] =
    line match
      case ""           => acc
      case period(rest) => lineToMapElements(rest, y)(x + 1, acc)
      case number(value, rest) =>
        lineToMapElements(rest, y)(x + value.length, acc :+ MapElement(Number(value.toInt), x, y))
      case other(value, rest) => lineToMapElements(rest, y)(x + 1, acc :+ MapElement(Symbol(value.head), x, y))

  def sum(input: String): Int =
    val (symbols, numbers) = readMap(input).partition(_.element.isInstanceOf[Symbol])
    numbers
      .map: element =>
        val number = element.element.asInstanceOf[Number]
        if number.isAdjacent(symbols.toIndexedSeq, element.x, element.y) then number.value else 0
      .sum

  private def readMap(input: String) =
    for
      (line, y) <- input.split('\n').zipWithIndex
      mapElements <- lineToMapElements(line, y)()
    yield mapElements

  def sumGearRatios(input: String): Int =
    val map = readMap(input)
    val stars = map.filter(mapElement =>
      mapElement.element match
        case symbol: Symbol if symbol.char == '*' => true
        case _                                    => false
    )
    val numbers = map.filter(_.element.isInstanceOf[Number])
    stars
      .map(star =>
        val adjacentNumbers = star.element.asInstanceOf[Symbol].adjacentNumbers(numbers.toIndexedSeq, star.x, star.y)
        adjacentNumbers match
          case Seq(first, last) => first.value * last.value
          case _                => 0
      )
      .sum
