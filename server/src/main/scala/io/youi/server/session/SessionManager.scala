package io.youi.server.session

import io.youi.http.{Connection, HttpConnection}

trait SessionManager[S <: Session] {
  protected def get(httpConnection: HttpConnection): Option[S]
  protected def set(httpConnection: HttpConnection, session: S): Unit
  protected def create(httpConnection: HttpConnection): S

  def apply(httpConnection: HttpConnection): S = get(httpConnection) match {
    case Some(session) => session
    case None => {
      val session = create(httpConnection)
      set(httpConnection, session)
      session
    }
  }

  def apply(connection: Connection): S = apply(connection.store[HttpConnection]("httpConnection"))

  def byHttpConnections(connections: Set[HttpConnection]): Set[S] = connections.map(apply)

  def byConnections(connections: Set[Connection]): Set[S] = connections.map(apply)
}