package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.unmarshalling.PredefinedFromStringUnmarshallers.CsvSeq

object References {

  val route = pathPrefix("references") {
    (path(Segment) & pathEndOrSingleSlash) { id =>
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"Reference with id $id"))
        } ~ put {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"Update reference with id $id"))
        }
      } ~
      pathEndOrSingleSlash {
        get {
          parameter("tags".as(CsvSeq[String])) { tags => {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"List of all references filtered by $tags"))
          }
          } ~ {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "List of all references"))
          }
        } ~ post {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Create a new reference"))
        }
      }
  }
}
