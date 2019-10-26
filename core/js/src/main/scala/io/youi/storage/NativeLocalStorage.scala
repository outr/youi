package io.youi.storage

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("NativeStorage")
object NativeLocalStorage extends js.Object {
  def setItem(key: String,
              value: String,
              successCallback: js.Function1[String, Unit],
              failureCallback: js.Function1[NativeStorageError, Unit]): Unit = js.native

  def getItem(key: String,
              successCallback: js.Function1[String, Unit],
              failureCallback: js.Function1[NativeStorageError, Unit]): Unit = js.native

  def remove(key: String,
             successCallback: js.Function0[Unit],
             failureCallback: js.Function1[NativeStorageError, Unit]): Unit = js.native

  def clear(successCallback: js.Function0[Unit],
            failureCallback: js.Function1[NativeStorageError, Unit]): Unit = js.native
}