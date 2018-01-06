package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, pathEndOrSingleSlash, pathPrefix}

object Health {

  val route = pathPrefix("health") {
    pathEndOrSingleSlash {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "OK"))
      }
    }
  }

}
