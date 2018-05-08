package io.youi.component.bootstrap

import io.youi.component.extras.Classifiable

object ButtonType extends Classifiable[ButtonType] {
  case object Primary extends ButtonType("primary")
  case object Secondary extends ButtonType("secondary")
  case object Success extends ButtonType("success")
  case object Danger extends ButtonType("danger")
  case object Warning extends ButtonType("warning")
  case object Info extends ButtonType("info")
  case object Light extends ButtonType("light")
  case object Dark extends ButtonType("dark")
  case object Link extends ButtonType("link")

  object outline {
    case object Primary extends ButtonType("outline-primary")
    case object Secondary extends ButtonType("outline-secondary")
    case object Success extends ButtonType("outline-success")
    case object Danger extends ButtonType("outline-danger")
    case object Warning extends ButtonType("outline-warning")
    case object Info extends ButtonType("outline-info")
    case object Light extends ButtonType("outline-light")
    case object Dark extends ButtonType("outline-dark")
  }

  private val map = List(
    Primary, Secondary, Success, Danger, Warning, Info, Light, Dark, Link, outline.Primary, outline.Secondary,
    outline.Success, outline.Danger, outline.Warning, outline.Info, outline.Light, outline.Dark
  )
    .map(bt => bt.className -> bt)
    .toMap

  override def fromString(value: String): Option[ButtonType] = map.get(value)

  override def toString(value: ButtonType): String = value.className
}

sealed abstract class ButtonType(key: String) {
  private val className: String = s"btn-$key"
}