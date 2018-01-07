package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._

object Tags {

  val route = pathPrefix("tags") {
    (path(Segment) & pathEndOrSingleSlash) { id =>
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"Tag with id $id"))
      } ~ put {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"Update tag with id $id"))
      }
    } ~
      pathEndOrSingleSlash {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "List of all tags"))
        } ~ post {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Create a new tag"))
        }
      }
  }
}
