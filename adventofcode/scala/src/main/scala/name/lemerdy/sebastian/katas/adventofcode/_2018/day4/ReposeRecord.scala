package name.lemerdy.sebastian.katas.adventofcode._2018.day4

import java.time.{Duration, LocalDateTime}

import scala.annotation.tailrec
import scala.util.matching.Regex

object ReposeRecord {

  implicit val o: Ordering[LocalDateTime] = Ordering.fromLessThan { case (one, two) ⇒ one.isBefore(two) }

  def guardIdThenMinuteMostLikelyToBeAsleep(records: Iterator[String]): Long = {
    val (guard, (minute, _)) = recordsByGuard(records)
      .mapValues(records ⇒ computeAsleepDuration(records))
      .maxBy(_._2._2)
    guard.id.toLong * minute.maxBy(_._2)._1
  }

  def guardIdAndHourMostLikelyToBeAsleep(records: Iterator[String]): Long = {
    val (guard, (minute, _)) = recordsByGuard(records)
      .mapValues(records ⇒ computeAsleepDuration(records)._1.maxBy(_._2))
      .maxBy(_._2._2)
    guard.id.toLong * minute
  }

  private def recordsByGuard(records: Iterator[String]): Map[Guard, Seq[Record]] = {
    records
      .flatMap(Record.toRecord)
      .toList
      .sortBy(_.date)
      .foldLeft(Aggregate.empty) {
        case (Aggregate(map, _), BeginsShift(currentGuard, _)) ⇒ Aggregate(map, Some(currentGuard))
        case (Aggregate(map, Some(currentGuard)), record) ⇒
          val seq: Seq[Record] = map.getOrElse(currentGuard, Seq.empty)
          Aggregate(map + (currentGuard → (seq :+ record)), Some(currentGuard))
        case (aggregate, _) ⇒ aggregate
      }
      .recordsByGuard
  }

  private case class Guard(id: String)

  private object Guard {

    private val regex = """Guard #(\d+) begins shift""".r

    def apply(line: String): Option[Guard] = line match {
      case regex(id) ⇒ Some(new Guard(id))
      case _ ⇒ None
    }

  }

  private sealed trait Record {
    val date: LocalDateTime
  }

  private object Record {

    private val dateAndRecord: Regex = """\[(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2})\] (.+)""".r

    def toRecord(line: String): Option[Record] = line match {
      case dateAndRecord(year, month, day, hour, minute, record) ⇒
        val date = LocalDateTime.of(year.toInt, month.toInt, day.toInt, hour.toInt, minute.toInt)
        if (record.startsWith("Guard")) for {
          guard ← Guard(record)
        } yield BeginsShift(guard, date)
        else if (record == "falls asleep")
          Some(FallsAsleep(date))
        else if (record == "wakes up")
          Some(WakesUp(date))
        else
          None
      case _ ⇒ None
    }

  }

  private case class BeginsShift(guard: Guard, date: LocalDateTime) extends Record

  private case class FallsAsleep(date: LocalDateTime) extends Record

  private case class WakesUp(date: LocalDateTime) extends Record

  private case class Aggregate(recordsByGuard: Map[Guard, Seq[Record]], currentGuard: Option[Guard])

  private object Aggregate {

    lazy val empty: Aggregate = Aggregate(Map.empty, None)

  }

  @tailrec
  private def computeAsleepDuration(records: Seq[Record],
                                    duration: Long = 0,
                                    asleepByMinute: Map[Int, Long] = Map.empty,
                                    maybeAsleepDate: Option[LocalDateTime] = None): (Map[Int, Long], Long) =
    (records, maybeAsleepDate) match {
      case (Nil, _) ⇒
        (asleepByMinute, duration)
      case (FallsAsleep(asleepDate) :: tail, _) ⇒
        computeAsleepDuration(tail, duration, asleepByMinute, Some(asleepDate))
      case (WakesUp(wakeUpDate) :: tail, Some(asleepDate)) ⇒
        computeAsleepDuration(
          tail,
          duration + Duration.between(asleepDate, wakeUpDate).toMinutes,
          merge(asleepByMinute, asleepDate.getMinute, wakeUpDate.getMinute), None)
      case other ⇒
        println(s"can't handle $other")
        (asleepByMinute, duration)
    }

  @tailrec
  private def merge(asleepByMinutes: Map[Int, Long], asleepMinute: Int, wakeUpMinute: Int): Map[Int, Long] =
    asleepMinute match {
      case a if a == wakeUpMinute ⇒ asleepByMinutes
      case _ ⇒ merge(
        asleepByMinutes + (asleepMinute → (asleepByMinutes.getOrElse(asleepMinute, 0L) + 1L)),
        asleepMinute + 1,
        wakeUpMinute)
    }

}
