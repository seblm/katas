package name.lemerdy.sebastian.katas.adventofcode._2022.day04

import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class CampCleanupSpec extends AnyFlatSpec:

  "CampCleanup" should "count fully contained assignments" in {
    CampCleanup.countFullyContainedAssignments("""2-4,6-8
                                                 |2-3,4-5
                                                 |5-7,7-9
                                                 |2-8,3-7
                                                 |6-6,4-6
                                                 |2-6,4-8""".stripMargin) should be(2)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day04/input.txt"))(
      _.mkString
    ).success.value

  it should "count fully contained assignments with real example" in {
    CampCleanup.countFullyContainedAssignments(input) should be(494)
  }

  it should "count overlapped assignments" in {
    CampCleanup.countOverlappedAssignments("""2-4,6-8
                                             |2-3,4-5
                                             |5-7,7-9
                                             |2-8,3-7
                                             |6-6,4-6
                                             |2-6,4-8""".stripMargin) should be(4)
  }

  it should "count overlapped assignments with real example" in {
    CampCleanup.countOverlappedAssignments(input) should be(833)
  }
