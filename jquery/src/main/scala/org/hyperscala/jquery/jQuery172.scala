package org.hyperscala.jquery

import org.hyperscala.html._
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.Version
import org.hyperscala.module._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuery172 extends Module {
  def name = "jquery"

  def version = Version(1, 7, 2)

  override def implements = List(jQuery)

  def init() = {
    Website().register("/js/jquery-1.7.2.js", "jquery/jquery-1.7.2.js")
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery-1.7.2.js")
  }
}
