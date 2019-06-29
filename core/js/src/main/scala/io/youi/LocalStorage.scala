package io.youi

import org.scalajs.dom._

object LocalStorage extends io.youi.Storage {
  override protected def storage: raw.Storage = window.localStorage
}