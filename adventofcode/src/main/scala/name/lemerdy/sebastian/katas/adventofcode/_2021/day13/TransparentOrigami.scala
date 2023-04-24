package name.lemerdy.sebastian.katas.adventofcode._2021.day13

import scala.annotation.tailrec

object TransparentOrigami:

  def foldAll(transparentPaper: List[String]): String =
    val (dots, folds) = parse(transparentPaper)
    toString(folds.foldLeft(dots)(fold))

  def count(foldCount: Int, transparentPaper: List[String]): Int =
    val (dots, folds) = parse(transparentPaper)
    fold(dots, folds.head).size

  private def fold(dots: Set[Dot], foldInstruction: Fold): Set[Dot] =
    foldInstruction match {
      case HorizontalFold(line) => dots.map(dot => if (dot.y > line) dot.copy(y = 2 * line - dot.y) else dot)
      case VerticalFold(column) => dots.map(dot => if (dot.x > column) dot.copy(x = 2 * column - dot.x) else dot)
    }

  private val dotRegex = """(\d+),(\d+)""".r

  @tailrec
  private def parse(transparentPaper: List[String], dots: Set[Dot] = Set.empty): (Set[Dot], Seq[Fold]) =
    transparentPaper match {
      case "" :: tail             => (dots, parseFolds(tail))
      case dotRegex(x, y) :: tail => parse(tail, dots + Dot(x.toInt, y.toInt))
      case _                      => (Set.empty, Seq.empty)
    }

  private val horizontalFoldRegex = """fold along y=(\d+)""".r
  private val verticalFoldRegex = """fold along x=(\d+)""".r

  @tailrec
  private def parseFolds(transparentPaper: List[String], folds: Seq[Fold] = Seq.empty): Seq[Fold] =
    transparentPaper match {
      case Nil                               => folds
      case horizontalFoldRegex(line) :: tail => parseFolds(tail, folds :+ HorizontalFold(line.toInt))
      case verticalFoldRegex(line) :: tail   => parseFolds(tail, folds :+ VerticalFold(line.toInt))
      case _                                 => Seq.empty
    }

  case class Dot(x: Int, y: Int)

  trait Fold
  case class HorizontalFold(line: Int) extends Fold
  case class VerticalFold(column: Int) extends Fold

  private def toString(dots: Set[Dot]): String = {
    Range(0, dots.map(_.y).max + 1)
      .map { y =>
        Range(0, dots.map(_.x).max + 1).map { x =>
          if (dots.contains(Dot(x, y))) "#" else "."
        }.mkString
      }
      .mkString("\n")
  }
