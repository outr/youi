package org.hyperscala.ui.widgets

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.jquery.jQuery
import org.hyperscala.web._
import org.hyperscala.html._
import org.hyperscala.realtime.Realtime
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.hyperscala.javascript.JavaScriptContent.JSOption
import org.hyperscala.selector.Selector
import com.outr.net.http.session.Session

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
    select.require(this)

    val id = select.identity
    val options = JavaScriptContent.options(
      JSOption("formatResult", formatResult),
      JSOption("formatSelection", formatSelection),
      JSOption("escapeMarkup", escapeMarkup)
    )
    select.connected[Webpage[Session]] {
      case webpage => Realtime.sendJavaScript(webpage, "$('#%s').select2(%s);".format(id, options), selector = Some(Selector.id(id)), onlyRealtime = false)
    }
  }

  def enable(select: tag.Select) = select.connected[Webpage[Session]] {
    case webpage => Realtime.sendJavaScript(webpage, "$('#%s').select2('enable');".format(select.id()))
  }

  def disable(select: tag.Select) = select.connected[Webpage[Session]] {
    case webpage => Realtime.sendJavaScript(webpage, "$('#%s').select2('disable');".format(select.id()))
  }

  def set(select: tag.Select, value: String, delay: Int = 0) = select.connected[Webpage[Session]] {
    case webpage => Realtime.sendJavaScript(webpage, "$('#%s').select2('val', '%s');".format(select.id(), value), delay = delay)
  }

  override def dependencies = List(jQuery.LatestWithDefault)

  override def init[S <: Session](website: Website[S]) = {
    website.addClassPath("/select2/", "select2-3.4.5/")
  }

  override def load[S <: Session](page: Webpage[S]) = {
    page.head.contents += new tag.Link(href = "/select2/select2.css", rel = "stylesheet")
    if (debug) {
      page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/select2/select2.js")
    } else {
      page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/select2/select2.min.js")
    }

  }
}