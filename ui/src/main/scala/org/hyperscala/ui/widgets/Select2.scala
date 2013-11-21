package org.hyperscala.ui.widgets

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.jquery.jQuery
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import org.hyperscala.realtime.Realtime
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.hyperscala.javascript.JavaScriptContent.JSOption
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Select2 extends Module {
  val name = "select2"
  val version = Version(3, 4, 5)
  var debug = false

  val DontEscapeMarkup = JavaScriptString("function(m) { return m; }")

  def apply(select: tag.Select,
            formatResult: Option[JavaScriptContent] = None,
            formatSelection: Option[JavaScriptContent] = None,
            escapeMarkup: Option[JavaScriptContent] = None) = {
    Webpage().require(this)

    val id = select.identity
    val options = JavaScriptContent.options(
      JSOption("formatResult", formatResult),
      JSOption("formatSelection", formatSelection),
      JSOption("escapeMarkup", escapeMarkup)
    )
    Realtime.sendJavaScript("$('#%s').select2(%s);".format(id, options), selector = Selector.id(id), onlyRealtime = false)
  }

  def enable(select: tag.Select) = {
    Realtime.sendJavaScript("$('#%s').select2('enable');".format(select.id()))
  }

  def disable(select: tag.Select) = {
    Realtime.sendJavaScript("$('#%s').select2('disable');".format(select.id()))
  }

  def set(select: tag.Select, value: String, delay: Int = 0) = {
    Realtime.sendJavaScript("$('#%s').select2('val', '%s');".format(select.id(), value), delay = delay)
  }

  override def dependencies = List(jQuery.LatestWithDefault)

  def init() = {
    Website().addClassPath("/select2/", "select2-3.4.5/")
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Link(href = "/select2/select2.css", rel = "stylesheet")
    if (debug) {
      page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/select2/select2.js")
    } else {
      page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/select2/select2.min.js")
    }
  }
}