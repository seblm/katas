package controllers

import play.api.mvc._
import play.api.libs.json._
import models._

import javax.inject._

@Singleton
class TondeuseController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def moveTondeuses() = Action { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    val width = (json \ "width").as[Int]
    val height = (json \ "height").as[Int]
    val tondeuses = (json \ "tondeuses").as[Seq[Tondeuse]]

    // Process each mower (tondeuse)
    val finalPositions = tondeuses.map { tondeuse =>
      moveTondeuse(tondeuse, width, height)
    }

    // Respond with the final positions
    Ok(Json.obj("finalPositions" -> finalPositions))
  }

  private def moveTondeuse(tondeuse: Tondeuse, width: Int, height: Int): String = {
    var position = tondeuse.position
    tondeuse.instructions.foreach {
      case 'A' => position = moveForward(position, width, height)
      case 'D' => position = turnRight(position)
      case 'G' => position = turnLeft(position)
    }
    s"${position.x} ${position.y} ${position.direction}"
  }

  private def moveForward(position: Position, width: Int, height: Int): Position = {
    position.direction match {
      case "N" => if (position.y < height - 1) position.copy(y = position.y + 1) else position
      case "E" => if (position.x < width - 1) position.copy(x = position.x + 1) else position
      case "S" => if (position.y > 0) position.copy(y = position.y - 1) else position
      case "W" => if (position.x > 0) position.copy(x = position.x - 1) else position
    }
  }

  private def turnRight(position: Position): Position = {
    position.direction match {
      case "N" => position.copy(direction = "E")
      case "E" => position.copy(direction = "S")
      case "S" => position.copy(direction = "W")
      case "W" => position.copy(direction = "N")
    }
  }

  private def turnLeft(position: Position): Position = {
    position.direction match {
      case "N" => position.copy(direction = "W")
      case "E" => position.copy(direction = "N")
      case "S" => position.copy(direction = "E")
      case "W" => position.copy(direction = "S")
    }
  }
}
