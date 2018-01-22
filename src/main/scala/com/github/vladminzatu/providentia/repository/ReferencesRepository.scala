package com.github.vladminzatu.providentia.repository

import com.github.vladminzatu.providentia.model.Reference

trait ReferencesRepository {

  def getAllReferences(): List[Reference]

  def getReferencesByTags(tagIds: List[String]): List[Reference]

  def getReferenceById(id: String): Option[Reference]
}
