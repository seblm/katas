package http

import logic.MowerLogic
import org.apache.pekko.http.scaladsl.model.{ContentTypes, HttpEntity}
import utils.JsonParser

// Gestionnaire de requête pour la tondeuse
object MowerHandler {
  def handleRequest(body: String): HttpEntity.Strict = {
    val responseJson =
      try {
        val (lawn, mowers) = JsonParser.parseMowers(body)
        val finalMowers = MowerLogic.processMowers(lawn, mowers)
        JsonParser.generateResponse(finalMowers)
      } catch {
        case e: Exception => s"""{"error": "${e.getMessage}"}"""
      }

    HttpEntity(ContentTypes.`application/json`, responseJson)
  }
}
