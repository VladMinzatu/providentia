package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.unmarshalling.PredefinedFromStringUnmarshallers.CsvSeq
import com.github.vladminzatu.providentia.model.Reference
import com.github.vladminzatu.providentia.repository.ReferencesRepository

class References(val referencesRepository: ReferencesRepository) extends JsonSupport {

  val route = pathPrefix("references") {
    (path(Segment) & pathEndOrSingleSlash) { id =>
        get {
          rejectEmptyResponse {
            complete(referencesRepository.getReferenceById(id))
          }
        } ~ put {
          rejectEmptyResponse {
            entity(as[Reference]) { reference =>
              complete(referencesRepository.updateReference(reference.copy(id = id)))
            }
          }
        }
      } ~
      pathEndOrSingleSlash {
        get {
          parameter("tags".as(CsvSeq[String])) { tags => {
            complete(referencesRepository.getReferencesByTags(tags))
          }
          } ~ {
            complete(referencesRepository.getAllReferences())
          }
        } ~ post {
          entity(as[Reference]) { reference =>
            referencesRepository.addNewReference(reference) match {
              case None => complete(StatusCodes.InternalServerError)
              case Some(_) => complete(StatusCodes.Created)
            }
          }
        }
      }
  }
}
