package io.youi

import org.scalajs.dom._

object LocalStorage extends Storage {
  override protected lazy val storage: raw.Storage = window.localStorage
}