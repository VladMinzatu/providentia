package com.github.vladminzatu.providentia.app.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import com.github.vladminzatu.providentia.model.Tag
import com.github.vladminzatu.providentia.repository.TagsRepository

class Tags(val tagsRepository: TagsRepository) extends JsonSupport {

  val route = pathPrefix("tags") {
    (path(Segment) & pathEndOrSingleSlash) { id =>
      get {
        rejectEmptyResponse {
          complete(tagsRepository.getTagById(id))
        }
      } ~ put {
        rejectEmptyResponse {
          entity(as[Tag]) { tag =>
            complete(tagsRepository.updateTag(Tag(id, tag.name)))
          }
        }
      }
    } ~
      pathEndOrSingleSlash {
        get {
          complete(tagsRepository.getAllTags())
        } ~ post {
            entity(as[Tag]) { tag =>
              tagsRepository.addNewTag(tag) match {
                case None => complete(StatusCodes.InternalServerError)
                case Some(_) => complete(StatusCodes.Created)
              }
            }
        }
      }
  }
}
