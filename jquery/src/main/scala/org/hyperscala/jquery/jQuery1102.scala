package org.hyperscala.jquery

import org.hyperscala.html.tag
import org.hyperscala.web.Webpage
import org.powerscala.Version
import org.hyperscala.module._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object jQuery1102 extends Module {
  def name = "jquery"
  var debug = false

  def version = Version(1, 10, 2)

  override def implements = List(jQuery)

  def init() = {}

  def load() = if (debug) {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.js")
  } else {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js")
  }
}
