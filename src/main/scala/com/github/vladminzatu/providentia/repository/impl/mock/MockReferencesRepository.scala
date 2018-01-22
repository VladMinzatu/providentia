package com.github.vladminzatu.providentia.repository.impl.mock

import com.github.vladminzatu.providentia.model.Reference
import com.github.vladminzatu.providentia.repository.ReferencesRepository

class MockReferencesRepository extends ReferencesRepository {

  override def getAllReferences(): List[Reference] =
    MockData.references

  override def getReferencesByTags(tagIds: List[String]): List[Reference] =
    if(tagIds.size == 0) List()
    else MockData.references.filter(x => tagIds.intersect(x.tagIds) == tagIds)

  override def getReferenceById(id: String): Option[Reference] =
    MockData.references.find(_.id == id)
}
