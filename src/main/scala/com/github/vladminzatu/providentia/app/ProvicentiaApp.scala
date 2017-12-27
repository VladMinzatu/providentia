package com.github.vladminzatu.providentia.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

object ProvicentiaApp {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("providentia")
    implicit val materializer = ActorMaterializer()

    val route =
      path("health") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "OK"))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server started")
  }
}