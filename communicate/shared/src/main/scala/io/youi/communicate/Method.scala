package io.youi.communicate

import scala.concurrent.Future

trait Method[Request, Response] extends Invocation {
  def requestPickler: Pickler[Request] = throw new UnsupportedOperationException("Not implemented.")
  def responsePickler: Pickler[Response] = throw new UnsupportedOperationException("Not implemented.")

  def apply(request: Request): Future[Response] = throw new UnsupportedOperationException("Not implemented.")
}

trait ClientMethod[Request, Response] extends Method[Request, Response]

trait ServerMethod[Request, Response] extends Method[Request, Response]