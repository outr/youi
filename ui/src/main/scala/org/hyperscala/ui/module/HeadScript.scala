package org.hyperscala.ui.module

import org.hyperscala.web.Webpage
import org.powerscala.Version
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.tag.Script
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.Unique
import org.hyperscala.javascript.{EventProperty, JavaScriptString}
import org.hyperscala.module._
import org.hyperscala.jquery.jQuery
import org.powerscala.hierarchy.event.Descendants

/**
 * @author Matt Hicks <matt@outr.com>
 */
object HeadScript extends Module {
  def name = "headscript"

  def version = Version(1)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  def init() = {}

  def load() = {
    val page = Webpage()
    page.intercept.renderAttribute.on {
      case ep: EventProperty => None    // Don't render JavaScript in HTML
    }

    page.intercept.init.on {
      case tag: HTMLTag => new HeadScriptTag(page, tag)
    }

    page.view.foreach {
      case tag: HTMLTag => new HeadScriptTag(page, tag)
      case _ =>
    }
  }
}

class HeadScriptTag(page: Webpage, tag: HTMLTag) {
  private var script: Script = null

  // TODO: support id changing

  tag.listen[PropertyChangeEvent[_], Unit, Unit]("change", Descendants) {
    case evt => if (evt.property.isInstanceOf[EventProperty] && evt.property.parent == tag) {
      updateScript()
    }
  }

  updateScript()

  def updateScript() = {
    val b = new StringBuilder
    tag.xmlAttributes.foreach {
      case ep: EventProperty if (ep.shouldRender) => {
        if (tag.id() == null) {   // Make sure there's an ID to reference
          tag.id := Unique()
        }

        b.append("\n\t$('#%s').unbind('%s');".format(tag.id(), ep.name.substring(2)))
        b.append("\n\t$('#%s').bind('%s', function(event, data) {\n\t\t".format(tag.id(), ep.name.substring(2)))
        b.append(ep().content)
        b.append("\n\t});\n")
      }
      case _ =>
    }
    if (b.length > 0) {
      if (script == null) {
        script = new Script(mimeType = "text/javascript", id = "%sScript".format(tag.id()))
        page.head.contents += script
      }
      val s = "\n$(function() {%s});".format(b)
      script.contents.replaceWith(JavaScriptString(s))
    }
  }
}