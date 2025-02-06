package controllers

import mower.Lawn
import play.api.*
import play.api.libs.json.Json
import play.api.mvc.*

import javax.inject.*

@Singleton
class MowerController @Inject() (val controllerComponents: ControllerComponents) extends BaseController:

  def compute(): Action[Mowers] = Action(parse.json[Mowers]): (request: Request[Mowers]) =>
    Ok(Json.toJson(Response(Lawn.compute(request.body))))
