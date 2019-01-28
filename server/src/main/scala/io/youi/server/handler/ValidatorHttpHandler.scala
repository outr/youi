package io.youi.server.handler

import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.server.validation.ValidationResult.{Continue, Error, Redirect}
import io.youi.server.validation.{ValidationResult, Validator}

import scala.concurrent.Future
import scribe.Execution.global

class ValidatorHttpHandler(validators: List[Validator]) extends HttpHandler {
  override def handle(connection: HttpConnection): Future[HttpConnection] = {
    ValidatorHttpHandler.validate(connection, validators).map(_._1)
  }
}

object ValidatorHttpHandler {
  def validate(connection: HttpConnection,
               validators: List[Validator]): Future[(HttpConnection, ValidationResult)] = if (validators.isEmpty) {
    Future.successful(connection -> ValidationResult.Continue)
  } else {
    val validator = validators.head
    validator.validate(connection).flatMap {
      case Continue => validate(connection, validators.tail)
      case v: Redirect => Future.successful(HttpHandler.redirect(connection, v.location) -> v)
      case v: Error => {
        val modified = connection
          .modify(_.withStatus(HttpStatus(v.status, v.message)).withContent(Content.empty))
          .finish()
        Future.successful(modified -> v)
      }
    }
  }
}