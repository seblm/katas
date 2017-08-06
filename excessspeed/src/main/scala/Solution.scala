import scala.collection.mutable
import scala.io.StdIn.{readInt, readLine}

object Solution extends App {

  case class Photo(distance: Long, time: Long)

  val limit = readInt
  val numberOfSnapshots = readInt
  val measures: mutable.Map[String, List[Photo]] = mutable.Map.empty[String, List[Photo]]

  for (i <- 0 until numberOfSnapshots) {
    val line = readLine.split(" ")
    val car = line(0)
    val distance = line(1).toLong
    val time = line(2).toLong
    measures.update(car, measures.getOrElseUpdate(car, List()) ::: List(Photo(distance, time)))
  }

  val result = measures.flatMap { case (car, photos) =>
    (1 until photos.length).flatMap { i =>
      val duration = photos(i).time - photos(i - 1).time
      val distance = photos(i).distance - photos(i - 1).distance
      val averageSpeed = BigDecimal(3600 * distance) / duration
      if (averageSpeed > limit) {
        Some(s"$car ${photos(i).distance}")
      } else {
        None
      }
    }
  }

  print(result.toList match {
    case Nil => "OK"
    case speedLimits => speedLimits.mkString("\n")
  })
}