package name.lemerdy.sebastian.katas.adventofcode._2020.day2

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source
import scala.util.Try

class PasswordPolicyCheckerSpec extends AnyFlatSpec {

  lazy val input: String =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day2/input.txt").getLines().mkString("\n")

  "PasswordPolicyChecker" should "check range policy passwords" in {
    val result = PasswordPolicyChecker.checkRangePolicy("""1-3 a: abcde
                                                          |1-3 b: cdefg
                                                          |2-9 c: ccccccccc""".stripMargin)

    result shouldBe 2
  }

  it should "check range policy passwords from input file" in {
    val result = PasswordPolicyChecker.checkRangePolicy(input)

    result shouldBe 454
  }

  it should "check position policy passwords" in {
    val result = PasswordPolicyChecker.checkPositionPolicy("""1-3 a: abcde
                                                             |1-3 b: cdefg
                                                             |2-9 c: ccccccccc""".stripMargin)

    result shouldBe 1
  }

  it should "check position policy passwords from input file" in {
    val result = PasswordPolicyChecker.checkPositionPolicy(input)

    result shouldBe 649
  }

}
