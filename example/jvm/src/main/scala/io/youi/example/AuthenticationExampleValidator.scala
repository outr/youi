package io.youi.example

import io.youi.http.HttpConnection
import io.youi.server.validation.{ValidationResult, Validator}

object AuthenticationExampleValidator extends Validator {
  override def validate(connection: HttpConnection): ValidationResult = MySession(connection).username() match {
    case Some(username) => ValidationResult.Continue
    case None => ValidationResult.Redirect("/login.html")
  }
}
