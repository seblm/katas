package mowitnow.mitchel.andriatsilavo

import scala.io.Source
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}

object App {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  def main(args: Array[String]): Unit = {
    val filename = "mowitnow/mitchel/andriatsilavo/input.txt"
    val lines = Try(Source.fromResource(filename).getLines().toList)

    lines match {
      case Success(value) =>
        val lawnEither = FileParser.parseLawn(value.head)

        lawnEither match {
          case Right(lawn) =>
            val lawnmowers = FileParser.parseLawnmowers(value.tail, lawn)
            val futureLawnmowers = Future.sequence(
              lawnmowers.map(lawnmower =>
                Future {
                  val initialPosition = lawnmower.position
                  val initialOrientation = lawnmower.orientation
                  val instructions = lawnmower.instructions
                  lawnmower.executeInstructions(lawn) match {
                    case Right(finalLawnmower) =>
                      val finalPosition = finalLawnmower.position
                      val finalOrientation = finalLawnmower.orientation
                      s"Position Initial: ${initialPosition.x},${initialPosition.y},${initialOrientation}\n" +
                        s"Instructions: $instructions\n" +
                        s"Position Final: ${finalPosition.x},${finalPosition.y},${finalOrientation}\n"
                    case Left(error) =>
                      s"Initial Position: ${initialPosition.x},${initialPosition.y},${initialOrientation}\n" +
                        s"Instructions: $instructions\n" +
                        s"Error: $error\n"
                  }
                }
              )
            )

            val results = Await.result(futureLawnmowers, Duration.Inf)
            results.foreach(println)

          case Left(error) => println(s"Error parsing lawn: $error")
        }

      case Failure(exception) => println(s"Error reading file: ${exception.getMessage}")
    }
  }
}
