package name.lemerdy.sebastian.katas.adventofcode._2018.day4

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class ReposeRecordSpec extends FlatSpec with Matchers {

  "ReposeRecord" should "find guard then minute most likely to be asleep" in {
    val result = ReposeRecord.guardIdThenMinuteMostLikelyToBeAsleep(records.toIterator)

    result should be(10 * 24)
  }

  it should "find guard then hour most likely to be asleep with dataset" in {
    val records = Source.fromResource(dataset).getLines()

    val result = ReposeRecord.guardIdThenMinuteMostLikelyToBeAsleep(records)

    result should be(11367)
  }

  it should "find guard and minute most likely to be asleep" in {
    val result = ReposeRecord.guardIdAndHourMostLikelyToBeAsleep(records.toIterator)

    result should be(99 * 45)
  }

  it should "find guard and minute most likely to be asleep with dataset" in {
    val records = Source.fromResource(dataset).getLines()

    val result = ReposeRecord.guardIdAndHourMostLikelyToBeAsleep(records)

    result should be(36896)
  }

  private lazy val records: Seq[String] = Seq(
    "[1518-11-01 00:00] Guard #10 begins shift",
    "[1518-11-01 00:05] falls asleep",
    "[1518-11-01 00:25] wakes up",
    "[1518-11-01 00:30] falls asleep",
    "[1518-11-01 00:55] wakes up",
    "[1518-11-01 23:58] Guard #99 begins shift",
    "[1518-11-02 00:40] falls asleep",
    "[1518-11-02 00:50] wakes up",
    "[1518-11-03 00:05] Guard #10 begins shift",
    "[1518-11-03 00:24] falls asleep",
    "[1518-11-03 00:29] wakes up",
    "[1518-11-04 00:02] Guard #99 begins shift",
    "[1518-11-04 00:36] falls asleep",
    "[1518-11-04 00:46] wakes up",
    "[1518-11-05 00:03] Guard #99 begins shift",
    "[1518-11-05 00:45] falls asleep",
    "[1518-11-05 00:55] wakes up",
  )

  lazy val dataset: String = "name/lemerdy/sebastian/katas/adventofcode/_2018/day4/dataset.txt"

}
