package org.hyperscala.web

import org.hyperscala.html.tag
import org.hyperscala.io.HTMLToScala
import org.scalatest.Matchers
import org.scalatest.WordSpec

import com.outr.net.http.request.HttpRequest
import com.outr.net.http.session.MapSession

/**
 * @author hrj <http://github.com/hrj>
 */
class HTMLToScalaSpec extends WordSpec with Matchers {

  val website = new Website[MapSession] {
    protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)

  }

  "HTMLToScala" should {
    "convert simple html to page" in {
      val webpage = HTMLToScala.toPage(website, "<html><body>Test</body></html>")
      webpage.body.innerHTML should fullyMatch regex """\s*Test\s*"""
    }

    "convert simple html to scala" in {
      val tagStr = HTMLToScala.toScala(new tag.HTML())
      tagStr should fullyMatch regex """new tag.HTML\s*\{\s*\}\s*"""

      val webpage = new Webpage(website)
      val scalaStr = HTMLToScala.toScala(new tag.HTML(), "pkg", "cls")
      val expectedRegex = """\s*package pkg\s+(import .*\s*)*class cls extends tag.HTML\s*\{\s*\}\s*"""
      scalaStr should fullyMatch regex expectedRegex
    }

  }
}
