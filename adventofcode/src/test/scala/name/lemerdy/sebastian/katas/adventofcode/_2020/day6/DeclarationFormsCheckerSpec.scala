package name.lemerdy.sebastian.katas.adventofcode._2020.day6

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class DeclarationFormsCheckerSpec extends AnyFlatSpec {

  lazy val input: String =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day6/input.txt").getLines().mkString("\n")

  "DeclarationFormsChecker" should "count groups answers" in {
    DeclarationFormsChecker.anyone("""abc
                                     |
                                     |a
                                     |b
                                     |c
                                     |
                                     |ab
                                     |ac
                                     |
                                     |a
                                     |a
                                     |a
                                     |a
                                     |
                                     |b""".stripMargin) shouldBe 11
  }

  it should "count anyone groups answers for input file" in {
    DeclarationFormsChecker.anyone(input) shouldBe 6351
  }

  it should "count everyone groups answers" in {
    DeclarationFormsChecker.everyone("""abc
                                       |
                                       |a
                                       |b
                                       |c
                                       |
                                       |ab
                                       |ac
                                       |
                                       |a
                                       |a
                                       |a
                                       |a
                                       |
                                       |b""".stripMargin) shouldBe 6
  }

  it should "count everyone groups answers for input file" in {
    DeclarationFormsChecker.everyone(input) shouldBe 3143
  }

}
