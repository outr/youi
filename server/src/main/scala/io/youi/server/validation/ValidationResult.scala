package io.youi.server.validation

sealed trait ValidationResult

object ValidationResult {
  case object Continue extends ValidationResult
  case class Redirect(location: String) extends ValidationResult
  case class Error(status: Int, message: String) extends ValidationResult
}