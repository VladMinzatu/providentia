package com.github.vladminzatu.providentia.repository

import com.github.vladminzatu.providentia.model.Tag

trait TagsRepository {

  def getAllTags(): List[Tag]

  def getTagById(id: String): Option[Tag]

  def updateTag(tag: Tag): Option[Tag]

  def addNewTag(tag: Tag): Option[Tag]
}
