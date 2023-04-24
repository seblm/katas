package name.lemerdy.sebastian.katas.adventofcode._2020.day7

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class LuggageRulesSpec extends AnyFlatSpec {

  lazy val input: String =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day7/input.txt").getLines().mkString("\n")

  "LuggageRules" should "count other bag that can eventually contain at least some bag" in {
    LuggageRules.count(
      """light red bags contain 1 bright white bag, 2 muted yellow bags.
        |dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        |bright white bags contain 1 shiny gold bag.
        |muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        |shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        |dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        |vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        |faded blue bags contain no other bags.
        |dotted black bags contain no other bags.""".stripMargin,
      "shiny gold"
    ) shouldBe 4
  }

  it should "count other bag that can eventually contain at least some bag for input" in {
    LuggageRules.count(input, "shiny gold") shouldBe 378
  }

  it should "count bags required inside shiny gold bag" in {
    LuggageRules.countRequired(
      """light red bags contain 1 bright white bag, 2 muted yellow bags.
        |dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        |bright white bags contain 1 shiny gold bag.
        |muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        |shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        |dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        |vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        |faded blue bags contain no other bags.
        |dotted black bags contain no other bags.""".stripMargin,
      "shiny gold"
    ) shouldBe 32
  }

  it should "count bags required inside shiny gold bag for input" in {
    LuggageRules.countRequired(input, "shiny gold") shouldBe 27526
  }

}
