package models
import play.api.libs.json._

case class InputJson(width : Int, height : Int, mowers : List[Mower])

object InputJson {
  implicit val inputJsonFormat: Format[InputJson] = Json.format[InputJson]
}