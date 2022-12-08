package name.lemerdy.sebastian.katas.adventofcode._2022.day08

import scala.Function.const
import scala.annotation.tailrec
import scala.collection.mutable
import scala.jdk.StreamConverters.*
import scala.util.matching.Regex

object TreetopTreeHouse:
  private def readTrees(input: String) =
    input.lines().toScala(Vector).zipWithIndex.flatMap { case (line, row) =>
      line.toVector.zipWithIndex.map { case (char, column) =>
        Tree(row, column, char.toInt - '0'.toInt)
      }
    }

  def countVisibleTrees(input: String): Long =
    val trees: Vector[Tree] = readTrees(input)
    val rows = trees.map(_.row).max + 1
    val columns = trees.map(_.column).max + 1
    val numberOfTreesAtEdges = rows + columns - 1 + rows - 1 + columns - 2
    def findTree: ((Int, Int)) => Option[Tree] = findTreeFrom(trees)
    val visibles = Range(1, rows - 1).flatMap { row =>
      Range(1, columns - 1).flatMap { column =>
        findTree(row, column).flatMap { tree =>
          val topVisible = Range(0, row)
            .flatMap(currentRow => findTree(currentRow, column))
            .forall(_.height < tree.height)
          val bottomVisible = Range(row + 1, rows)
            .flatMap(currentRow => findTree(currentRow, column))
            .forall(_.height < tree.height)
          val leftVisible = Range(0, column)
            .flatMap(currentColumn => findTree(row, currentColumn))
            .forall(_.height < tree.height)
          val rightVisible = Range(column + 1, columns)
            .flatMap(currentColumn => findTree(row, currentColumn))
            .forall(_.height < tree.height)
          Option.when(topVisible || bottomVisible || leftVisible || rightVisible)(())
        }
      }
    }.size
    visibles + numberOfTreesAtEdges

  def computeScenicScore(input: String, tree: Tree): Long =
    computeScenicScore(readTrees(input))(tree)

  def computeMaxScenicScore(input: String): Long =
    val trees = readTrees(input)
    trees.map(computeScenicScore(trees)).max

  private def computeScenicScore(trees: Vector[Tree])(tree: Tree): Long =
    val rows = trees.map(_.row).max + 1
    val columns = trees.map(_.column).max + 1
    def findTree = findTreeFrom(trees)
    val topVisibles =
      measure(tree, Range.inclusive(tree.row - 1, 0, -1).flatMap(currentRow => findTree(currentRow, tree.column)))
    val bottomVisibles =
      measure(tree, Range(tree.row + 1, rows).flatMap(currentRow => findTree(currentRow, tree.column)))
    val leftVisibles =
      measure(tree, Range.inclusive(tree.column - 1, 0, -1).flatMap(currentColumn => findTree(tree.row, currentColumn)))
    val rightVisibles =
      measure(tree, Range(tree.column + 1, columns).flatMap(currentColumn => findTree(tree.row, currentColumn)))
    topVisibles * bottomVisibles * leftVisibles * rightVisibles

  private def measure(tree: Tree, trees: Iterable[Tree]) =
    trees
      .foldLeft((0, true)) { case ((count, continue), current) =>
        if continue && current.height < tree.height then (count + 1, true)
        else if continue && current.height >= tree.height then (count + 1, false)
        else (count, false)
      }
      ._1

  private def findTreeFrom(trees: Vector[Tree])(row: Int, column: Int): Option[Tree] =
    trees.find(tree => tree.row == row && tree.column == column)

  case class Tree(row: Int, column: Int, height: Int)
