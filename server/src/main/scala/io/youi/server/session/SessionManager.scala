package io.youi.server.session

import io.youi.server.Server

trait SessionManager[S <: Session] {
  protected def get(server: Server): Option[S]
  protected def set(server: Server, session: S): Unit
  protected def create(): S

  def apply(server: Server): S = get(server) match {
    case Some(session) => session
    case None => {
      val session = create()
      set(server, session)
      session
    }
  }
}