package io.youi.server.handler

import io.youi.http.HttpConnection
import io.youi.server.validation.ValidationResult._
import io.youi.server.validation.{ValidationError, ValidationResult, Validator}

/**
  * HttpProcessor extends HttpHandler to provide a clean and efficient mechanism to manage proper
  * matching, validation, and then processing.
  *
  * @tparam T the type a match will return to be used by process if validations pass
  */
trait HttpProcessor[T] extends HttpHandler {
  protected def validators: List[Validator]

  protected def matches(connection: HttpConnection): Option[T]

  protected def validate(connection: HttpConnection): ValidationResult = {
    val failures = validators.map(_.validate(connection)).collect {
      case result if result != Continue => result
    }
    failures.headOption.getOrElse(Continue)
  }

  protected def process(connection: HttpConnection, value: T): Unit

  override def handle(connection: HttpConnection): Unit = {
    matches(connection).foreach { value =>
      validate(connection) match {
        case Continue => process(connection, value)
        case Redirect(location) => connection.update(_.withRedirect(location))
        case Error(status, message) => throw new ValidationError(status, message)
      }
    }
  }
}