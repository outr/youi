package io.youi.server.handler

import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.server.validation.ValidationResult.{Continue, Error, Redirect}
import io.youi.server.validation.{ValidationResult, Validator}
import scribe.Execution.global

import scala.concurrent.Future

class ValidatorHttpHandler(validators: List[Validator]) extends HttpHandler {
  override def handle(connection: HttpConnection): Future[HttpConnection] = {
    ValidatorHttpHandler.validate(connection, validators).map(_.connection)
  }
}

object ValidatorHttpHandler {
  def validate(connection: HttpConnection,
               validators: List[Validator]): Future[ValidationResult] = if (validators.isEmpty) {
    Future.successful(ValidationResult.Continue(connection))
  } else {
    val validator = validators.head
    validator.validate(connection).flatMap {
      case Continue(c) => validate(c, validators.tail)
      case v: Redirect => Future.successful(v.copy(HttpHandler.redirect(v.connection, v.location)))
      case v: Error => {
        val modified = connection
          .modify(_.withStatus(HttpStatus(v.status, v.message)).withContent(Content.empty))
          .finish()
        Future.successful(v.copy(connection = modified))
      }
    }
  }
}