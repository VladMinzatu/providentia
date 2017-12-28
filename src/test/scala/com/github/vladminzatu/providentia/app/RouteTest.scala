package com.github.vladminzatu.providentia.app

import org.scalatest.{ Matchers, WordSpec }
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._

class RouteTest extends WordSpec with Matchers with ScalatestRouteTest {

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

    "return 200 - 'List of all tags' on the tags endpoint" in {
      Get("/tags") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all tags"
      }

      Get("/tags/") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all tags"
      }
    }

    "return 200 - 'Create a new tag' on the tags endpoint" in {
      Post("/tags") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Create a new tag"
      }

      Post("/tags/") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Create a new tag"
      }
    }

    "return 200 - 'Tag with id ...' on the tag endpoint" in {
      Get("/tags/tag_foo") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Tag with id tag_foo"
      }

      Get("/tags/tag_bar") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Tag with id tag_bar"
      }
    }

    "return 200 - 'Update tag with id ...' on the tag endpoint" in {
      Put("/tags/tag_foo") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Update tag with id tag_foo"
      }

      Put("/tags/tag_bar") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Update tag with id tag_bar"
      }
    }

    "return 200 - 'List of all references' on the references endpoint" in {
      Get("/references") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references"
      }

      Get("/references/") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references"
      }
    }

    "return 200 - 'Create a new reference' on the references endpoint" in {
      Post("/references") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Create a new reference"
      }

      Post("/references/") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Create a new reference"
      }
    }

    "return 200 - 'List of all references filtered by ...' on the references endpoint" in {
      Get("/references?tags=") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references filtered by List()"
      }

      Get("/references?tags=tag_foo") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references filtered by List(tag_foo)"
      }

      Get("/references?tags=tag_foo,tag_bar") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references filtered by List(tag_foo, tag_bar)"
      }

      Get("/references/?tags=tag_foo,tag_bar") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "List of all references filtered by List(tag_foo, tag_bar)"
      }
    }

    "return 200 - 'Reference with id ...' on the references endpoint" in {
      Get("/references/ref_foo") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Reference with id ref_foo"
      }

      Get("/references/ref_bar") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Reference with id ref_bar"
      }
    }

    "return 200 - 'Update reference with id ...' on the references endpoint" in {
      Put("/references/ref_foo") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Update reference with id ref_foo"
      }

      Put("/references/ref_bar") ~> ProvicentiaApp.route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "Update reference with id ref_bar"
      }
    }
  }

}