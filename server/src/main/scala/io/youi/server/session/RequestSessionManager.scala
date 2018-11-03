package io.youi.server.session

import io.youi.Store
import io.youi.http.HttpConnection

trait RequestSessionManager[S] extends StoreSessionManager[S] {
  override protected def store(httpConnection: HttpConnection): Store = httpConnection.store
}
