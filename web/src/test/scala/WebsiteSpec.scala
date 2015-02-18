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
import com.outr.net.http.HttpParameters

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
        website.pages.size shouldBe (0)

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

    "handle page scoped requests" in {
      val website = new Website[MapSession] {
        protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)
      }

      val url = "/testPageScope"

      val webpage = new Webpage(website)
      val handler = website.page(webpage, Scope.Page, url)
      handler.link shouldBe (url)

      {
        val request = new HttpRequest(new URL(path = url), Method.Get, HttpRequestHeaders.Empty, Map.empty)
        val response = website.onReceive(request, HttpResponse.NotFound)

        response.status shouldEqual (HttpResponseStatus.OK)
        response.content.asString.replaceAll("""\s""", "") shouldEqual ("""<!DOCTYPEhtml><html><head><title>Webpage</title><metaname="generator"content="Hyperscala"/><metacharset="UTF-8"/></head></html>""")
        website.pages.size shouldBe (1)
        website.pages.byId(webpage.pageId) should not be empty
      }

      {
        // A request with pageid should trigger a cached response
        val cachedRequest = new HttpRequest(new URL(path = url, parameters = HttpParameters(Map("pageId" -> List(webpage.pageId)))), Method.Get, HttpRequestHeaders.Empty, Map.empty)
        val response2 = website.onReceive(cachedRequest, HttpResponse.NotFound)

        response2.status shouldEqual (HttpResponseStatus.OK)
        response2.content.asString.replaceAll("""\s""", "") shouldEqual ("""<!DOCTYPEhtml><html><head><title>Webpage</title><metaname="generator"content="Hyperscala"/><metacharset="UTF-8"/></head></html>""")
        website.pages.size shouldBe (1)
      }

      website.removeContent(url)

      {
        val request = new HttpRequest(new URL(path = url), Method.Get, HttpRequestHeaders.Empty, Map.empty)
        val response = website.onReceive(request, HttpResponse.NotFound)

        response.status shouldEqual (HttpResponseStatus.NotFound)
        response.content.asString shouldBe ("404 Page not found: http://localhost" + url)
        website.pages.size shouldBe (1)
      }

    }


    "handle session scoped requests" in {
      val website = new Website[MapSession] {
        protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)
      }

      val url = "/testSessionScope"

      val webpage = new Webpage(website)
      webpage.rendered shouldBe(false)


      var sessionCount = 0
      webpage.session {
        sessionCount += 1
      }

      val handler = website.page(webpage, Scope.Session, url)

      handler.link shouldBe (url)

      {
        val request = new HttpRequest(new URL(path = url), Method.Get, HttpRequestHeaders.Empty, Map.empty)
        val response = website.onReceive(request, HttpResponse.NotFound)

        response.status shouldEqual (HttpResponseStatus.OK)
        response.content.asString.replaceAll("""\s""", "") shouldEqual ("""<!DOCTYPEhtml><html><head><title>Webpage</title><metaname="generator"content="Hyperscala"/><metacharset="UTF-8"/></head></html>""")
        website.pages.size shouldBe (1)
        website.pages.byId(webpage.pageId) should not be empty
      }

      sessionCount shouldBe (1)

      {
        // A request with pageid should trigger a cached response
        val cachedRequest = new HttpRequest(new URL(path = url, parameters = HttpParameters(Map("pageId" -> List(webpage.pageId)))), Method.Get, HttpRequestHeaders.Empty, Map.empty)
        val response2 = website.onReceive(cachedRequest, HttpResponse.NotFound)

        response2.status shouldEqual (HttpResponseStatus.OK)
        response2.content.asString.replaceAll("""\s""", "") shouldEqual ("""<!DOCTYPEhtml><html><head><title>Webpage</title><metaname="generator"content="Hyperscala"/><metacharset="UTF-8"/></head></html>""")
        website.pages.size shouldBe (1)
      }

      website.removeContent(url)

      {
        val request = new HttpRequest(new URL(path = url), Method.Get, HttpRequestHeaders.Empty, Map.empty)
        val response = website.onReceive(request, HttpResponse.NotFound)

        response.status shouldEqual (HttpResponseStatus.NotFound)
        response.content.asString shouldBe ("404 Page not found: http://localhost" + url)
        website.pages.size shouldBe (1)
      }

      webpage.rendered shouldBe(true)

      sessionCount shouldBe (2)
    }

  }
}
