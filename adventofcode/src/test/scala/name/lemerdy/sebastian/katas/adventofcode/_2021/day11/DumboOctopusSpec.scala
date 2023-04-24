package name.lemerdy.sebastian.katas.adventofcode._2021.day11

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class DumboOctopusSpec extends AnyFlatSpec:

  private lazy val example = """11111
                               |19991
                               |19191
                               |19991
                               |11111""".stripMargin.split("\n").toList

  "DumboOctopus" should "compute one step" in {
    show(DumboOctopus.step(DumboOctopus.parse(example))) should be("""34543
                                                                     |40004
                                                                     |50005
                                                                     |40004
                                                                     |34543
                                                                     |""".stripMargin)
  }

  it should "compute two step" in {
    show(DumboOctopus.steps(DumboOctopus.parse(example), 2)) should be("""45654
                                                                         |51115
                                                                         |61116
                                                                         |51115
                                                                         |45654
                                                                         |""".stripMargin)
  }

  private lazy val complexExample = """5483143223
                                      |2745854711
                                      |5264556173
                                      |6141336146
                                      |6357385478
                                      |4167524645
                                      |2176841721
                                      |6882881134
                                      |4846848554
                                      |5283751526""".stripMargin.split("\n").toList

  it should "compute number of flashes" in {
    DumboOctopus.flashCount(complexExample, 100) should be(1656)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day11/input.txt").getLines().toList

  it should "compute number of flashes with real example" in {
    DumboOctopus.flashCount(input, 100) should be(1571)
  }

  it should "find step where all octopuses flash simultaneously" in {
    DumboOctopus.flashSimultaneouslyStep(complexExample) should be(195)
  }

  it should "find step where all octopuses flash simultaneously with real example" in {
    DumboOctopus.flashSimultaneouslyStep(input) should be(387)
  }

  private def show(octopuses: List[DumboOctopus]): String = octopuses.map { octopus =>
    s"${octopus.internalEnergy}${if (octopus.x == octopuses.map(_.x).max) "\n" else ""}"
  }.mkString
