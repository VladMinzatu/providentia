package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.vladminzatu.providentia.model.Reference
import com.github.vladminzatu.providentia.repository.impl.mock.{MockData, MockReferencesRepository}
import org.scalatest.{Matchers, WordSpec}

class ReferencesTest extends WordSpec with Matchers with ScalatestRouteTest with JsonSupport {

  val route = new References(new MockReferencesRepository()).route
  
  "The service " should {
    "return 200 and the list of all references " in {
      Get("/references") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[List[Reference]] shouldEqual MockData.references
      }

      Get("/references/") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[List[Reference]] shouldEqual MockData.references
      }
    }

    "return 204 and create new reference " in {
      Post("/references", MockData.references(0).copy(name = "New Scala Ref")) ~> route ~> check {
        status shouldEqual StatusCodes.Created
        MockData.references.size shouldEqual 5
        MockData.references.find(_.id == "5").get.name shouldEqual "New Scala Ref"
      }
    }

    "return 200 and references filtered by tags " in {
      Get("/references?tags=") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[List[Reference]] shouldEqual List()
      }

      Get("/references?tags=2") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[List[Reference]] shouldEqual List(MockData.references(3), MockData.references(4))
      }
    }

    "return 200 and reference with the given id " in {
      Get("/references/1") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[Reference] shouldEqual MockData.references.find(_.id == "1").get
      }
    }

    "return 404 due to non-existing reference " in {
      Get("/references/not-an-id") ~> Route.seal(route) ~> check {
        status shouldEqual StatusCodes.NotFound
      }
    }

    "return 200 and update the reference with the given id" in {
      val ref = MockData.references.find(_.id == "1").get
      Put("/references/1", ref.copy(name = "New Name")) ~> route ~> check {
        status shouldEqual StatusCodes.OK
        MockData.references.find(_.id == "1").get.name shouldEqual "New Name"
      }
    }

    "return 404 when trying to update unknown ref " in {
      Put("/references/not-an-id", MockData.references.find(_.id == "1").get.copy(name = "New Name")) ~> Route.seal(route) ~> check {
        status shouldEqual StatusCodes.NotFound
      }
    }
  }
}
