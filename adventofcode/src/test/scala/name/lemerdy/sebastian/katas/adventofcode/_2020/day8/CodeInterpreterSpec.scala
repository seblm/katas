package name.lemerdy.sebastian.katas.adventofcode._2020.day8

import name.lemerdy.sebastian.katas.adventofcode._2020.day8.CodeInterpreter.Result.{InfiniteLoop, Terminate}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class CodeInterpreterSpec extends AnyFlatSpec {

  lazy val input: String =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day8/input.txt").getLines().mkString("\n")

  "CodeInterpreter" should "return accumulator value just before running an instruction twice" in {
    CodeInterpreter.run("""nop +0
                          |acc +1
                          |jmp +4
                          |acc +3
                          |jmp -3
                          |acc -99
                          |acc +1
                          |jmp -4
                          |acc +6""".stripMargin) shouldBe InfiniteLoop(5)
  }

  it should "return accumulator value just before running an instruction twice with input" in {
    CodeInterpreter.run(input) shouldBe InfiniteLoop(1801)
  }

  it should "return accumulator value for a program that terminates by changing exactly one jmp to nop or nop to jmp" in {
    CodeInterpreter.run(
      """nop +0
                          |acc +1
                          |jmp +4
                          |acc +3
                          |jmp -3
                          |acc -99
                          |acc +1
                          |jmp -4
                          |acc +6""".stripMargin,
      mutate = true
    ) shouldBe Terminate(8)
  }

  it should "return accumulator value for a program that terminates by changing exactly one jmp to nop or nop to jmp with input" in {
    CodeInterpreter.run(input, mutate = true) shouldBe Terminate(2060)
  }

}
