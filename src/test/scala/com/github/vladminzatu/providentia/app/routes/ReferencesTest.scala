package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.vladminzatu.providentia.app.ProvicentiaApp
import org.scalatest.{Matchers, WordSpec}

class ReferencesTest extends WordSpec with Matchers with ScalatestRouteTest {

  "The service " should {
    "return 200 - 'List of all references' on the references endpoint" in {
      Get("/references") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references"
      }

      Get("/references/") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references"
      }
    }

    "return 200 - 'Create a new reference' on the references endpoint" in {
      Post("/references") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Create a new reference"
      }

      Post("/references/") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Create a new reference"
      }
    }

    "return 200 - 'List of all references filtered by ...' on the references endpoint" in {
      Get("/references?tags=") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references filtered by List()"
      }

      Get("/references?tags=tag_foo") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references filtered by List(tag_foo)"
      }

      Get("/references?tags=tag_foo,tag_bar") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references filtered by List(tag_foo, tag_bar)"
      }

      Get("/references/?tags=tag_foo,tag_bar") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references filtered by List(tag_foo, tag_bar)"
      }
    }

    "return 200 - 'Reference with id ...' on the references endpoint" in {
      Get("/references/ref_foo") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Reference with id ref_foo"
      }

      Get("/references/ref_bar") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Reference with id ref_bar"
      }
    }

    "return 200 - 'Update reference with id ...' on the references endpoint" in {
      Put("/references/ref_foo") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Update reference with id ref_foo"
      }

      Put("/references/ref_bar") ~> ProvicentiaApp.route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Update reference with id ref_bar"
      }
    }
  }
}
