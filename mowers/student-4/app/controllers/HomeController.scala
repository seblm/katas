package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services.MowerService
import models._

import scala.concurrent.Future

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok("It works")
  }

  def processMowers(): Action[JsValue] = Action(parse.json).async { request =>
    request.body.validate[MowerRequest] match {
      case JsSuccess(mowerRequest, _) =>
        val response = MowerService.processMowers(mowerRequest)
        Future.successful(Ok(Json.toJson(response)))

      case JsError(errors) =>
        Future.successful(BadRequest(Json.obj("error" -> "Invalid JSON format", "details" -> errors.toString())))
    }
  }
}
