package org.hyperscala.jquery

import org.hyperscala.html.tag
import org.hyperscala.web.site.{Website, Webpage}
import org.powerscala.Version
import org.hyperscala.module._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object jQuery182 extends Module {
  def name = "jquery"

  def version = Version(1, 8, 2)

  override def implements = List(jQuery)

  def init() = {
    Website().register("/js/jquery-1.8.2.js", "jquery/jquery-1.8.2.min.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery-1.8.2.js")
  }
}
