package com.github.vladminzatu.providentia.repository.impl.mock

import com.github.vladminzatu.providentia.model.Tag
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

    "update existing tag if the id exists" in {
      val newName = "ML"
      val id = MockData.mlTag.id

      repo.updateTag(Tag(id, newName)) shouldEqual Some(Tag(id, newName))
      repo.getAllTags().size shouldEqual 3
      repo.getTagById(id) shouldEqual Some(Tag(id, newName))
    }

    "return None if tag to update does not exist" in {
      repo.updateTag(Tag("non-existing-id", "Name")) shouldEqual None
    }

    "add new tag and return it on success" in {
      val newId = (MockData.tags.size + 1).toString
      repo.getTagById(newId) shouldEqual None // verify that it didn't already exist
      repo.addNewTag(Tag("id-will-be-generated", "Name of new tag"))
      repo.getAllTags().size shouldEqual newId.toInt
      repo.getTagById(newId) shouldEqual Some(Tag(newId, "Name of new tag"))
    }
  }
}
