package name.lemerdy.sebastian.katas.adventofcode._2015.day5

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

class NiceStringsSpec extends AnyFlatSpec:

  "Santa" should "tell if a string is nice" in {
    new NiceStrings().isNice("ugknbfddgicrmopn") shouldBe true
    new NiceStrings().isNice("aaa") shouldBe true
  }

  it should "tell if a string is naughty" in {
    new NiceStrings().isNice("jchzalrnumimnmhp") shouldBe false
    new NiceStrings().isNice("haegwjzuvuyypxyu") shouldBe false
    new NiceStrings().isNice("dvszwmarrgswjxmb") shouldBe false
  }

  "boring Santa" should "tell if a string is nice" in {
    new NiceStrings().isVeryNice("qjhvhtzxzqqjkmpb") shouldBe true
    new NiceStrings().isVeryNice("xxyxx") shouldBe true
  }

  it should "tell if a string is naughty" in {
    new NiceStrings().isVeryNice("uurcxstgmygtbstg") shouldBe false
    new NiceStrings().isVeryNice("ieodomkazucvgmuy") shouldBe false
  }
