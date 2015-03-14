package org.hyperscala.ui

import com.outr.net.http.session.Session
import org.hyperscala.html.tag
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Rangy extends Module {
  val name = "rangy"
  val version = Version(1, 3, 0, 20150122, "alpha")

  override def dependencies = List(jQuery)

  override def init(website: Website) = {
    website.addClassPath("/rangy/", "rangy/")
  }

  override def load(page: Webpage) = {
    val path = "/rangy"
    page.head.contents += new tag.Script(src = s"$path/rangy-core.js")
    page.head.contents += new tag.Script(src = s"$path/rangy-classapplier.js")
    page.head.contents += new tag.Script(src = s"$path/rangy-selectionsaverestore.js")
    page.head.contents += new tag.Script(src = s"$path/rangy-serializer.js")
    page.head.contents += new tag.Script(src = s"$path/rangy-textrange.js")
  }
}
