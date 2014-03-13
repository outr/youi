package org.hyperscala.ui

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import scala.collection.mutable.ListBuffer
import org.hyperscala.javascript.JavaScriptString


/**
 * @author Matt Hicks <matt@outr.com>
 */
object BasketJS extends Module {
  def name = "basketjs"

  def version = Version(0, 4, 0)

  def init() = {
    Website().register("/js/basket.full.min.js", "basketjs/basket.full.min.js")
    Website().register("/js/basket.full.map", "basketjs/basket.full.map")
  }

  def load() = {
    Webpage().head.onBeforeRender {
      Webpage().store("basketjs") = new BasketJSPage(Webpage())
    }
  }
}

class BasketJSPage(page: Webpage) {
  val basketScriptURLS = ListBuffer.empty[String]
  val basketScriptContents = ListBuffer.empty[String]

  page.head.byTag[tag.Script].toList.foreach {              // Process and remove existing scripts
    case s => {
      if (s.src() == null || s.src().trim.isEmpty) {        // Body script
        basketScriptContents += s.scriptString
      } else {                                              // URL script
        basketScriptURLS += s.src()
      }
      s.removeFromParent()                                  // Remove the script from the parent
    }
  }

  page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/basket.full.min.js")
  val scriptString =
    s"""
      |basket.require(
      |${basketScriptURLS.map(url => s"   { url: '$url' }").mkString(",\r\n")}
      |).then(function() {
      |${basketScriptContents.mkString("\r\n\r\n")}
      |});
    """.stripMargin
  page.head.contents += new tag.Script(content = JavaScriptString(scriptString))
}