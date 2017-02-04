package io.youi.server.session

import io.youi.Store
import io.youi.http.HttpConnection

trait StoreSessionManager[S <: Session] extends SessionManager[S] {
  protected val key: String = getClass.getName
  protected def store(httpConnection: HttpConnection): Store
  override protected def get(httpConnection: HttpConnection): Option[S] = store(httpConnection).get[S](key)
  override protected def set(httpConnection: HttpConnection, session: S): Unit = store(httpConnection).update[S](key, session)
}