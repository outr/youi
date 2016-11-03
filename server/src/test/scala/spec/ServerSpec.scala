package spec

import io.youi.http.{Content, HttpRequest, HttpResponse, Status}
import io.youi.net.{URL, URLMatcher}
import io.youi.server.test.TestServer
import io.youi.server.HttpHandler
import org.scalatest.{Matchers, WordSpec}

class ServerSpec extends WordSpec with Matchers {
  val server = new TestServer

  "TestHttpApplication" should {
    "configure the TestServer" in {
      server.handlers.add(URLMatcher.path.exact("/test.html"))(new HttpHandler {
        override def handle(request: HttpRequest, response: HttpResponse): HttpResponse = response.withContent(Content.string("test!"))
      })
    }
    "receive OK for test.html" in {
      val response = server.handle(HttpRequest(url = URL("http://localhost/test.html")), HttpResponse())
      response.status should equal(Status.OK)
    }
    "receive NotFound for other.html" in {
      val response = server.handle(HttpRequest(url = URL("http://localhost/other.html")), HttpResponse())
      response.status should equal(Status.NotFound)
    }
  }
}



