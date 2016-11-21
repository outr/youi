package io.youi.server.session

import io.youi.Store
import io.youi.server.Server

trait StoreSessionManager[S <: Session] extends SessionManager[S] {
  protected val key: String = getClass.getName
  protected def store(server: Server): Store
  override protected def get(server: Server): Option[S] = store(server).get[S](key)
  override protected def set(server: Server, session: S): Unit = store(server).update[S](key, session)
}