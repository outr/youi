package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Alert(alertType: AlertType) extends tag.Div(clazz = List("alert", alertType.className))

object Alert {
  def apply(title: String, message: String, alertType: AlertType) = new Alert(alertType) {
    contents += new tag.Strong(content = title)
    contents += "&#160;"
    contents += message
  }

  def success(title: String, message: String) = apply(title, message, AlertType.Success)
  def info(title: String, message: String) = apply(title, message, AlertType.Info)
  def warning(title: String, message: String) = apply(title, message, AlertType.Warning)
  def danger(title: String, message: String) = apply(title, message, AlertType.Danger)
}

class AlertType(val className: String) extends EnumEntry

object AlertType extends Enumerated[AlertType] {
  val Success = new AlertType("alert-success")
  val Info = new AlertType("alert-info")
  val Warning = new AlertType("alert-warning")
  val Danger = new AlertType("alert-danger")
}