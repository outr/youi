package org.hyperscala.ui

import org.hyperscala.module._
import org.hyperscala.web.site.{Website, Webpage}
import org.powerscala.Version
import org.hyperscala.web.site.realtime.Realtime
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object PageChangeWarning extends Module {
  def name = "pagechangewarning"

  def version = Version(1)

  override def dependencies = List(Realtime)

  def load() = {
    Website().register("/js/page_change_warning.js", "page_change_warning.js")
    Webpage().head.contents += new tag.Script(src = "/js/page_change_warning.js")
  }

  def warn(message: String) = {
    val m = message match {
      case null => "null"
      case _ => "'%s'".format(message)
    }
    Webpage().body.contents += new tag.Script {
      contents += new JavaScriptString("setPageChangeWarning(%s);".format(m))
    }
  }
}
