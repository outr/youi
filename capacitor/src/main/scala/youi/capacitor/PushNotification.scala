package youi.capacitor

import scala.scalajs.js

trait PushNotification extends js.Object {
  def id: String
  def title: String
  def body: String
  def data: PushNotificationData
}