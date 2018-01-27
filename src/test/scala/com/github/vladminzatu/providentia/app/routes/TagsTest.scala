package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.vladminzatu.providentia.model.Tag
import com.github.vladminzatu.providentia.repository.impl.mock.{MockData, MockTagsRepository}
import org.scalatest.{Matchers, WordSpec}

class TagsTest extends WordSpec with Matchers with ScalatestRouteTest with JsonSupport {

  val route = new Tags(new MockTagsRepository()).route
  
  "The service " should {
    "return 200 and all tags on the tags endpoint" in {
      Get("/tags") ~> route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[List[Tag]] shouldEqual MockData.tags
      }

      Get("/tags/") ~> route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[List[Tag]] shouldEqual MockData.tags
      }
    }

    "return 204 when posting a new tag " in {
      Post("/tags/", Tag("id", "new-tag-name")) ~> route ~> check{
        status shouldEqual StatusCodes.Created
        MockData.tags.size shouldEqual 4
        MockData.tags.map(_.id).contains("4") shouldEqual true
      }
    }

    "return 200 and the tag with the specified id in " in {
      Get("/tags/1") ~> route ~> check{
        status shouldEqual StatusCodes.OK
        responseAs[Tag] shouldEqual MockData.scalaTag
      }
    }

    "return 404 due to non-existing tag " in {
      Get("/tags/not-an-id") ~> Route.seal(route) ~> check{
        status shouldEqual StatusCodes.NotFound
      }
    }

    "return 200 and update the tag with the matching id" in {
      Put("/tags/1", Tag("not-important", "SCALA")) ~> route ~> check{
        status shouldEqual StatusCodes.OK
        MockData.tags.find(_.id == "1").get.name shouldEqual "SCALA"
      }
    }

    "return 404 due to non-existing tag update " in {
      Put("/tags/not-an-id", Tag("not-important", "SCALA")) ~> Route.seal(route) ~> check{
        status shouldEqual StatusCodes.NotFound
      }
    }
  }
}
