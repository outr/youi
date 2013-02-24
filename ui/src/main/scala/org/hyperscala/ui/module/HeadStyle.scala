package org.hyperscala.ui.module

import org.hyperscala.web.site.Webpage
import org.powerscala.Version
import java.util
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.tag.Style
import org.powerscala.bus.Routing
import org.hyperscala.css.StyleSheetProperty
import org.hyperscala.module._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object HeadStyle extends Module {
  def name = "headstyle"

  def version = Version(1)

  def init() = {}

  def load() = {
    val page = Webpage()
    val map = page.store.getOrSet("headStyleMap", new util.WeakHashMap[HTMLTag, HeadStyleTag]())
    page.intercept.renderAttribute {
      case ssa: StyleSheetProperty => Routing.Stop
    }
//    page.intercept.initStyle {
//      case tag: HTMLTag => map.get(tag) match {
//        case null => map.put(tag, new HeadStyleTag(page, tag))
//        case hst => hst.updateStyle()
//      }
//    }
    page.view.foreach {
      case tag: HTMLTag => map.put(tag, new HeadStyleTag(page, tag))
      case _ =>
    }
  }
}

class HeadStyleTag(page: Webpage, tag: HTMLTag) {
  private var style: Style = null

  // TODO: support id changing

  var listening: List[StyleSheetProperty] = Nil

  updateStyle()

  def updateStyle(): Unit = synchronized {
    val b = new StringBuilder
    // TODO: re configure
    /*tag.style.selectors.foreach {
      case selector => {
        buildStyle(b, selector)
        if (selector.loaded && !listening.contains(selector)) {
          selector().listeners.synchronous.filter.descendant() {
            case evt: PropertyChangeEvent if (evt.property.isInstanceOf[StyleSheetAttribute[_]]) => updateStyle()
          }
          listening = selector :: listening
        }
      }
    }
    if (b.length > 0) {
      if (style == null) {
        style = new Style(mimeType = "text/css", id = "%sStyle".format(tag.id()))
        page.head.contents += style
      }
      style.content := "\n%s".format(b)
    }*/
  }

  // TODO: re configure
  /*def buildStyle(b: StringBuilder, sp: StyleProperty) = {
    if (sp.loaded && sp.modified) {
      val s = sp().properties.collect {
        case p: StyleSheetAttribute[_] if (p.modified) => "\t%s: %s".format(p.name(), p.attributeValue)
      }.mkString(";\n")
      val selector = sp.name() match {
        case "style" => ""
        case n => ":%s".format(n)
      }
      if (s.nonEmpty) {
        if (tag.id() == null) {   // Make sure there's an ID to reference
          tag.id := Unique()
        }

        b.append("#%s%s {\n%s\n}\n".format(tag.id(), selector, s))
      }
    }
  }*/
}