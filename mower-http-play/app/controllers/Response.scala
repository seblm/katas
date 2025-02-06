package controllers

import play.api.libs.json.{Format, Json}

case class Response(finalPositions: Seq[String])

object Response:
  given responseFormat: Format[Response] = Json.format
