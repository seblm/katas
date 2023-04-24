package name.lemerdy.sebastian.katas.adventofcode._2018.day2

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class InventoryManagementSystemSpec extends AnyFlatSpec {

  "InventoryManagementSystem" should "compute checksum" in {
    val boxIDs: Iterator[String] = Iterator(
      "abcdef",
      "bababc",
      "abbcde",
      "abcccd",
      "aabcdd",
      "abcdee",
      "ababab"
    )

    val checksum: BigInt = InventoryManagementSystem.checksum(boxIDs)

    checksum should be(BigInt(12))
  }

  it should "compute checksum for dataset.txt" in {
    val boxIDs: Iterator[String] = Source.fromResource(dataset).getLines()

    val checksum: BigInt = InventoryManagementSystem.checksum(boxIDs)

    checksum should be(BigInt(6422))
  }

  it should "find letters that are common between the two correct box IDs" in {
    val boxIDs: Iterator[String] = Iterator(
      "abcde",
      "fghij",
      "klmno",
      "pqrst",
      "fguij",
      "axcye",
      "wvxyz"
    )

    val commonLettersBetweenTwoCorrectBoxIDs: String =
      InventoryManagementSystem.commonLettersBetweenTwoCorrectBoxIDs(boxIDs)

    commonLettersBetweenTwoCorrectBoxIDs should be("fgij")
  }

  it should "find letters that are common between the two correct box IDs for dataset.txt" in {
    val boxIDs: Iterator[String] = Source.fromResource(dataset).getLines()

    val commonLettersBetweenTwoCorrectBoxIDs: String =
      InventoryManagementSystem.commonLettersBetweenTwoCorrectBoxIDs(boxIDs)

    commonLettersBetweenTwoCorrectBoxIDs should be("qcslyvphgkrmdawljuefotxbh")
  }

  private lazy val dataset = "name/lemerdy/sebastian/katas/adventofcode/_2018/day2/dataset.txt"

}
