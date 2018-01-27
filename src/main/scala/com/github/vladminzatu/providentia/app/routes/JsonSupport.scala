package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.github.vladminzatu.providentia.model.{Reference, Tag}
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val itemFormat = jsonFormat2(Tag)
  implicit val orderFormat = jsonFormat4(Reference)
}