package name.lemerdy.sebastian.katas.adventofcode._2018.day3

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class NoMatterHowYouSliceItSpec extends AnyFlatSpec {

  "NoMatterHowYouSliceIt" should "compute overlap" in {
    val claims: Iterator[String] = Iterator.apply(
      "#1 @ 1,3: 4x4",
      "#2 @ 3,1: 4x4",
      "#3 @ 5,5: 2x2"
    )

    val numberOfSquareInches: Int = NoMatterHowYouSliceIt.computeOverlap(claims)

    numberOfSquareInches should be(4)
  }

  it should "compute overlap 1" in {
    val claims: Iterator[String] = Iterator.apply(
      "#1 @ 1,3: 4x4",
      "#2 @ 3,1: 4x4",
      "#3 @ 5,5: 2x2",
      "#4 @ 4,4: 2x2",
      "#4 @ 4,4: 2x2",
    )

    val numberOfSquareInches: Int = NoMatterHowYouSliceIt.computeOverlap(claims)

    numberOfSquareInches should be(7)
  }

  it should "compute overlap with dataset.txt" ignore {
    val in = Source.fromResource(dataset).getLines()

    val numberOfSquareInches: Int = NoMatterHowYouSliceIt.computeOverlap(in)

    numberOfSquareInches should be(136852)
  }

  private lazy val dataset = "name/lemerdy/sebastian/katas/adventofcode/_2018/day3/dataset.txt"

}
