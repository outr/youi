package io.youi.server.handler

import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.server.validation.ValidationResult.{Continue, Error, Redirect}
import io.youi.server.validation.{ValidationResult, Validator}

class ValidatorHttpHandler(validators: List[Validator]) extends HttpHandler {
  override def handle(connection: HttpConnection): Unit = ValidatorHttpHandler.validate(connection, validators)
}

object ValidatorHttpHandler {
  def validate(connection: HttpConnection, validators: List[Validator]): ValidationResult = {
    val failures = validators.map(_.validate(connection)).collect {
      case result if result != Continue => result
    }
    val validationResult = failures.headOption.getOrElse(Continue)
    validationResult match {
      case Continue => // Nothing to do, keep going
      case Redirect(location) => HttpHandler.redirect(connection, location)
      case Error(status, message) => {
        connection.update(_.withStatus(HttpStatus(status, message)).withContent(Content.empty))
        connection.finish()
      }
    }
    validationResult
  }
}