package name.lemerdy.sebastian.katas.adventofcode._2022.day07

import scala.Function.const
import scala.annotation.tailrec
import scala.jdk.StreamConverters.*
import scala.util.matching.Regex

object NoSpaceLeftOnDevice:
  def compute(input: String): Long =
    val root = computeSize(parse(input.lines().toScala(Iterator)))
    val atMost100_000 = filter { case Directory(_, _, size) => size <= 100_000 }(root)
    atMost100_000.map(_.size).sum

  def computeSmallest(input: String): Long =
    val root = computeSize(parse(input.lines().toScala(Iterator)))
    val total = 70_000_000
    val desiredUnused = 30_000_000
    val totalUsed = root.size
    val totalUnused = total - totalUsed
    val toDeleteAtLeast = desiredUnused - totalUnused
    val atLeast = filter { case Directory(_, _, size) => size >= toDeleteAtLeast }(root)
    atLeast.map(_.size).min

  private val changeDirectory = """\$ cd (.+)""".r
  private val list = """\$ ls""".r
  private val directory = """dir .+""".r
  private val file = """(\d+) (.+)""".r

  private def parse(input: Iterator[String], breadcrumb: Vector[Directory] = Vector.empty): Node =
    if input.isEmpty then
      breadcrumb.dropRight(1).foldLeft(breadcrumb.last) { case (child, parent) =>
        parent.copy(children = parent.children :+ child)
      }
    else parse(input, input.next(), breadcrumb)
  private def parse(input: Iterator[String], current: String, breadcrumb: Vector[Directory]): Node =
    current match {
      case changeDirectory("..") =>
        val currentDirectory = breadcrumb.last
        val parent = breadcrumb.dropRight(1).last
        parse(input, breadcrumb.dropRight(2) :+ parent.copy(children = parent.children :+ currentDirectory))
      case changeDirectory(directory) => parse(input, breadcrumb :+ Directory(directory))
      case list() | directory()       => parse(input, breadcrumb)
      case file(size, name) =>
        val currentDirectory = breadcrumb.last
        val parents = breadcrumb.dropRight(1)
        parse(input, parents :+ currentDirectory.copy(children = currentDirectory.children :+ File(name, size.toLong)))
    }

  private def computeSize: Node => Node = {
    case file: File => file
    case directory @ Directory(_, children, _) =>
      val sizedChildren = children.map(computeSize)
      directory.copy(children = sizedChildren, size = sizedChildren.map(_.size).sum)
  }

  private def filter(predicate: PartialFunction[Node, Boolean])(node: Node): Vector[Node] = node match {
    case file: File => if predicate.isDefinedAt(file) then Vector(file) else Vector.empty
    case directory @ Directory(_, children, _) =>
      Option
        .when(predicate.applyOrElse(directory, const(false)))(directory)
        .toVector
        .concat(children.flatMap(filter(predicate)))
  }

  private sealed trait Node:
    val size: Long
  private case class Directory(name: String, children: Vector[Node] = Vector.empty, size: Long = 0) extends Node
  private case class File(name: String, size: Long) extends Node
