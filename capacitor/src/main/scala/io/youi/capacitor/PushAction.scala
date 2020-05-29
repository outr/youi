package io.youi.capacitor

import scala.scalajs.js

trait PushAction extends js.Object {
  def actionId: String
  def notification: PushNotification
}
