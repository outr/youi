package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.{ClassName, ClassProperty}
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Alert extends tag.Div {
  clazz += "alert"
  role := "alert"

  val alertType = new ClassProperty[AlertType](this, AlertType.Info)

  def this(alertType: AlertType) = {
    this()
    this.alertType := alertType
  }
}

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

sealed abstract class AlertType(val className: Option[String]) extends EnumEntry with ClassName

object AlertType extends Enumerated[AlertType] {
  case object Success extends AlertType(Option("alert-success"))
  case object Info extends AlertType(Option("alert-info"))
  case object Warning extends AlertType(Option("alert-warning"))
  case object Danger extends AlertType(Option("alert-danger"))

  val values = findValues.toVector
}