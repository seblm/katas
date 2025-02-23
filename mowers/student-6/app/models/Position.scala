package models

import play.api.libs.json._

case class Position(x: Int, y: Int, direction: String)

object Position {
  // Créer un format personnalisé pour le Char
/*
implicit val charFormat: Format[Char] = Format(
  Reads {
    case JsString(s) if s.length == 1 => JsSuccess(s.charAt(0))
    case _ => JsError("Invalid Char")
  },
  Writes(c => JsString(c.toString))
)
*/
// Utiliser le format personnalisé pour Position
implicit val positionFormat: Format[Position] = Json.format[Position]
}
