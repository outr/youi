package youi.storage

import org.scalajs.dom.window

object SessionStorage extends Storage {
  override def implementation: StorageImplementation = new JavaScriptStorageImplementation(window.sessionStorage)
}
