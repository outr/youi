package io.youi.example

import io.youi.http.{Content, HttpConnection}
import io.youi.net.ContentType
import io.youi.server.handler.{HttpHandler, SenderHandler}
import io.youi.server.session.{Session, SessionSessionManager}

object SessionExample extends HttpHandler {
  override def handle(connection: HttpConnection): Unit = {
    val session = MySession(ServerExampleApplication)
    val html = <html>
      <head>
        <title>Session Example</title>
      </head>
      <body>
        <h2>Session Example</h2>
        <hr/>
        <h4>Session Created {(System.currentTimeMillis() - session.created) / 1000} seconds ago.</h4>
      </body>
    </html>
    SenderHandler.handle(connection, Content.string(html.toString, ContentType.`text/html`))
  }
}

class MySession extends Session {
  val created: Long = System.currentTimeMillis()
}

object MySession extends SessionSessionManager[MySession] {
  override protected def create(): MySession = new MySession
}