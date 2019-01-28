package io.youi.example

import io.youi.http.HttpConnection
import io.youi.http.content.Content
import io.youi.net.ContentType
import io.youi.server.handler.{HttpHandler, SenderHandler}

import scala.concurrent.Future
import scribe.Execution.global

object SessionExample extends HttpHandler {
  override def handle(connection: HttpConnection): Future[HttpConnection] = MySession.withSession(connection) { transaction =>
    val session = transaction.session
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
    SenderHandler.handle(transaction.connection, Content.string(html.toString, ContentType.`text/html`)).map { c =>
      transaction.copy(connection = c)
    }
  }
}