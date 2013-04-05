package org.hyperscala.web.site

import com.outr.webcommunicator.Communicator
import java.util.UUID
import org.powerscala.log.Logging
import org.hyperscala.html.{tag, HTMLTag}

import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala._
import event.StylePropertyChangeEvent
import html.FormField
import css.{StyleSheetProperty, Style, StyleSheet}
import org.hyperscala.javascript.JavaScriptContent

import org.powerscala.json._
import scala.Some
import org.powerscala.hierarchy.event.ChildRemovedEvent
import org.powerscala.hierarchy.event.ChildAddedEvent
import svg.{Svg, SVGTag}
import util.parsing.json.JSON

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
    page.listeners.synchronous.filter.descendant() {
      case evt: ChildAddedEvent => childAdded(evt)
      case evt: ChildRemovedEvent => childRemoved(evt)
    }
    page.intercept.update {
      case page if (communicatorReceiver != null || communicatorSender != null) => {
        page.asInstanceOf[Webpage].checkIn()
      }
    }
    page.listeners.synchronous.filter(evt => true) {    // Accept all events on this pages' bus
      case evt: PropertyChangeEvent if (FormField.changingProperty == evt.property && FormField.changingValue == evt.newValue) => {
        // Ignore a change initialized by this connector (avoid recursive changes)
        debug("Ignoring change being applied: %s".format(FormField.changingValue))
      }
      case evt: StylePropertyChangeEvent => {
        styleChanged(evt.property.parent.asInstanceOf[HTMLTag], evt.styleSheet, evt.style, evt.newStyleValue)
      }
      case evt: PropertyChangeEvent => evt.property match {
        case property: PropertyAttribute[_] => property.parent match {
          case tag: IdentifiableTag => propertyChanged(tag, property, evt.oldValue, evt.newValue)
        }
        case _ => // Ignore other changes
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
    debug("propertyChanged: %s.%s from %s to %s".format(t.xmlLabel, property.name(), oldValue, newValue))
    if (t.root[Webpage].nonEmpty && !property.isInstanceOf[StyleSheetProperty] && !t.isInstanceOf[tag.Text]) {
      if (property == t.id && oldValue == null) {
        // Ignore initial id change as it is sent when added
      } else {
        Page().intercept.renderAttribute.fire(property) match {
          case Some(pa) => t match {
            case title: tag.Title if (property == title.content) => send(JavaScriptMessage("document.title = content;", property.attributeValue))
            case textual: Textual if (property == textual.content) => send(JavaScriptMessage("$('#%s').val(content);".format(t.id()), property.attributeValue))
            case input: tag.Input if (property == input.value) => send(JavaScriptMessage("$('#%s').val(content);".format(t.id()), property.attributeValue))
            case _ if (property() == false) => send(JavaScriptMessage("$('#%s').removeAttr('%s');".format(t.id(), property.name())))
            case _ => send(JavaScriptMessage("$('#%s').attr('%s', content);".format(t.id(), property.name()), property.attributeValue))
          }
          case None => // Attribute shouldn't render so we ignore it
        }
      }
    }
  }

  def styleChanged(tag: HTMLTag, ss: StyleSheet, style: Style[_], value: AnyRef): Unit = {
    val tagId = tag.id()
    if (style == null) {    // StyleSheet assigned, so we need to send everything
      ss.map.foreach {
        case (s, v) => styleChanged(tag, ss, s, v)
      }
    } else {
      val anyStyle = style.asInstanceOf[Style[AnyRef]]
      val styleName = anyStyle.cssName
      val styleValue = anyStyle.persistence.toString(value, value.getClass)
      send(JavaScriptMessage("$('#%s').css('%s', content);".format(tagId, styleName), styleValue))
    }
  }

  def childAdded(evt: ChildAddedEvent) = {
    val parent = evt.parent.asInstanceOf[HTMLTag with Container[HTMLTag]]
    evt.child match {
      case child: IdentifiableTag => {
        // Verifies the parent and child have an id
        parent.identity
        child.identity

        val index = parent.contents.indexOf(child)
        val variable = child match {
          case tag: SVGTag if (!tag.isInstanceOf[Svg]) => "parseSVG(content)"
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