package name.lemerdy.sebastian.katas.adventofcode._2018.day4

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class ReposeRecordSpec extends FlatSpec with Matchers {

  "ReposeRecord" should "find guard and hour most likely to be asleep" in {
    val records = Iterator(
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

    val result = ReposeRecord.guardIdAndHourMostLikelyToBeAsleep(records)

    result should be(240)
  }

  it should "find guard and hour most likely to be asleep with dataset" in {
    val records = Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2018/day4/dataset.txt").getLines()

    val result = ReposeRecord.guardIdAndHourMostLikelyToBeAsleep(records)

    result should be(240)
  }

}
