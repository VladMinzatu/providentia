package com.github.vladminzatu.providentia.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.github.vladminzatu.providentia.app.routes.{Health, References, Tags}
import com.github.vladminzatu.providentia.repository.{ReferencesRepository, TagsRepository}
import com.github.vladminzatu.providentia.repository.impl.mock.{MockReferencesRepository, MockTagsRepository}

object ProvidentiaApp {

  val referencesRepository: ReferencesRepository = new MockReferencesRepository()
  val tagsRepository: TagsRepository= new MockTagsRepository()

  val route =
    new Health().route ~
    new References(referencesRepository).route ~
    new Tags(tagsRepository).route

  def main(args: Array[String]) {

    implicit val system = ActorSystem("providentia")
    implicit val materializer = ActorMaterializer()

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server started")
  }
}