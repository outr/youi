package io.youi.example

import io.youi.http.HttpConnection
import io.youi.server.validation.{ValidationResult, Validator}

import scala.concurrent.Future
import scribe.Execution.global

object AuthenticationExampleValidator extends Validator {
  override def validate(connection: HttpConnection): Future[ValidationResult] = {
    var result: ValidationResult = ValidationResult.Continue(connection)
    MySession.withHttpConnection(connection) { transaction =>
      if (transaction.session.username().isEmpty) {
        result = ValidationResult.Redirect(transaction.connection, "/login.html")
      }
      Future.successful(transaction)
    }.map(_ => result)
  }
}