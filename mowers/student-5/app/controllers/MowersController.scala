package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services.MowerService
import models._

// Test Controleur mowers
//class MowersController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
//
//  val jsTest: JsValue = Json.parse("""
//  {
//    "test" : "Le contrôleur de la tondeuse fonctionne !"
//  }
//  """)
//
//  def index : Action[JsValue] = Action(parse.json)
//    {
//      implicit request => Ok(jsTest)
//    }
//}

// Controleur MowersController
class MowersController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  // Action process pour
  def process: Action[JsValue] = Action(parse.json) { implicit request =>
    // Vérification du JSON
    request.body.validate[MowerRequest] match {
      case JsSuccess(mowerRequest, _) =>
        // Création pelouse
        val lawn = Lawn(mowerRequest.width, mowerRequest.height)
        // Création instructions
        val finalPositions = mowerRequest.mowers.map { mower =>
          val Array(x, y, dir) = mower.position.split(" ")
          val startPosition = Position(x.toInt, y.toInt, Orientation.withName(dir))
          val finalPosition = MowerService.executeInstructions(startPosition, mower.instructions, lawn)
          s"${finalPosition.x} ${finalPosition.y} ${finalPosition.direction}"
        }
        // Réponse JSON
        Ok(Json.obj("finalPositions" -> finalPositions))
        // Mauvais JSON
      case JsError(errors) =>
        BadRequest(Json.obj("error" -> "Invalid JSON"))
    }
  }
}