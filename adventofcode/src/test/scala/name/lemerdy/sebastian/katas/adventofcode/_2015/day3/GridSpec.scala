package name.lemerdy.sebastian.katas.adventofcode._2015.day3

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

class GridSpec extends AnyFlatSpec:

  "Santa" should "visit 2 houses" in {
    new Grid().countHousesWithAtLeastOnePresent(">") shouldBe 2
  }

  it should "visit 4 houses" in {
    new Grid().countHousesWithAtLeastOnePresent("^>v<") shouldBe 4
  }

  it should "visit only 2 houses" in {
    new Grid().countHousesWithAtLeastOnePresent("^v^v^v^v^v") shouldBe 2
  }

  it should "fail if some character is unexpected" in {
    an[IllegalArgumentException] shouldBe thrownBy {
      new Grid().countHousesWithAtLeastOnePresent("^> v<")
    }
  }

  "Santa not recursive" should "visit 2 houses" ignore {
    new Grid().countHousesWithAtLeastOnePresentNoRec(">") shouldBe 2
  }

  it should "visit 4 houses" ignore {
    new Grid().countHousesWithAtLeastOnePresentNoRec("^>v<") shouldBe 4
  }

  it should "visit only 2 houses" ignore {
    new Grid().countHousesWithAtLeastOnePresentNoRec("^v^v^v^v^v") shouldBe 2
  }

  it should "fail if some character is unexpected" in {
    an[IllegalArgumentException] shouldBe thrownBy {
      new Grid().countHousesWithAtLeastOnePresentNoRec("^> v<")
    }
  }

  "Santa with Robo-Santa" should "visit 3 houses with one move each" ignore {
    new Grid().countHousesWithAtLeastOnePresentWhenRoboSantaHelpsSanta("^v") shouldBe 3
  }

  it should "visit 3 houses with two moves each" ignore {
    new Grid().countHousesWithAtLeastOnePresentWhenRoboSantaHelpsSanta("^>v<") shouldBe 3
  }

  it should "visit 11 houses" ignore {
    new Grid().countHousesWithAtLeastOnePresentWhenRoboSantaHelpsSanta("^v^v^v^v^v") shouldBe 11
  }

  it should "visit 2 houses when Santa and Robo-Santa visit the same house" ignore {
    new Grid().countHousesWithAtLeastOnePresentWhenRoboSantaHelpsSanta("^^") shouldBe 2
  }

  it should "fail if some character is unexpected" in {
    an[IllegalArgumentException] shouldBe thrownBy {
      new Grid().countHousesWithAtLeastOnePresentWhenRoboSantaHelpsSanta("^> v<")
    }
  }
