package spec

import io.youi.http.{Content, HttpRequest, HttpResponse, Status}
import io.youi.net.{URL, URLMatcher}
import io.youi.server.{HttpHandler, Server, ServerImplementation}
import org.scalatest.{Matchers, WordSpec}

class ServerSpec extends WordSpec with Matchers {
  "TestHttpApplication" should {
    "receive OK for test.html" in {
      val response = TestServer.handle(HttpRequest(url = URL("http://localhost/test.html")), HttpResponse())
      response.status should equal(Status.OK)
    }
    "receive NotFound for other.html" in {
      val response = TestServer.handle(HttpRequest(url = URL("http://localhost/other.html")), HttpResponse())
      response.status should equal(Status.NotFound)
    }
  }
}

object TestServer extends Server {
  handlers.add(URLMatcher.path("/test.html"))(new HttpHandler {
    override def handle(request: HttpRequest, response: HttpResponse): HttpResponse = response.withContent(Content.string("test!"))
  })

  override protected val implementation: ServerImplementation = TestServerImplementation
}

object TestServerImplementation extends ServerImplementation {
  private var running = false

  override def start(): Unit = running = true

  override def stop(): Unit = running = false

  override def isRunning: Boolean = running
}