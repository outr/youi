package io.youi

import org.scalajs.dom._

object SessionStorage extends Storage {
  override protected lazy val storage: raw.Storage = window.sessionStorage
}