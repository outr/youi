package io.youi.form

trait FieldError {
  def input: FormInput

  def show(message: String): Unit
  def clear(): Unit
}