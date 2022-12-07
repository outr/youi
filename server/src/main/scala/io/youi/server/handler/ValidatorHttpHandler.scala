package io.youi.server.handler

import cats.effect.IO
import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.server.validation.ValidationResult.{Continue, Error, Redirect}
import io.youi.server.validation.{ValidationResult, Validator}

class ValidatorHttpHandler(validators: List[Validator]) extends HttpHandler {
  override def handle(connection: HttpConnection): IO[HttpConnection] = {
    ValidatorHttpHandler.validate(connection, validators).map(_.connection)
  }
}

object ValidatorHttpHandler {
  def validate(connection: HttpConnection,
               validators: List[Validator]): IO[ValidationResult] = if (validators.isEmpty) {
    IO.pure(ValidationResult.Continue(connection))
  } else {
    val validator = validators.head
    validator.validate(connection).flatMap {
      case Continue(c) => validate(c, validators.tail)
      case v: Redirect => IO(v.copy(HttpHandler.redirect(v.connection, v.location)))
      case v: Error => {
        val modified = connection
          .modify(_.withStatus(HttpStatus(v.status, v.message)).withContent(Content.empty))
          .finish()
        IO(v.copy(connection = modified))
      }
    }
  }
}