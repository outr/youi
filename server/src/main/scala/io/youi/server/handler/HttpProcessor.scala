package io.youi.server.handler

import io.youi.http.HttpConnection
import io.youi.server.validation.{ValidationResult, Validator}

/**
  * HttpProcessor extends HttpHandler to provide a clean and efficient mechanism to manage proper
  * matching, validation, and then processing.
  *
  * @tparam T the type a match will return to be used by process if validations pass
  */
trait HttpProcessor[T] extends HttpHandler {
  protected def validators(connection: HttpConnection): List[Validator]

  protected def matches(connection: HttpConnection): Option[T]

  protected def process(connection: HttpConnection, value: T): Unit

  override def handle(connection: HttpConnection): Unit = {
    matches(connection).foreach { value =>
      val validationResult = ValidatorHttpHandler.validate(connection, validators(connection))
      if (validationResult == ValidationResult.Continue) {
        process(connection, value)
      }
    }
  }
}