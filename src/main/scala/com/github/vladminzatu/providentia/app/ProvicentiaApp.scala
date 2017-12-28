package com.github.vladminzatu.providentia.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.unmarshalling.PredefinedFromStringUnmarshallers.CsvSeq
import akka.stream.ActorMaterializer

object ProvicentiaApp {
  val route =
    pathPrefix("health") {
      pathEndOrSingleSlash {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "OK"))
        }
      }
    } ~ pathPrefix("references") {
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
    } ~ pathPrefix("references" / Segment) { id =>
      pathEndOrSingleSlash {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"Reference with id $id"))
        } ~ put {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"Update reference with id $id"))
        }
      }
    } ~ pathPrefix("tags") {
      pathEndOrSingleSlash {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "List of all tags"))
        } ~ post {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Create a new tag"))
        }
      }
    } ~ pathPrefix("tags" / Segment) { id =>
      pathEndOrSingleSlash {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"Tag with id $id"))
        } ~ put {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"Update tag with id $id"))
        }
      }
    }

  def main(args: Array[String]) {

    implicit val system = ActorSystem("providentia")
    implicit val materializer = ActorMaterializer()

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server started")
  }
}