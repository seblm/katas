package modele

import io.circe.{Decoder, Encoder, HCursor, Json}
import io.circe.generic.semiauto._

case class Position(x: Int, y: Int, direction: String)

case class Mower(position: Position, instructions: String)

case class Simulation(width: Int, height: Int, mowers: List[Mower])

object Simulation {
  implicit val positionDecoder: Decoder[Position] = Decoder.decodeString.emap { str =>
    str.split(" ") match {
      case Array(x, y, d) if x.forall(_.isDigit) && y.forall(_.isDigit) =>
        Right(Position(x.toInt, y.toInt, d))
      case _ =>
        Left("Format invalide pour Position (doit être 'x y D')")
    }
  }

  implicit val positionEncoder: Encoder[Position] = Encoder.encodeString.contramap { pos =>
    s"${pos.x} ${pos.y} ${pos.direction}"
  }

  implicit val mowerDecoder: Decoder[Mower] = deriveDecoder[Mower]
  implicit val mowerEncoder: Encoder[Mower] = deriveEncoder[Mower]

  implicit val simulationDecoder: Decoder[Simulation] = deriveDecoder[Simulation]
  implicit val simulationEncoder: Encoder[Simulation] = deriveEncoder[Simulation]
}
