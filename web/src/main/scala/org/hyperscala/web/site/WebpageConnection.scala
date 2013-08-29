package org.hyperscala.web.site

import com.outr.webcommunicator.Communicator
import java.util.UUID
import org.powerscala.log.Logging
import org.hyperscala.html.{tag, HTMLTag}

import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala._
import html.FormField
import org.hyperscala.css.{StyleSheet, StyleSheetAttribute, Style}
import org.hyperscala.javascript.JavaScriptContent

import org.powerscala.json._
import scala.Some
import org.powerscala.hierarchy.event.{Descendants, ChildRemovedEvent, ChildAddedEvent}
import svg.{Svg, SVGTag}
import util.parsing.json.JSON
import org.powerscala.hierarchy.ChildLike

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebpageConnection(val id: UUID) extends Communicator with Logging {
  debug("WebpageConnection created: %s".format(id))

  private var _page: Webpage = _

  def page = _page
  def page_=(page: Webpage) = {
//    info("Initialize page with WebpageConnection")
    _page = page
    initializePage()
  }

  def initializePage() = {
    // Listen for changes
    page.childAdded.listen(Descendants) {
      case evt => childAdded(evt)
    }
    page.childRemoved.listen(Descendants) {
      case evt => childRemoved(evt)
    }
    page.intercept.update.on {
      case page if communicatorReceiver != null || communicatorSender != null => {
        page.asInstanceOf[Webpage].checkIn()
      }
      case _ => // Ignore
    }
    page.head.styleSpaces.removedEvent.on {
      case evt => styleSheetRemoved(evt.styleSheet)
    }
    page.listen[PropertyChangeEvent[_], Unit, Unit]("change", Descendants) {
      case evt if FormField.changingProperty == evt.property && FormField.changingValue == evt.newValue => {
        // Ignore a change initialized by this connector (avoid recursive changes)
        debug("Ignoring change being applied: %s".format(FormField.changingValue))
      }
      case evt => evt.property match {
        case property: PropertyAttribute[_] => ChildLike.parentOf(property) match {
          case tag: IdentifiableTag => property match {
            case ssa: StyleSheetAttribute[_] => styleChanged(s"#${tag.identity}", ssa.style, evt.newValue.asInstanceOf[AnyRef])
            case _ => propertyChanged(tag, property, evt.oldValue, evt.newValue)
          }
          case styleSheet: StyleSheet => {
            val ssa = property.asInstanceOf[StyleSheetAttribute[_]]
            styleSheetChanged(styleSheet.selectorString, ssa.style, evt.newValue.asInstanceOf[AnyRef])
          }
        }
        case _ => // Ignore others
      }
    }
  }

  def receive(event: String, id: String, message: String) = WebContext.contextualize(page) {
    try {
      val map = JSON.parseFull(message).getOrElse(throw new RuntimeException("Failed to parse: %s".format(message))).asInstanceOf[Map[String, Any]]
      page.html.byId[IdentifiableTag](id) match {
        case Some(tag) => tag.receive(event, Message(message, map))
        case None => warn("Unable to find tag by id: %s to fire event: %s for message: %s".format(id, event, message))
      }
    } catch {
      case t: Throwable => page.errorThrown(t)
    }
  }

  def propertyChanged(t: IdentifiableTag, property: PropertyAttribute[_], oldValue: Any, newValue: Any) = {
    debug("propertyChanged: %s.%s from %s to %s".format(t.xmlLabel, property.name, oldValue, newValue))
    if (t.root[Webpage].nonEmpty && !property.isInstanceOf[StyleSheetAttribute[_]] && !t.isInstanceOf[tag.Text]) {
      if (property == t.id && oldValue == null) {
        // Ignore initial id change as it is sent when added
      } else {
        Page().intercept.renderAttribute.fire(property) match {
          case Some(pa) => t match {
            case title: tag.Title if property.name == "content" => send(JavaScriptMessage("document.title = content;", property.attributeValue))
            case textual: Textual if property.name == "content" => textual match {
              case option: tag.Option => send(JavaScriptMessage("$('#%s').html(content);".format(t.id()), property.attributeValue))
              case _ => send(JavaScriptMessage("$('#%s').val(content);".format(t.id()), property.attributeValue))
            }
            case input: tag.Input if property.name == "value" => send(JavaScriptMessage("$('#%s').val(content);".format(t.id()), property.attributeValue))
            case input: tag.Input if property.name == "checked" => send(JavaScriptMessage(s"$$('#${t.identity}').prop('checked', ${property()});"))
            case option: tag.Option if property.name == "selected" => {
              if (option.selected()) {
                val select = option.parent.asInstanceOf[tag.Select]
                if (select.multiple()) {
                  throw new RuntimeException("Multiple Select Currently not supported!")
                } else {
                  send(JavaScriptMessage(s"$$('#${select.identity}').val(content);", content = option.value()))
                }
              }
            }
//            case option: tag.Option if (property.name == "selected") => if (property() == true) send(JavaScriptMessage(s"$$('#${t.id()}').attr('${property.name}', ${property()});"))
            case _ if property() == false => send(JavaScriptMessage("$('#%s').removeAttr('%s');".format(t.id(), property.name)))
//            case _ if (property() == true) => send(JavaScriptMessage(s"$$('#${t.id()}').attr('${property.name}', '${property.name}');"))
            case _ => send(JavaScriptMessage("$('#%s').attr('%s', content);".format(t.id(), property.name), property.attributeValue))
          }
          case None => // Attribute shouldn't render so we ignore it
        }
      }
    }
  }

  def styleChanged(selector: String, style: Style[_], value: AnyRef): Unit = {
    val anyStyle = style.asInstanceOf[Style[AnyRef]]
    val cssName = style.cssName
    val cssValue = anyStyle.persistence.toString(value, cssName, value.getClass)
    send(JavaScriptMessage("$('%s').css('%s', content);".format(selector, cssName), cssValue))
  }

  def styleSheetChanged(selector: String, style: Style[_], value: AnyRef): Unit = {
    val anyStyle = style.asInstanceOf[Style[AnyRef]]
    val cssName = style.cssName
    val cssValue = value match {
      case null => ""
      case _ => anyStyle.persistence.toString(value, cssName, value.getClass)
    }
    send(JavaScriptMessage(s"$$.stylesheet('$selector', '$cssName', content)", cssValue))
  }

  def styleSheetRemoved(styleSheet: StyleSheet) = {
    val selector = styleSheet.selectorString
    send(JavaScriptMessage(s"$$.stylesheet('$selector').css(null)"))
  }

  def childAdded(evt: ChildAddedEvent) = {
    val parent = evt.parent.asInstanceOf[IdentifiableTag with Container[IdentifiableTag]]
    evt.child match {
      case child: IdentifiableTag => {
        // Verifies the parent and child have an id
        parent.identity
        child.identity

        val index = parent.contents.indexOf(child)
        val variable = child match {
          case tag: SVGTag if !tag.isInstanceOf[Svg] => "parseSVG(content)"
          case _ => "content"
        }
        val instruction = if (index == parent.contents.length - 1) {    // Append to the end
          "$('#%s').append(%s);".format(parent.id(), variable)
        } else if (index == 0) {                                   // Append before
        val after = parent.contents(1)
          "$('#%s').before(%s);".format(after.id(), variable)
        } else {
          val before = parent.contents(index - 1)
          "$('#%s').after(%s);".format(before.id(), variable)
        }
        val content = child.outputString
        send(JavaScriptMessage(instruction, content))
      }
      case js: JavaScriptContent => send(JavaScriptMessage(js.content))
    }
  }

  def childRemoved(evt: ChildRemovedEvent) = evt.child match {
    case text: tag.Text => {
      val parent = evt.parent.asInstanceOf[HTMLTag with Container[HTMLTag]]
      val index = parent.contents.indexOf(text)
      val instruction = "$('#%s').contents().eq(%s).remove();".format(parent.id(), index)
      send(JavaScriptMessage(instruction))
    }
    case tag: IdentifiableTag => send(JavaScriptMessage("$('#%s').remove();".format(tag.id())))
    case js: JavaScriptContent => // TODO: evaluate? - Previously nothing was needed here
  }

  def send(js: JavaScriptMessage): Unit = {
    val message = generate(js)
    send("eval", message)
  }

  override def send(event: String, message: String) {
    debug("Sending event: %s, message: %s".format(event, message))
    super.send(event, message)
  }
}

case class JavaScriptMessage(instruction: String, content: String = null)