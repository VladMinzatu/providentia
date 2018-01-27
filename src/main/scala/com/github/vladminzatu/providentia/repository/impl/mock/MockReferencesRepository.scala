package com.github.vladminzatu.providentia.repository.impl.mock

import com.github.vladminzatu.providentia.model.Reference
import com.github.vladminzatu.providentia.repository.ReferencesRepository

class MockReferencesRepository extends ReferencesRepository {

  override def getAllReferences(): List[Reference] =
    MockData.references

  override def getReferencesByTags(tagIds: Seq[String]): List[Reference] =
    if(tagIds.size == 0) List()
    else MockData.references.filter(x => tagIds.intersect(x.tagIds) == tagIds)

  override def getReferenceById(id: String): Option[Reference] =
    MockData.references.find(_.id == id)

  override def updateReference(reference: Reference): Option[Reference] = {
    if(MockData.references.map(_.id).contains(reference.id)){
      MockData.references = reference :: MockData.references.filter(_.id != reference.id)
      Some(reference)
    }
    else None
  }

  override def addNewReference(reference: Reference): Option[Reference] = {
    val allTagIds = MockData.tags.map(_.id).toSet
    if (reference.tagIds.find(!allTagIds.contains(_)).isEmpty) {
      val newId = (MockData.references.size + 1).toString
      val newReference = reference.copy(id = newId)
      MockData.references = newReference :: MockData.references
      Some(newReference)
    }
    else None
  }
}
