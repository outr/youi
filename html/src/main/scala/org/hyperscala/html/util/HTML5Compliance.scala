package org.hyperscala.html.util

import org.hyperscala.Markup
import org.jdom2.Attribute
import org.powerscala.event.Intercept
import org.hyperscala.html._
import org.hyperscala.css.attributes.{Length, BorderCollapse}
import org.powerscala.Priority

/**
 * HTML5Compliance, when enabled, will dynamically convert previous version of HTML and XHTML to HTML5 compliance during
 * parsing.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object HTML5Compliance {
  private val listener = Markup.attributeSet.create(Priority.Normal) {
    case (markup, attribute) => process(markup, attribute)
  }

  def enable() = if (!Markup.listeners().contains(listener)) {
    Markup.attributeSet += listener
  }

  def disable() = Markup.attributeSet -= listener

  def process(markup: Markup, attribute: Attribute) = {
    val name = attribute.getName
    val value = attribute.getValue
    markup match {
      case t: HTMLTag if name == "width" => {
        t.style.width := Length(value)
        Intercept.Stop
      }
      case table: tag.Table => name match {
        case "cellpadding" => {
          table.style.padding := s"${value}px"      // TODO: shouldn't this be on td and th, not the table?
          Intercept.Stop
        }
        case "cellspacing" => {
          table.style.borderCollapse := BorderCollapse.Separate
          table.style.borderSpacing := s"${value}px"
          Intercept.Stop
        }
      }
      case th: tag.Th if name == "rowspan" => {

        Intercept.Stop
      }
      case td: tag.Td => name match {
        case "valign" => {
          td.style.verticalAlign := new Length(value)
          Intercept.Stop
        }
      }
      case _ => {
        println(s"Markup: ${markup.getClass} - ${attribute.getName} - [${attribute.getValue}]")
        Intercept.Continue
      }
    }
  }
}
