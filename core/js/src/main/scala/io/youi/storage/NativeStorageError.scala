package io.youi.storage

import scala.scalajs.js

@js.native
trait NativeStorageError extends js.Object {
  def code: String
  def source: String
  def exception: js.Object
}