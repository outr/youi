package io.youi.storage

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("localforage")
object JavaScriptLocalForage extends js.Object {
  def getItem(key: String): js.Promise[String] = js.native
  def setItem(key: String, value: String): js.Promise[String] = js.native
  def removeItem(key: String): js.Promise[Unit] = js.native
  def clear(): js.Promise[Unit] = js.native
  def length(): js.Promise[Int] = js.native
  def key(keyIndex: Int): js.Promise[String] = js.native
  def keys(): js.Promise[js.Array[String]] = js.native
}