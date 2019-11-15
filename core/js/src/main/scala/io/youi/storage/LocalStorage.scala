package io.youi.storage

import org.scalajs.dom.{raw, window}

object LocalStorage extends Storage {
  override protected def storage: raw.Storage = window.localStorage
}
