package io.youi.server.validation

import io.youi.http.HttpConnection

import scala.concurrent.Future

trait Validator {
  def validate(connection: HttpConnection): Future[ValidationResult]
}