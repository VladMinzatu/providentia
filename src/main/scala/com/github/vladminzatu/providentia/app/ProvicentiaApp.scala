package com.github.vladminzatu.providentia.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.github.vladminzatu.providentia.app.routes.{Health, References, Tags}

object ProvicentiaApp {

  val route =
    Health.route ~
    References.route ~
    Tags.route

  def main(args: Array[String]) {

    implicit val system = ActorSystem("providentia")
    implicit val materializer = ActorMaterializer()

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server started")
  }
}