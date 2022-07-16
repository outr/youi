package io.youi.server.validation

import cats.effect.IO
import io.youi.http.HttpConnection

trait Validator {
  def validate(connection: HttpConnection): IO[ValidationResult]
}