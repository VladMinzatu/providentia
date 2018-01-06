package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.vladminzatu.providentia.app.ProvicentiaApp
import org.scalatest.{Matchers, WordSpec}

class HealthTest extends WordSpec with Matchers with ScalatestRouteTest {

  "The service " should {

    "return 200 - 'OK' on the /health endpoint" in {
      Get("/health") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "OK"
      }

      Get("/health/") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "OK"
      }
    }
  }
}
