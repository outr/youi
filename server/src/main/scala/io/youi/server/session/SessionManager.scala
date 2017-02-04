package io.youi.server.session

import io.youi.http.HttpConnection

trait SessionManager[S <: Session] {
  protected def get(httpConnection: HttpConnection): Option[S]
  protected def set(httpConnection: HttpConnection, session: S): Unit
  protected def create(): S

  def apply(httpConnection: HttpConnection): S = get(httpConnection) match {
    case Some(session) => session
    case None => {
      val session = create()
      set(httpConnection, session)
      session
    }
  }
}