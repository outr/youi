package org.hyperscala.ui

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.tag
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Rangy extends Module {
  val name = "rangy"
  val version = Version(1, 2, 3)

  override def dependencies = List(jQuery.LatestWithDefault)

  def init() = {
    Website().register(PathHandler("/rangy-1.2.3/", "rangy-1.2.3/"))
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/rangy-1.2.3/rangy-core.js")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/rangy-1.2.3/rangy-cssclassapplier.js")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/rangy-1.2.3/rangy-selectionsaverestore.js")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/rangy-1.2.3/rangy-serializer.js")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/rangy-1.2.3/rangyinputs-jquery-1.1.2.min.js")
  }
}
