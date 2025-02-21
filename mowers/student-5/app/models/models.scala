package models

import play.api.libs.json._

// Pelouse
case class Lawn(width: Int, height: Int)

// Tondeuse
case class Mower(position: String, instructions: String)

// Pilotage tonseuse
case class MowerRequest(width: Int, height: Int, mowers: List[Mower])

// Pour convertir en JSON
object MowerRequest {
  implicit val mowerFormat: Format[Mower] = Json.format[Mower]
  implicit val mowerRequestFormat: Format[MowerRequest] = Json.format[MowerRequest]
}

// Orientation de la tondeuse
object Orientation extends Enumeration {
  type Orientation = Value
  val N, E, S, W = Value
  implicit val orientationFormat: Format[Orientation.Value] = Json.formatEnum(this)
}

//Position de la tondeuse
case class Position(x: Int, y: Int, direction: Orientation.Orientation) {
  def gauche: Position = copy(direction = Orientation((direction.id + 3) % 4))
  def droite: Position = copy(direction = Orientation((direction.id + 1) % 4))
  def avancer(lawn: Lawn): Position = {
    val newPosition = direction match {
      case Orientation.N => copy(y = y + 1)
      case Orientation.E => copy(x = x + 1)
      case Orientation.S => copy(y = y - 1)
      case Orientation.W => copy(x = x - 1)
    }
    if (newPosition.x >= 0 && newPosition.x <= lawn.width &&
      newPosition.y >= 0 && newPosition.y <= lawn.height)
      newPosition
    else this
  }
}