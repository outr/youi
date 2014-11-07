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
  val version = Version(1, 3, 0, 20140706, "alpha")

  override def dependencies = List(jQuery)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](page: Webpage[S]) = {
    val path = "//cdn.jsdelivr.net/rangy/1.3alpha.20140706"
    page.head.contents += new tag.Script(src = s"$path/rangy-core.js")
    page.head.contents += new tag.Script(src = s"$path/rangy-cssclassapplier.js")
    page.head.contents += new tag.Script(src = s"$path/rangy-selectionsaverestore.js")
    page.head.contents += new tag.Script(src = s"$path/rangy-serializer.js")
  }
}
