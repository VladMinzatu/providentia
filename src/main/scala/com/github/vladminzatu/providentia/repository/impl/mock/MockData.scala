package com.github.vladminzatu.providentia.repository.impl.mock

import com.github.vladminzatu.providentia.model.{Reference, Tag}

object MockData {

  val scalaTag = Tag("1", "Scala")
  val pythonTag = Tag("2", "Python")
  val mlTag = Tag("3", "Machine Learning")

  var tags = List(scalaTag, pythonTag, mlTag)

  var references = List(
    Reference("1", "Scala School", "https://twitter.github.io/scala_school/", List(scalaTag.id)),
    Reference("2", "Effective Scala", "http://twitter.github.io/effectivescala/", List(scalaTag.id)),
    Reference("3", "Effective Python", "https://www.amazon.com/Effective-Python-Specific-Software-Development/dp/0134034287", List(pythonTag.id)),
    Reference("4", "Tensorflow", "https://www.tensorflow.org/get_started/", List(pythonTag.id, mlTag.id))
  )
}
