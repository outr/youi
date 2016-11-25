package io.youi.communicate

import scala.concurrent.Future

trait Call[Response] extends Invocation {
  def responsePickler: Pickler[Response] = throw new UnsupportedOperationException("Not implemented.")

  def apply(): Future[Response] = throw new UnsupportedOperationException("Not implemented.")
}

trait ClientCall[Response] extends Call[Response]

trait ServerCall[Response] extends Call[Response]