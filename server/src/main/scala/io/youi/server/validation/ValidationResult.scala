package io.youi.server.validation

import io.youi.http.HttpConnection

sealed trait ValidationResult {
  def connection: HttpConnection
}

object ValidationResult {
  case class Continue(connection: HttpConnection) extends ValidationResult
  case class Redirect(connection: HttpConnection, location: String) extends ValidationResult
  case class Error(connection: HttpConnection, status: Int, message: String) extends ValidationResult
}