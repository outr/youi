package org.hyperscala.ui.module

import com.outr.net.http.session.Session
import org.hyperscala.InclusionMode
import org.hyperscala.html._
import org.hyperscala.javascript.{EventProperty, JavaScriptString}
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryEvents extends Module with Logging {
  val name = "jQueryEvents"
  val version = Version(1)

  override val dependencies = List(jQuery)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    val html = webpage.html
    val b = new StringBuilder
    html.byTag[HTMLTag].foreach {
      case tag => {
        tag.xmlAttributes.foreach {
          case attribute: EventProperty if attribute.modified && attribute.name.startsWith("on") => {
            val id = tag.identity
            val name = attribute.name.substring(2)
            b.append(s"\t$$('#$id').$name(function (event) { ${attribute().content} });\r\n")
            attribute.inclusion = InclusionMode.Exclude     // Don't render it inline
          }
          case _ => // We only care about EventProperties
        }
      }
    }
    if (b.nonEmpty) {
      html.head.contents += new tag.Script {
        contents += JavaScriptString(
          """
            |$(document).ready(function () {
            |%s
            |});
          """.stripMargin.format(b))
      }
    }
  }
}
