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

    "convert simple tag to scala" in {
      val tagStr = HTMLToScala.toScala(new tag.HTML())
      tagStr should fullyMatch regex """new tag.HTML\s*\{\s*\}\s*"""
    }

    "convert simple html to scala" in {
      val scalaStr = HTMLToScala.toScala(new tag.HTML(), "pkg", "cls")
      val expectedRegex = """\s*package pkg\s+(import .*\s*)*class cls extends tag.HTML\s*\{\s*\}\s*"""
      scalaStr should fullyMatch regex expectedRegex
    }

    "convert simple page to scala" in {
      val webpage = new Webpage(website)
      webpage.html.body.contents += "test"
      val scalaStr = HTMLToScala.toScala(webpage, "pkg", "cls")

      val meta1Regex = """head.contents\s*\+=\s*new\s*tag\.Meta\(name\s*=\s*"generator",\s*content\s*=\s*"Hyperscala"\)\s*\{\s*\}"""
      val meta2Regex = """head.contents\s*\+=\s*new\s*tag\.Meta\(charset\s*=\s*"UTF-8"\)\s*\{\s*\}"""
      val headRegex = meta1Regex + """\s+""" + meta2Regex
      val bodyRegex = """body.contents\s*\+=\s*"test""""
      val titleRegex = """title\s*:=\s*"Webpage"\s+"""
      val classRegex = titleRegex+headRegex+"\\s+"+bodyRegex
      val expectedRegex = """\s*package pkg\s+(import .*\s*)*class cls extends Webpage\s*\{\s*"""+classRegex+"""\s*}\s*"""

      scalaStr should fullyMatch regex expectedRegex
    }

  }
}
