package name.lemerdy.sebastian.katas.adventofcode._2018.day5

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class AlchemicalReductionSpec extends FlatSpec with Matchers {

  "Alchemical Reduction" should "reduce with simple case" in {
    AlchemicalReduction.reduce("aA") should be("")
  }

  it should "reduce with one chain reaction" in {
    AlchemicalReduction.reduce("abBA") should be("")
  }

  it should "reduce when polarities match" in {
    AlchemicalReduction.reduce("aabAAB") should be("aabAAB")
  }

  it should "reduce with larger example" in {
    AlchemicalReduction.reduce("dabAcCaCBAcCcaDA") should be("dabCBAcaDA")
  }

  it should "reduce with dataset" ignore {
    val dataset = Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2018/day5/dataset.txt").mkString

    AlchemicalReduction.reduce(dataset) should have size 10804
  }

  it should "reduce more" in {
    AlchemicalReduction.reduceMore("dabAcCaCBAcCcaDA") should be("daDA")
  }

  it should "reduce more with dataset" ignore {
    val dataset = Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2018/day5/dataset.txt").mkString

    AlchemicalReduction.reduceMore(dataset) should have size 6650
  }

}
