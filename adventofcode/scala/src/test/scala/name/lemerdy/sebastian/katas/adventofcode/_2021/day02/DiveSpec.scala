package name.lemerdy.sebastian.katas.adventofcode._2021.day02

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Try

class DiveSpec extends AnyFlatSpec:

  "Dive" should "compute horizontal position and depth" in {
    val (horizontalPosition, depth) =
      Dive.computeHorizontalPositionAndDepth(List("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2"))

    horizontalPosition should be(15)
    depth should be(10)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day02/input.txt").getLines().toList

  it should "compute horizontal position and depth with real example" in {
    val (horizontalPosition, depth) = Dive.computeHorizontalPositionAndDepth(input)

    horizontalPosition should be(1962)
    depth should be(987)
    horizontalPosition * depth should be(1936494)
  }

  it should "compute horizontal position and depth with aim" in {
    val (horizontalPosition, depth, _) = Dive.computeHorizontalPositionAndDepthWithAim(
      List("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
    )

    horizontalPosition should be(15)
    depth should be(60)
  }

  it should "compute horizontal position and depth with aim with real example" in {
    val (horizontalPosition, depth, _) = Dive.computeHorizontalPositionAndDepthWithAim(input)

    horizontalPosition should be(1962)
    depth should be(1017893)
    horizontalPosition * depth should be(1997106066)
  }
