package org.hyperscala.web

import org.hyperscala.html.{StyleProperty, HTMLTag}
import org.hyperscala.css.StyleSheetAttribute
import org.powerscala.bus.Routing
import org.hyperscala.html.tag.Style
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.Unique
import java.util

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait HeadStyle {
  this: HTMLPage =>

  private val map = new util.WeakHashMap[HTMLTag, HeadStyleTag]()

  intercept.renderAttribute {
    case ssa: StyleSheetAttribute[_] => Routing.Stop
  }

  intercept.initStyle {
    case tag: HTMLTag => {
      map.get(tag) match {
        case null => map.put(tag, new HeadStyleTag(this, tag))
        case hst => hst.updateStyle()
      } // TODO: support removal if style changes
    }
  }
}

class HeadStyleTag(page: HTMLPage, tag: HTMLTag) {
  private var style: Style = null

  // TODO: support id changing

  var listening: List[StyleProperty] = Nil

  updateStyle()

  def updateStyle(): Unit = synchronized {
    val b = new StringBuilder
    tag.style.selectors.foreach {
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
    }
  }

  def buildStyle(b: StringBuilder, sp: StyleProperty) = {
    if (sp.loaded && sp.modified) {
      val s = sp().properties.collect {
        case p: StyleSheetAttribute[_] if (p.modified) => "\t%s: %s".format(p.name(), p.attributeValue)
      }.mkString(",\n")
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
  }
}