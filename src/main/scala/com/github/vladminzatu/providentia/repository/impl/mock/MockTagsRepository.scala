package com.github.vladminzatu.providentia.repository.impl.mock

import com.github.vladminzatu.providentia.model.Tag
import com.github.vladminzatu.providentia.repository.TagsRepository

class MockTagsRepository extends TagsRepository {

  override def getAllTags(): List[Tag] = MockData.tags

  override def getTagById(id: String): Option[Tag] = MockData.tags.find(_.id == id)

  override def updateTag(tag: Tag): Option[Tag] = {
    if(MockData.tags.map(_.id).contains(tag.id)) {
      MockData.tags = tag :: MockData.tags.filter(_.id != tag.id)
      Some(tag)
    }
    else None
  }

  override def addNewTag(tag: Tag): Option[Tag] = {
    val newId = (MockData.tags.size + 1).toString
    val newTag = Tag(newId, tag.name)
    MockData.tags = newTag :: MockData.tags
    Some(newTag)
  }
}