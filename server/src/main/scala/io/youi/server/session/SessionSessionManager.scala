package io.youi.server.session

import io.youi.Store
import io.youi.http.HttpConnection

import scala.concurrent.duration._

trait SessionSessionManager[S <: Session] extends StoreSessionManager[S] {
  protected def timeout: FiniteDuration = 1.hour
  override protected def store(httpConnection: HttpConnection): Store = SessionStore(httpConnection, timeout)
}