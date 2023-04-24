package name.lemerdy.sebastian.katas.adventofcode._2020.day2

import scala.util.Try
import scala.util.matching.Regex

object PasswordPolicyChecker {

  private val policyRegex: Regex = """(\d+)-(\d+) ([a-z]): ([a-z]+)""".r

  def checkRangePolicy(policies: String): Long = policies
    .split('\n')
    .count {
      case policyRegex(min, max, character, password) =>
        isRangePolicyValid(min, max, character, password).getOrElse(false)
      case _ =>
        false
    }

  private def isRangePolicyValid(min: String, max: String, character: String, password: String): Option[Boolean] =
    for {
      validatedMin <- min.toLongOption
      validatedMax <- max.toLongOption
      validatedCharacter <- character.toCharArray.headOption
      count = password.count(_ == validatedCharacter)
    } yield validatedMin <= count && count <= validatedMax

  def checkPositionPolicy(policies: String): Long = policies
    .split('\n')
    .count {
      case policyRegex(position1, position2, character, password) =>
        isPositionPolicyValid(position1, position2, character, password).getOrElse(false)
      case _ =>
        false
    }

  private def isPositionPolicyValid(pos1: String, pos2: String, character: String, password: String): Option[Boolean] =
    for {
      position1 <- pos1.toIntOption
      position2 <- pos2.toIntOption
      validatedCharacter <- character.toCharArray.headOption
      character1 <- Try(password.charAt(position1 - 1)).toOption
      character2 <- Try(password.charAt(position2 - 1)).toOption
    } yield character1 == validatedCharacter && character2 != validatedCharacter ||
      character1 != validatedCharacter && character2 == validatedCharacter

}
