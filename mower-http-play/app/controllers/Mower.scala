package controllers

import play.api.libs.json.{Format, Json}

case class Mower(position: String, instructions: String)

object Mower:
  given mowerFormat: Format[Mower] = Json.format[Mower]
