package io.youi.client

import io.youi.http.cookie.ResponseCookie

class SessionManager(private var _session: Session = Session()) {
  def session: Session = _session

  def apply(cookies: List[ResponseCookie]): Session = synchronized {
    val filtered = session.cookies.filterNot(cookies.contains)
    _session = session.copy(filtered ::: cookies)
    session
  }
}