package models

import play.api.libs.json._

case class Lawn(width: Int, height: Int)

object Lawn {
  implicit val format: Format[Lawn] = Json.format[Lawn]
}

case class Position(x: Int, y: Int, orientation: Char)

object Position {
  implicit val format: Format[Position] = new Format[Position] {
    def writes(pos: Position): JsValue = JsString(s"${pos.x} ${pos.y} ${pos.orientation}")

    def reads(json: JsValue): JsResult[Position] = json.validate[String].flatMap { str =>
      str.split(" ") match {
        case Array(x, y, o) if "NEWS".contains(o) => JsSuccess(Position(x.toInt, y.toInt, o.head))
        case _ => JsError("Invalid position format")
      }
    }
  }
}

case class Mower(position: Position, instructions: String)

object Mower {
  implicit val format: Format[Mower] = Json.format[Mower]
}

case class MowerRequest(width: Int, height: Int, mowers: Seq[Mower])

object MowerRequest {
  implicit val format: Format[MowerRequest] = Json.format[MowerRequest]
}

case class MowerResponse(finalPositions: Seq[String])

object MowerResponse {
  implicit val format: Format[MowerResponse] = Json.format[MowerResponse]
}
