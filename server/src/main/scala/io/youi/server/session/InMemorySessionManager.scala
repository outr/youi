package io.youi.server.session
import io.youi.MapStore
import io.youi.http.HttpConnection

import scala.concurrent.Future

/**
  * In-Memory session that only lives as long as the server is running
  *
  * @tparam Session the type of session
  */
trait InMemorySessionManager[Session] extends SessionManager[Session] {
  private def store = InMemorySessionManager.store

  override protected def loadBySessionId(sessionId: String,
                                         connection: HttpConnection): Future[Option[SessionTransaction[Session]]] = {
    Future.successful {
      store.get[Session](sessionId).map { session =>
        SessionTransaction(sessionId, session, connection)
      }
    }
  }

  override protected def save(transaction: SessionTransaction[Session]): Future[HttpConnection] = Future.successful {
    store(transaction.id) = transaction.session
    transaction.connection
  }
}

object InMemorySessionManager {
  private val store = new MapStore
}