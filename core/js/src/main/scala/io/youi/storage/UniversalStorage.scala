package io.youi.storage

import scala.util.Try

/**
  * Utilizes the best storage implementation falling back to lower quality as necessary
  */
object UniversalStorage extends Storage {
  var storage: StorageImplementation = LocalStorage.implementation

  override def implementation: StorageImplementation = storage

  Try {
    JavaScriptLocalForage
    storage = LocalForage.implementation
  }
}