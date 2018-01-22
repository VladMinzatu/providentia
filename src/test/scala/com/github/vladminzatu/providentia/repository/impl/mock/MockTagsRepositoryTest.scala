package com.github.vladminzatu.providentia.repository.impl.mock

import org.scalatest.{Matchers, WordSpec}

class MockTagsRepositoryTest extends WordSpec with Matchers {

  val repo = new MockTagsRepository

  "The repository" should {

    "return all mock tags" in {
      val allTags = repo.getAllTags()
      allTags shouldEqual MockData.tags
    }

    "return the correct tag by id" in {
      repo.getTagById(MockData.scalaTag.id) shouldEqual Some(MockData.scalaTag)
      repo.getTagById(MockData.pythonTag.id) shouldEqual Some(MockData.pythonTag)
      repo.getTagById(MockData.mlTag.id) shouldEqual Some(MockData.mlTag)
    }

    "return None for non-existing id" in {
      repo.getTagById("no-an-actual-id") shouldEqual None
    }
  }
}
