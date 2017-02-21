package io.youi.server.handler

import io.youi.http.{Content, HttpConnection, Status, StringHeaderKey}
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
      case Redirect(location) => {
        val isStreaming = connection.request.headers.first(new StringHeaderKey("streaming")).contains("true")
        if (isStreaming) {
          val status = Status.NetworkAuthenticationRequired(s"Redirect to $location")
          connection.update(_.withStatus(status).withContent(Content.empty))
        } else {
          connection.update(_.withRedirect(location))
        }
        connection.finish()
      }
      case Error(status, message) => {
        connection.update(_.withStatus(Status(status, message)).withContent(Content.empty))
        connection.finish()
      }
    }
    validationResult
  }
}