package models

import play.api.libs.json._

case class Tondeuse(position: Position, instructions: String)

object Tondeuse {
  //implicit val positionFormat: OFormat[Position] = Json.format[Position]
  implicit val tondeuseFormat: OFormat[Tondeuse] = Json.format[Tondeuse]
}
