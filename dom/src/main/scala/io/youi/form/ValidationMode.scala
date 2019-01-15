package io.youi.form

sealed trait ValidationMode

object ValidationMode {
  val all: Set[ValidationMode] = Set(Blur, ValueChange, FormSubmit, OnDemand)

  case object Blur extends ValidationMode
  case object ValueChange extends ValidationMode
  case object FormSubmit extends ValidationMode
  case object OnDemand extends ValidationMode
}