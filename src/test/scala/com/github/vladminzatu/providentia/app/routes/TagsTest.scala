package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.vladminzatu.providentia.app.ProvicentiaApp
import org.scalatest.{Matchers, WordSpec}

class TagsTest extends WordSpec with Matchers with ScalatestRouteTest {

  "The service " should {
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
  }
}
