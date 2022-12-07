package io.youi.server.handler

import cats.effect.IO
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

  protected def process(connection: HttpConnection, value: T): IO[HttpConnection]

  override def handle(connection: HttpConnection): IO[HttpConnection] = {
    matches(connection).map { value =>
      ValidatorHttpHandler.validate(connection, validators(connection)).flatMap {
        case ValidationResult.Continue(c) => process(c, value)
        case vr => IO.pure(vr.connection)
      }
    }.getOrElse(IO.pure(connection))
  }
}