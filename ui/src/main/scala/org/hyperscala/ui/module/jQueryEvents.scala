package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.jquery.jQuery
import org.hyperscala.web.site.Webpage
import org.hyperscala.javascript.{JavaScriptString, EventProperty}

import org.hyperscala.html._
import org.hyperscala.InclusionMode
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryEvents extends Module with Logging {
  val name = "jQueryEvents"
  val version = Version(1)

  override val dependencies = List(jQuery.LatestWithDefault)

  def init() = {}

  def load() = {
    val html = Webpage().html
    val b = new StringBuilder
    html.byTag[HTMLTag].foreach {
      case tag => {
        tag.xmlAttributes.foreach {
          case attribute: EventProperty if (attribute.modified && attribute.name.startsWith("on")) => {
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
