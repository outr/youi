package io.youi.storage

import org.scalajs.dom.{raw, window}

object SessionStorage extends Storage {
  override protected def storage: raw.Storage = window.sessionStorage
}
