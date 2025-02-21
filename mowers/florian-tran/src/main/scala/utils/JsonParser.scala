package utils

import domain.{Lawn, Mower, Orientation}
import org.json.{JSONArray, JSONObject}

import scala.collection.mutable.ListBuffer

// Parseur JSON pour les données de la tondeuse
object JsonParser {
  // Parse le JSON pour obtenir la pelouse et les tondeuses
  def parseMowers(body: String): (Lawn, List[(Mower, String)]) = {
    val json = new JSONObject(body)
    val lawn = Lawn(json.getInt("width"), json.getInt("height"))

    val mowersArr = json.getJSONArray("mowers")
    val buffer = ListBuffer.empty[(Mower, String)]

    for (i <- 0 until mowersArr.length()) {
      val mowerObj = mowersArr.getJSONObject(i)
      val Array(xStr, yStr, oStr) = mowerObj.getString("position").split(" ")

      buffer += ((
        Mower(xStr.toInt, yStr.toInt, Orientation.fromChar(oStr.head)),
        mowerObj.getString("instructions")
      ))
    }

    (lawn, buffer.toList)
  }

  // Génère la réponse JSON avec les positions finales des tondeuses
  def generateResponse(mowers: List[Mower]): String = {
    val json = new JSONObject()
    val finalArray = new JSONArray()

    mowers.foreach { m =>
      finalArray.put(s"${m.x} ${m.y} ${Orientation.toChar(m.orientation)}")
    }

    json.put("finalPositions", finalArray)
    json.toString()
  }
}
