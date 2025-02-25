package controllers

import models.InputJson

import javax.inject._
import play.api.libs.json._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index());
  }

  def mowersCalculator(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>

    try {
      val getJsonFromBody = request.body.asJson match {
        case Some(json) => json
        case None => throw new IllegalArgumentException("Aucun JSON dans le body")
      }

      val input : InputJson = getJsonFromBody.as[InputJson]

      val lawnWidth = input.width
      val lawnHeight = input.height

      val finalPositions : List[String] = input.mowers.map(m => m.getFinalPosition(lawnWidth, lawnHeight))

      val jsonFinalPos : JsObject = Json.obj(
        "finalPositions" -> Json.toJson(finalPositions)
      )
      Ok(jsonFinalPos)

    }catch {
      case e: IllegalArgumentException => BadRequest(Json.obj("error" -> e.getMessage))
      case e: JsResultException => BadRequest(Json.obj("error" -> "JSON invalide"))
      case e : Exception => BadRequest(Json.obj("error" -> e.getMessage))
    }
  }
}
