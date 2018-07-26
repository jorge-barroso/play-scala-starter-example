package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def untrail(path: String) = Action {
    MovedPermanently(s"/$path")
  }
}
