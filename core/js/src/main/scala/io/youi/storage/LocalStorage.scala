package io.youi.storage

import org.scalajs.dom.window

object LocalStorage extends Storage {
  override def implementation: StorageImplementation = new JavaScriptStorageImplementation(window.localStorage)
}