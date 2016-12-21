package io.youi.communicate

import scala.concurrent.Future

trait Call[Response] extends Invocation {
  def responsePickler: Pickler[Response]

  def apply(): Future[Response]
}

trait ClientCall[Response] extends Call[Response]

trait ServerCall[Response] extends Call[Response]