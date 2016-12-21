package io.youi.communicate

import scala.concurrent.Future

trait Method[Request, Response] extends Invocation {
  def requestPickler: Pickler[Request]
  def responsePickler: Pickler[Response]

  def apply(request: Request): Future[Response]
}

trait ClientMethod[Request, Response] extends Method[Request, Response]

trait ServerMethod[Request, Response] extends Method[Request, Response]