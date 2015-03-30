package org.hyperscala

import org.scalatest.{Matchers, WordSpec}
import org.hyperscala.hello.{HelloSite, HelloPage}
import org.hyperscala.html._
import org.hyperscala.event.ClickEvent
import com.outr.net.http.request.HttpRequest
import com.outr.net.URL
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import com.outr.net.http.content.{StreamingContent, ContentType}
import java.io.ByteArrayOutputStream

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HelloSiteSpec extends WordSpec with Matchers {
  "HelloPage" when {
    val page = new HelloPage
    val expectedHTML = s"""<html><head><title>HelloPage</title><metaname="generator"content="Hyperscala"/><metaname="pageId"content="${page.pageId}"/><metacharset="UTF-8"/></head><body><h1>Hello,World!</h1></body></html>"""

    "instantiated directly" should {
      lazy val body = page.body
      "load correctly" in {
        page should not be null
      }
      "render the expected HTML" in {
        page.html.outputString.replaceAll("""\s""", "") should be(expectedHTML)
      }
      "add a div" in {
        body.contents += new tag.Div(id = "testDiv", content = "Test Div")
      }
      "add a button" in {
        body.contents += new tag.Button(id = "testButton") {
          clickEvent.on {
            case evt => body.getById[tag.Div]("testDiv").contents.replaceWith("Contents Changed!")
          }
        }
      }
      "verify initial contents of div" in {
        page.getById[tag.Div]("testDiv").outputChildrenString.trim should be("Test Div")
      }
      "click the button" in {
        val b = body.getById[tag.Button]("testButton")
        b.clickEvent.fire(new ClickEvent(b, Some(b)))
      }
      "verify modified contents of div" in {
        page.getById[tag.Div]("testDiv").outputChildrenString.trim should be("Contents Changed!")
      }
    }
    "referenced through an HTTP request" should {
      var response: HttpResponse = null
      "submit an HttpRequest and get an HttpResponse from HelloSite" in {
        val request = HttpRequest(URL.encoded("http://localhost/"))
        response = HelloSite.onReceive(request, HttpResponse.NotFound)
      }
      "validate the response status" in {
        response.status should be(HttpResponseStatus.OK)
      }
      "validate the response content type" in {
        response.content.contentType.mimeType should be(ContentType.HTML.mimeType)
      }
      "validate the response content" in {
        val baos = new ByteArrayOutputStream
        response.content.asInstanceOf[StreamingContent].stream(baos)
        val contentString = new String(baos.toByteArray, response.content.contentType.charSet).replaceAll("""\s""", "")
        contentString should fullyMatch regex """<!DOCTYPEhtml><html><head><title>HelloPage</title><metaname="generator"content="Hyperscala"/><metaname="pageId"content="([a-zA-Z0-9]{32})"/><metacharset="UTF-8"/></head><body><h1>Hello,World!</h1></body></html>""".r
      }
    }
  }
}
