package com.github.vladminzatu.providentia.repository

import com.github.vladminzatu.providentia.model.Reference

trait ReferencesRepository {

  def getAllReferences(): List[Reference]

  def getReferencesByTags(tagIds: Seq[String]): List[Reference]

  def getReferenceById(id: String): Option[Reference]

  def updateReference(reference: Reference): Option[Reference]

  def addNewReference(reference: Reference): Option[Reference]
}
