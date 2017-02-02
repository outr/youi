package io.youi.server.validation

import io.youi.http.HttpConnection

trait Validator {
  def validate(connection: HttpConnection): ValidationResult
}