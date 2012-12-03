package org.hyperscala.ui.widgets

import org.hyperscala.web.module.Module
import org.powerscala.Version
import org.hyperscala.web.site.Webpage
import org.hyperscala.html.tag
import org.hyperscala.web.site.realtime.Realtime
import com.outr.webcommunicator.netty.handler.PathHandler

/**
 * @author Matt Hicks <matt@outr.com>
 */
object CKEditor extends Module {
  def name = "ckeditor"

  def version = Version(4, 0)

  override def dependencies = List(Realtime)

  def load(page: Webpage) = {
    page.website.register(PathHandler("/cke/", "ckeditor/"))

    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/cke/ckeditor.js")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/cke/richeditor.js")
  }
}