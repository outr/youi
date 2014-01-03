package org.hyperscala.jquery

import org.hyperscala.html.tag
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.Version
import org.hyperscala.module._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuery191 extends Module {
  def name = "jquery"
  var debug = false

  def version = Version(1, 9, 1)

  override def implements = List(jQuery)

  def init() = {
    Website().register("/js/jquery-1.9.1.min.js", "jquery/jquery-1.9.1.min.js")
    Website().register("/js/jquery-1.9.1.js", "jquery/jquery-1.9.1.js")
  }

  def load() = if (debug) {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery-1.9.1.js")
  } else {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery-1.9.1.min.js")
  }
}
