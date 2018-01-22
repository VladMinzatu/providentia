package com.github.vladminzatu.providentia.repository.impl.mock

import org.scalatest.{Matchers, WordSpec}

class MockReferencesRepositoryTest extends WordSpec with Matchers {

  val repo = new MockReferencesRepository

  "The repository" should {

    "return all references " in {
      repo.getAllReferences() == MockData.references
    }

    "return the correct reference by id" in {
      (1 to MockData.references.size).foreach(id => {
        repo.getReferenceById(id.toString) shouldEqual Some(MockData.references(id - 1))
      })
    }

    "return no reference for non-existing id" in{
      repo.getReferenceById("no-an-actual-id") shouldEqual None
    }

    "return no references for empty list of tags" in {
      repo.getReferencesByTags(List()) shouldEqual List()
    }

    "return no references for non-existing tags " in {
      repo.getReferencesByTags(List("not-a-tag")) shouldEqual List()
    }


    "return references by tags" in{
      repo.getReferencesByTags(List(MockData.scalaTag.id)) shouldEqual
        List(MockData.references(0), MockData.references(1))
      repo.getReferencesByTags(List(MockData.pythonTag.id)) shouldEqual
        List(MockData.references(2), MockData.references(3))
      repo.getReferencesByTags(List(MockData.pythonTag.id, MockData.mlTag.id)) shouldEqual
        List(MockData.references(3))
      repo.getReferencesByTags(List(MockData.mlTag.id)) shouldEqual
        List(MockData.references(3))
    }
  }

}
