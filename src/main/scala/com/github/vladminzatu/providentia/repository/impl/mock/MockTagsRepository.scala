package com.github.vladminzatu.providentia.repository.impl.mock

import com.github.vladminzatu.providentia.model.Tag
import com.github.vladminzatu.providentia.repository.TagsRepository

class MockTagsRepository extends TagsRepository {

  override def getAllTags(): List[Tag] = MockData.tags

  override def getTagById(id: String): Option[Tag] = MockData.tags.find(_.id == id)
}