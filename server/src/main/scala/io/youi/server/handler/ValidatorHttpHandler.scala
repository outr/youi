package io.youi.server.handler

import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.server.validation.ValidationResult.{Continue, Error, Redirect}
import io.youi.server.validation.{ValidationResult, Validator}

import scala.concurrent.Future

class ValidatorHttpHandler(validators: List[Validator]) extends HttpHandler {
  override def handle(connection: HttpConnection): Future[HttpConnection] = Future.successful {
    ValidatorHttpHandler.validate(connection, validators)._1
  }
}

object ValidatorHttpHandler {
  def validate(connection: HttpConnection, validators: List[Validator]): (HttpConnection, ValidationResult) = {
    val failures = validators.map(_.validate(connection)).collect {
      case result if result != Continue => result
    }
    val validationResult = failures.headOption.getOrElse(Continue)
    (validationResult match {
      case Continue => connection   // Nothing to do, keep going
      case Redirect(location) => HttpHandler.redirect(connection, location)
      case Error(status, message) => {
        connection.modify(_.withStatus(HttpStatus(status, message)).withContent(Content.empty))
        connection.finish()
      }
    }, validationResult)
  }
}