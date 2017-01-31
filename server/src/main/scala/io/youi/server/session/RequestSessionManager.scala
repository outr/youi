package io.youi.server.session

import io.youi.Store
import io.youi.server.Server

trait RequestSessionManager[S <: Session] extends StoreSessionManager[S] {
  override protected def store(server: Server): Store = server.httpConnection.store
}
