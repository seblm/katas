package name.lemerdy.sebastian.katas.grid

import org.scalatest.{Matchers, FlatSpec}

class GridSpec extends FlatSpec with Matchers {

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
    an [IllegalArgumentException] shouldBe thrownBy {
      new Grid().countHousesWithAtLeastOnePresent("^> v<")
    }
  }

  "Santa not recursive" should "visit 2 houses" in {
    new Grid().countHousesWithAtLeastOnePresentNoRec(">") shouldBe 2
  }

  it should "visit 4 houses" in {
    new Grid().countHousesWithAtLeastOnePresentNoRec("^>v<") shouldBe 4
  }

  it should "visit only 2 houses" in {
    new Grid().countHousesWithAtLeastOnePresentNoRec("^v^v^v^v^v") shouldBe 2
  }

  it should "fail if some character is unexpected" in {
    an [IllegalArgumentException] shouldBe thrownBy {
      new Grid().countHousesWithAtLeastOnePresentNoRec("^> v<")
    }
  }

}
