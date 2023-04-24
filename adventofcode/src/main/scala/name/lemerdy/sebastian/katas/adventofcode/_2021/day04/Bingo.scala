package name.lemerdy.sebastian.katas.adventofcode._2021.day04

import scala.annotation.tailrec

object Bingo:

  def computeScore(input: List[String]): Long =
    val randomNumbersToDrawn = input.head.split(",").map(_.toInt).toList
    val boards = parseBoards(input.drop(2))
    playBingo(randomNumbersToDrawn, boards)

  @tailrec
  private def playBingo(randomNumbersToDrawn: List[Int], boards: List[Board]): Long = {
    val updatedBoards = boards.map(_.numberIsDraw(randomNumbersToDrawn.head))
    updatedBoards.find(_.isWinner) match {
      case Some(winner) => winner.computeScore
      case None         => playBingo(randomNumbersToDrawn.tail, updatedBoards)
    }
  }

  def computeLastWinnerScore(input: List[String]): Long =
    val randomNumbersToDrawn = input.head.split(",").map(_.toInt).toList
    val boards = parseBoards(input.drop(2))
    playBingoLastWinner(randomNumbersToDrawn, boards)

  @tailrec
  private def playBingoLastWinner(randomNumbersToDrawn: List[Int], boards: List[Board]): Long = {
    val updatedBoards = boards.map(_.numberIsDraw(randomNumbersToDrawn.head))
    val categorized = boards
      .zip(updatedBoards)
      .groupMap {
        case (before, after) if !before.isWinner && after.isWinner  => "firstTimeWinner"
        case (before, after) if !before.isWinner && !after.isWinner => "notYetWinner"
        case (before, after) if before.isWinner && after.isWinner   => "alreadyWinner"
      }(_._2)
    if (
      categorized.get("alreadyWinner").map(_.length).getOrElse(0) == boards.length - 1 && categorized
        .get("firstTimeWinner")
        .map(_.length)
        .getOrElse(0) == 1
    )
      categorized("firstTimeWinner").head.computeScore
    else
      playBingoLastWinner(randomNumbersToDrawn.tail, updatedBoards)
  }

  private val line = """\s*(\d+)\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)""".r

  @tailrec
  private def parseBoards(
      input: List[String],
      boards: List[Board] = List.empty,
      lines: List[Line] = List.empty
  ): List[Board] = {
    input match {
      case Nil        => boards :+ Board(lines)
      case "" :: tail => parseBoards(tail, boards :+ Board(lines))
      case line(a, b, c, d, e) :: tail =>
        parseBoards(tail, boards, lines :+ Line(List(a, b, c, d, e).map(n => NumberOnABoard(n.toInt))))
      case _ => List.empty
    }
  }

  class Board(lines: List[Line], lastDrawnNumber: Int = 0):
    def numberIsDraw(drawnNumber: Int): Board = Board(lines.map(_.numberIsDraw(drawnNumber)), drawnNumber)
    def isWinner: Boolean = lines.exists(_.isWinner) || isWinnerByColumns(lines)
    def computeScore: Long = lines.map(_.getColumns.filterNot(_.isMarked).map(_.getNumber).sum).sum * lastDrawnNumber
    override def toString: String = lines.mkString("\n")

  @tailrec
  private def isWinnerByColumns(lines: List[Line], column: Int = 0): Boolean =
    if (column == lines.head.getColumns.length)
      false
    else if (lines.map(_.getColumns(column)).forall(_.isMarked))
      true
    else
      isWinnerByColumns(lines, column + 1)

  class Line(columns: List[NumberOnABoard]):
    def numberIsDraw(drawnNumber: Int): Line = Line(columns.map(_.numberIsDraw(drawnNumber)))
    def isWinner: Boolean = columns.forall(_.isMarked)
    def getColumns: List[NumberOnABoard] = columns
    override def toString: String = columns.mkString(" | ")

  class NumberOnABoard(number: Int, marked: Boolean = false):
    def numberIsDraw(drawnNumber: Int): NumberOnABoard =
      if (number == drawnNumber) NumberOnABoard(number, marked = true) else this
    def isMarked: Boolean = marked
    def getNumber: Int = number
    override def toString: String =
      s"${if (marked) "*" else " "}${if (number < 10) " " else ""}$number${if (marked) "*" else " "}"
