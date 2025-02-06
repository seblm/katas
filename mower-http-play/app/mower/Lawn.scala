package mower

import controllers.Mowers
import mower.Orientation.*

import java.lang.Math.{max, min}
import scala.util.Try

object Lawn:
  def compute(mowers: Mowers): Seq[String] =
    mowers.mowers.map: mower =>
      val splitted = mower.position.split(" ")
      val finalPosition = for {
        x <- Try(splitted.apply(0)).flatMap(x => Try(x.toInt))
        y <- Try(splitted.apply(1)).flatMap(y => Try(y.toInt))
        orientation <- Try(splitted.apply(2)).flatMap(orientation => Try(Orientation.valueOf(orientation)))
      } yield mower.instructions.foldLeft(Position(x, y, orientation)): (position, instruction) =>
        instruction match
          case 'G' => position.copy(orientation = left(position.orientation))
          case 'D' => position.copy(orientation = right(position.orientation))
          case 'A' =>
            position.orientation match
              case N => position.copy(y = min(position.y + 1, mowers.height - 1))
              case E => position.copy(x = min(position.x + 1, mowers.width - 1))
              case W => position.copy(x = max(position.x - 1, 0))
              case S => position.copy(y = max(position.y - 1, 0))
          case _ => position
      finalPosition.fold(_.getMessage, _.toString)
