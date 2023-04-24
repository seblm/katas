package name.lemerdy.sebastian.katas.adventofcode._2022.day02

import scala.jdk.StreamConverters.*

object RockPaperScissors:

  def computeTotalScore(strategy: String): Long =
    strategy
      .lines()
      .toScala(Iterator)
      .map(Round.apply)
      .foldLeft(0L) { case (score, round) => score + round.computeScore() }

  case class Round(opponent: Shape, you: Shape):
    def computeScore(): Long = you.score + ((opponent, you) match {
      case (o, y) if o == y  => 3
      case (Rock, Scissors)  => 0
      case (Scissors, Paper) => 0
      case (Paper, Rock)     => 0
      case _                 => 6
    })

  object Round:
    def apply(line: String): Round = line.split(' ') match {
      case Array(opponent, you) => Round(Choice.opponent(opponent), Choice.you(you))
    }

  sealed abstract class Shape(val score: Long)
  case object Rock extends Shape(1)
  case object Paper extends Shape(2)
  case object Scissors extends Shape(3)

  object Choice:
    def opponent: String => Shape =
      case "A" => Rock
      case "B" => Paper
      case "C" => Scissors
    def you: String => Shape =
      case "X" => Rock
      case "Y" => Paper
      case "Z" => Scissors

  def computeTotalScoreCorrectStrategy(strategy: String): Long =
    strategy
      .lines()
      .toScala(Iterator)
      .map(RoundCorrectStrategy.apply)
      .foldLeft(0L) { case (score, round) => score + round.computeScore() }

  case class RoundCorrectStrategy(opponent: Shape, you: Strategy):
    def computeScore(): Long = you.score + ((opponent, you) match {
      case (opponent, Draw) => opponent
      case (Rock, Lose)     => Scissors
      case (Rock, Win)      => Paper
      case (Scissors, Lose) => Paper
      case (Scissors, Win)  => Rock
      case (Paper, Lose)    => Rock
      case (Paper, Win)     => Scissors
    }).score

  object RoundCorrectStrategy:
    def apply(line: String): RoundCorrectStrategy = line.split(' ') match {
      case Array(opponent, you) => RoundCorrectStrategy(Choice.opponent(opponent), Strategy(you))
    }

  sealed abstract class Strategy(val score: Long)
  case object Lose extends Strategy(0)
  case object Draw extends Strategy(3)
  case object Win extends Strategy(6)

  object Strategy:
    def apply(line: String): Strategy = line match {
      case "X" => Lose
      case "Y" => Draw
      case "Z" => Win
    }
