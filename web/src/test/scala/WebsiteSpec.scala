package org.hyperscala.web

import org.scalatest.{ Matchers, WordSpec }
import com.outr.net.http.session.MapSession
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.HttpResponse
import com.outr.net.Method
import com.outr.net.http.request.HttpRequestHeaders
import com.outr.net.URL
import com.outr.net.Protocol
import com.outr.net.http.response.HttpResponseStatus

/**
 * @author hrj <http://github.com/hrj>
 */
class WebsiteSpec extends WordSpec with Matchers {

  "Website" should {
    "handle requests" in {
      val website = new Website[MapSession] {
        protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)
      }

      val webpage = new Webpage(website)
      website.addHandler(webpage, "/test")

      {
        val request = new HttpRequest(new URL(path = "/test"), Method.Get, HttpRequestHeaders.Empty, Map.empty)
        val response = website.onReceive(request, HttpResponse.NotFound)

        response.status shouldEqual (HttpResponseStatus.OK)
        response.content.asString.replaceAll("""\s""", "") shouldEqual ("""<!DOCTYPEhtml><html><head><title>Webpage</title><metaname="generator"content="Hyperscala"/><metacharset="UTF-8"/></head></html>""")
      }

      website.removeContent("/test")

      {
        val request = new HttpRequest(new URL(path = "/test"), Method.Get, HttpRequestHeaders.Empty, Map.empty)
        val response = website.onReceive(request, HttpResponse.NotFound)

        response.status shouldEqual (HttpResponseStatus.NotFound)
        response.content.asString shouldBe ("404 Page not found: http://localhost/test")
      }
    }

    "handle not found requests" in {
      val website = new Website[MapSession] {
        protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)
      }

      website.init()

      val request = new HttpRequest(new URL(path = "/test"), Method.Get, HttpRequestHeaders.Empty, Map.empty)
      val response = website.onReceive(request, HttpResponse.NotFound)

      response.status shouldEqual(HttpResponseStatus.NotFound)
      response.content.asString.replaceAll("""\s""", "") shouldEqual ("""<html><head><title>404NotFound</title></head><body><h1><b>404</b>:NotFound</h1></body></html>""")
    }
  }
}
