package name.lemerdy.sebastian.katas.nicestrings

import org.scalatest.{FlatSpec, Matchers}

class NiceStringsSpec extends FlatSpec with Matchers {

  "Santa" should "tell if a string is nice" in {
    new NiceStrings().isNice("ugknbfddgicrmopn") shouldBe true
    new NiceStrings().isNice("aaa") shouldBe true
  }

  it should "tell if a string is naughty" in {
    new NiceStrings().isNice("jchzalrnumimnmhp") shouldBe false
    new NiceStrings().isNice("haegwjzuvuyypxyu") shouldBe false
    new NiceStrings().isNice("dvszwmarrgswjxmb") shouldBe false
  }

}
