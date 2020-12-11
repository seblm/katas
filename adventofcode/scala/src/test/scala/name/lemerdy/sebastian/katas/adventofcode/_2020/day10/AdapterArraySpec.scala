package name.lemerdy.sebastian.katas.adventofcode._2020.day10

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class AdapterArraySpec extends AnyFlatSpec {

  lazy val input: String =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day10/input.txt").getLines().mkString("\n")

  "AdapterArray" should "compute chain" in {
    AdapterArray.computeChain("""16
                                |10
                                |15
                                |5
                                |1
                                |11
                                |7
                                |19
                                |6
                                |12
                                |4""".stripMargin) shouldBe 35
  }

  it should "compute chain with larger example" in {
    AdapterArray.computeChain("""28
                                |33
                                |18
                                |42
                                |31
                                |14
                                |46
                                |20
                                |48
                                |47
                                |24
                                |23
                                |49
                                |45
                                |19
                                |38
                                |39
                                |11
                                |1
                                |32
                                |25
                                |35
                                |8
                                |17
                                |7
                                |9
                                |4
                                |2
                                |34
                                |10
                                |3""".stripMargin) shouldBe 220
  }

  it should "compute chain with input" in {
    AdapterArray.computeChain(input.stripMargin) shouldBe 2380
  }

  it should "count arrangements" in {
    AdapterArray.count("""16
                         |10
                         |15
                         |5
                         |1
                         |11
                         |7
                         |19
                         |6
                         |12
                         |4""".stripMargin) shouldBe 8
  }

  it should "count arrangements with larger example" in {
    AdapterArray.count("""28
                         |33
                         |18
                         |42
                         |31
                         |14
                         |46
                         |20
                         |48
                         |47
                         |24
                         |23
                         |49
                         |45
                         |19
                         |38
                         |39
                         |11
                         |1
                         |32
                         |25
                         |35
                         |8
                         |17
                         |7
                         |9
                         |4
                         |2
                         |34
                         |10
                         |3""".stripMargin) shouldBe 19208
  }

  it should "count arrangements with input" in {
    AdapterArray.count(input) shouldBe 48358655787008L
  }

}
