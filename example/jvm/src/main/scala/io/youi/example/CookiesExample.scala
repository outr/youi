package io.youi.example

import com.outr.scribe.Logging
import io.youi.http.cookie.ResponseCookie
import io.youi.http.{Content, Headers, HttpConnection, HttpRequest, HttpResponse}
import io.youi.net.ContentType
import io.youi.server.handler.{HttpHandler, SenderHandler}

object CookiesExample extends HttpHandler with Logging {
  override def handle(connection: HttpConnection): Unit = {
    val request = connection.request
    val actionOption = request.url.parameters.value("action")
    actionOption match {
      case Some(action) => action match {
        case "setCookie" => {
          val name = request.url.parameters.value("name").getOrElse("myCookie")
          val value = request.url.parameters.value("value").getOrElse("default value")
          connection.update { response =>
            response.withRedirect("/cookies.html").withHeader(Headers.Response.`Set-Cookie`(ResponseCookie(name, value)))
          }
        }
      }
      case None => {
        val setCookieURL = "/cookies.html?action=setCookie&name=myCookie&value=hello%20cookies"
        val html = <html>
          <head>
            <title>Cookies Example</title>
          </head>
          <body>
            <h2>Cookies Example</h2>
            <hr/>
            <h4>Cookies Currently Set:</h4>{request.cookies.map { cookie =>
            <li>
              <strong>
                {cookie.name}
              </strong>
              :
              {cookie.value}
            </li>
          }}<hr/>
            <h4>Actions:</h4>
            <li><a href={setCookieURL}>Set a Sample Cookie</a></li>
          </body>
        </html>
        SenderHandler.handle(connection, Content.string(html.toString, ContentType.`text/html`))
      }
    }
  }
}
