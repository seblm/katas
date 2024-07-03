package mowitnow.mitchel.andriatsilavo

import cats.syntax.traverse.given
import mowitnow.MowerContract

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.io.Source
import scala.util.{Failure, Success, Try}

object App extends MowerContract {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  override def computeFinalPositions(input: String): String =
    val lines = input.split(System.lineSeparator()).toList
    val lawn = FileParser.parseLawn(lines.head)
    lawn
      .flatMap: l =>
        FileParser
          .parseLawnmowers(lines.tail, l)
          .map: lawnMower =>
            lawnMower.executeInstructions(l)
          .sequence
          .map: lawnMowers =>
            lawnMowers
              .map: lawnMower =>
                s"${lawnMower.position.x} ${lawnMower.position.y} ${lawnMower.orientation}"
              .mkString(System.lineSeparator())
      .fold[String](identity, identity)

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
