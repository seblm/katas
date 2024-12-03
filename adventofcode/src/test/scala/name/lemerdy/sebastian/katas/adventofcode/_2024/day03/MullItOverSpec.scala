package name.lemerdy.sebastian.katas.adventofcode._2024.day03

import org.scalatest.TryValues.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class MullItOverSpec extends AnyFlatSpec:

  it should "multiply" in:
    val result = MullItOver.multiply("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")

    result should be(161)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2024/day03/input.txt"))(
      _.mkString
    ).success.value

  it should "multiply with real example" in:
    val result = MullItOver.multiply(input.replaceAll("\n", ""))

    result should be(156_388_521)

  it should "multiply with enablement" in:
    val result =
      MullItOver.multiplyEnablement("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")

    result should be(48)

  it should "multiply with enablement with real example" in:
    val result = MullItOver.multiplyEnablement(input.replaceAll("\n", ""))

    result should be(75_920_122)
