package io.youi.template

import io.youi.app.ClientApplication
import io.youi.{ActivationSupport, dom}
import org.scalajs.dom._

import scala.scalajs.js.annotation.JSExportTopLevel

object ClientTemplateApplication extends TemplateApplication with ClientApplication with ActivationSupport {
  @JSExportTopLevel("application")
  def main(): Unit = {
    val pages = dom.byId[html.Input]("template_pages").value.split(';').toSet
    fixAnchors(pages)

    activate()

    println("Template initialized!")
  }

  private def fixAnchors(pages: Set[String]): Unit = {
    dom.byTag[html.Anchor]("a").foreach { anchor =>
      val host = window.location.href.substring(0, window.location.href.lastIndexOf('/') + 1)
      val hrefOption = anchor.href match {
        case h if h.startsWith(host) && !h.endsWith("#") => Some(h.substring(host.length))
        case h => None
      }
      hrefOption match {
        case Some(href) => {
          if (pages.contains(href)) {
            anchor.href = s"/${href.substring(0, href.length - 5)}"
          }
        }
        case None => // Ignore
      }
    }
  }
}