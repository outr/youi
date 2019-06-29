package io.youi

import org.scalajs.dom._

object SessionStorage extends io.youi.Storage {
  override protected def storage: raw.Storage = window.sessionStorage
}