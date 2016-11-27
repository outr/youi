package spec

import io.youi.http.{Content, HttpConnection, HttpRequest, HttpResponse, Status}
import io.youi.net.{ContentType, URL, URLMatcher}
import io.youi.server.handler.HttpHandler
import io.youi.server.test.TestServer
import org.scalatest.{Matchers, WordSpec}

class ServerSpec extends WordSpec with Matchers {
  val server = new TestServer

  "TestHttpApplication" should {
    "configure the TestServer" in {
      server.handlers.add(URLMatcher.path.exact("/test.html"))(new HttpHandler {
        override def handle(connection: HttpConnection): Unit = connection.update { response =>
          response.withContent(Content.string("test!", ContentType.`text/plain`))
        }
      })
    }
    "receive OK for test.html" in {
      val connection = new HttpConnection(HttpRequest(url = URL("http://localhost/test.html")))
      server.handle(connection)
      connection.response.status should equal(Status.OK)
    }
    "receive NotFound for other.html" in {
      val connection = new HttpConnection(HttpRequest(url = URL("http://localhost/other.html")))
      server.handle(connection)
      connection.response.status should equal(Status.NotFound)
    }
  }
}



