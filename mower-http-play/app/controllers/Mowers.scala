package controllers

import play.api.libs.json.{Format, Json}

case class Mowers(width: Int, height: Int, mowers: Seq[Mower])

object Mowers:
  given mowersFormat: Format[Mowers] = Json.format
