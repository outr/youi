package io.youi.example

import reactify.Var
import io.youi.http.HttpConnection
import io.youi.server.session.{Session, SessionSessionManager}

class MySession extends Session {
  val created: Long = System.currentTimeMillis()
  val username: Var[Option[String]] = Var(None)
}

object MySession extends SessionSessionManager[MySession] {
  override protected def create(httpConnection: HttpConnection): MySession = new MySession
}