package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.vladminzatu.providentia.app.ProvidentiaApp
import org.scalatest.{Matchers, WordSpec}

class HealthTest extends WordSpec with Matchers with ScalatestRouteTest {

  val route = new Health().route

  "The service " should {

    "return 200 - 'OK' on the /health endpoint" in {
      Get("/health") ~> route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "OK"
      }

      Get("/health/") ~> route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "OK"
      }
    }
  }
}
