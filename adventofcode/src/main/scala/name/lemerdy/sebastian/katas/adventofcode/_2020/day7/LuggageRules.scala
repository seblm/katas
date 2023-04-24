package name.lemerdy.sebastian.katas.adventofcode._2020.day7

import scala.annotation.tailrec
import scala.util.matching.Regex

object LuggageRules {

  private val countLuggageExtractor: Regex = """(\d) (.+)""".r

  def count(rules: String, bag: String): Int = count(structuredRules(rules), Array(bag), Set.empty)

  @tailrec
  private def count(structuredRules: Array[Rule], bags: Array[String], containers: Set[String]): Int = {
    val (rules, others) = structuredRules.partition(rule => bags.contains(rule.bag))
    if (rules.isEmpty) containers.size
    else count(others, rules.map(_.container), containers.concat(rules.map(_.container)))
  }

  def countRequired(rules: String, bag: String): Int = countRequired(structuredRules(rules), bag)

  private def countRequired(structuredRules: Array[Rule], bag: String): Int = {
    val (rules, others) = structuredRules.partition(_.container == bag)
    rules.map(rule => rule.count + rule.count * countRequired(others, rule.bag)).sum
  }

  private def structuredRules(rules: String): Array[Rule] = rules.split('\n').flatMap { line =>
    val Array(container, contains) = line.split(" bags contain ")
    contains.split(" bags?[,|.]").map(_.trim).flatMap {
      case countLuggageExtractor(count, bag) => Array(Rule(container, count.toInt, bag))
      case _                                 => Array.empty[Rule]
    }
  }

  case class Rule(container: String, count: Int, bag: String)

}
