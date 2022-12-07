package io.youi.server.session

import cats.effect.IO
import io.youi.MapStore
import io.youi.http.HttpConnection

/**
  * In-Memory session that only lives as long as the server is running
  *
  * @tparam Session the type of session
  */
trait InMemorySessionManager[Session] extends SessionManager[Session] {
  private def store = InMemorySessionManager.store

  override protected def loadBySessionId(sessionId: String,
                                         connection: HttpConnection): IO[Option[SessionTransaction[Session]]] = {
    IO {
      store.get[Session](sessionId).map { session =>
        SessionTransaction(sessionId, session, connection)
      }
    }
  }

  override protected def save(transaction: SessionTransaction[Session]): IO[SessionTransaction[Session]] = IO {
    store(transaction.id) = transaction.session
    transaction
  }
}

object InMemorySessionManager {
  private val store = new MapStore
}