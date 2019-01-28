package io.youi.server.session

import io.youi.http.{HttpConnection, HttpResponse}

/**
  * SessionTransaction is used for working with a session in a SessionManager. Modifications can be made to both the
  * session and the request (if requestModifiable is true)
  *
  * @param id the session's id
  * @param session the session in this transaction
  * @param connection the HttpConnection in this transaction
  * @param requestModifiable true if the request can be modified (this is usually only false when working with sessions
  *                          in a Connection)
  * @tparam Session the type of session
  */
case class SessionTransaction[Session](id: String,
                                       session: Session,
                                       connection: HttpConnection,
                                       requestModifiable: Boolean = true) {
  def modifySession(f: Session => Session): SessionTransaction[Session] = {
    copy(session = f(session))
  }

  def modifyRequest(f: HttpResponse => HttpResponse): SessionTransaction[Session] = {
    assert(requestModifiable, "This session transaction is not modifiable")
    copy(connection = connection.modify(f))
  }

  def modifyConnection(f: HttpConnection => HttpConnection): SessionTransaction[Session] = {
    assert(requestModifiable, "This session transaction is not modifiable")
    copy(connection = f(connection))
  }
}
