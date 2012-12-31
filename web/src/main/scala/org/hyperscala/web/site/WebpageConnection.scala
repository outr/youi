package org.hyperscala.web.site

import com.outr.webcommunicator.Communicator
import java.util.UUID
import org.powerscala.Logging
import org.hyperscala.html.{tag, HTMLTag}

import org.hyperscala.event.{StylePropertyChangeEvent, FormSubmit, JavaScriptEvent}
import org.hyperscala.html.attributes.Method
import org.powerscala.hierarchy.event.{ChildRemovedEvent, ChildAddedEvent}
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.{Container, Textual, Page, PropertyAttribute}
import org.hyperscala.css.{Style, StyleSheet}
import org.hyperscala.javascript.JavaScriptContent
import org.powerscala.property.MutableProperty

import org.powerscala.json._
import org.hyperscala.svg.SVGTag
import org.hyperscala.svg.event._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebpageConnection(val id: UUID) extends Communicator with Logging {
  debug("WebpageConnection created: %s".format(id))

  var applyingProperty: MutableProperty[_] = _
  var applyingValue: Any = _

  private var _page: Webpage = _

  def page = _page
  def page_=(page: Webpage) = {
//    info("Initialize page with WebpageConnection")
    _page = page
    initializePage()    // TODO: why does commenting this out throw OutOfContextExceptions?
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
      case evt: PropertyChangeEvent if (applyingProperty == evt.property && applyingValue == evt.newValue) => {
        // Ignore a change initialized by this connector (avoid recursive changes)
//        info("Ignoring change being applied: %s".format(applyingValue))
        applyingProperty = null   // Only necessary to ignore the first event
        applyingValue = null
      }
      case evt: StylePropertyChangeEvent => styleChanged(evt.styleSheet, evt.style, evt.newStyleValue)
      case evt: PropertyChangeEvent => evt.property match {
        case property: PropertyAttribute[_] => property.parent match {
          case tag: HTMLTag => propertyChanged(tag, property, evt.oldValue, evt.newValue)
//          case ss: StyleSheet => styleChanged(ss, property)
        }
        case _ => // Ignore other changes
      }
    }
  }

  def receive(event: String, message: String) = WebContext.wrap(page) {
//    info("Receive: %s - %s".format(event, message))
    try {
      event match {
        case "event" => receiveJavaScriptEvent(parse[SimpleEvent](message))
        case "change" => receiveJavaScriptChange(parse[SimpleChangeEvent](message))
        case "keyEvent" => receiveJavaScriptKeyEvent(parse[SimpleKeyEvent](message))
        case "svgMouseEvent" => receiveSVGMouseEvent(parse[SimpleSVGMouseEvent](message))
        case "svgMutationEvent" => receiveSVGMutationEvent(parse[SimpleSVGMutationEvent](message))
        case "svgUIEvent" => receiveSVGUIEvent(parse[SimpleSVGUIEvent](message))
        case "svgEvent" => receiveSVGEvent(parse[SimpleEvent](message))
        case _ => info("WebpageConnection(%s) received event: %s with message: %s from the client (Page: %s)!".format(id, event, message, page))
      }
    } catch {
      case t: Throwable => page.errorThrown(t)
    }
  }

  private def receiveJavaScriptEvent(message: SimpleEvent) = page.html.byId[HTMLTag](message.id) match {
    case Some(tag) => {
      val evt = JavaScriptEvent.create(tag, message.event)
      tag.fire(evt)
    }
    case None => warn("Unable to find tag by id: %s to fire event: %s".format(message.id, message.event))
  }

  private def receiveJavaScriptChange(message: SimpleChangeEvent) = page.html.byId[HTMLTag](message.id) match {
    case Some(t) => t match {
      case button: tag.Button => button.fire(FormSubmit(Method.Post))
      case _ => {
        applyingProperty = t.formValue
        applyingValue = message.value
        try {
          t.formValue := message.value
        } finally {
          applyingProperty = null
          applyingValue = null
        }
      }
    }
    case None => warn("Unable to find tag by id: %s to fire change: %s".format(message.id, message.value))
  }

  private def receiveJavaScriptKeyEvent(message: SimpleKeyEvent) = page.html.byId[HTMLTag](message.id) match {
    case Some(tag) => {
      val evt = JavaScriptEvent.createKeyEvent(tag, message.event, message.altKey, message.char, message.ctrlKey, message.key, message.locale, message.location, message.metaKey, message.repeat, message.shiftKey)
      tag.fire(evt)
    }
    case None => warn("Unable to find tag by id: %s to fire key event: %s".format(message.id, message.event))
  }

  private def receiveSVGMouseEvent(m: SimpleSVGMouseEvent) = page.html.byId[SVGTag](m.id) match {
    case Some(tag) => {
      val evt = m.event match {
        case "click" => new SVGClickEvent(tag, m.altKey, MouseButton(m.button), m.clientX, m.clientY, m.ctrlKey, m.metaKey, m.screenX, m.screenY, m.shiftKey)
        case "mousedown" => new SVGMouseDownEvent(tag, m.altKey, MouseButton(m.button), m.clientX, m.clientY, m.ctrlKey, m.metaKey, m.screenX, m.screenY, m.shiftKey)
        case "mouseup" => new SVGMouseUpEvent(tag, m.altKey, MouseButton(m.button), m.clientX, m.clientY, m.ctrlKey, m.metaKey, m.screenX, m.screenY, m.shiftKey)
        case "mouseover" => new SVGMouseOverEvent(tag, m.altKey, MouseButton(m.button), m.clientX, m.clientY, m.ctrlKey, m.metaKey, m.screenX, m.screenY, m.shiftKey)
        case "mousemove" => new SVGMouseMoveEvent(tag, m.altKey, MouseButton(m.button), m.clientX, m.clientY, m.ctrlKey, m.metaKey, m.screenX, m.screenY, m.shiftKey)
        case "mouseout" => new SVGMouseOutEvent(tag, m.altKey, MouseButton(m.button), m.clientX, m.clientY, m.ctrlKey, m.metaKey, m.screenX, m.screenY, m.shiftKey)
      }
      tag.fire(evt)
    }
    case None => warn("Unable to find svg tag by id: %s to fire mouse event: %s".format(m.id, m.event))
  }

  private def receiveSVGMutationEvent(m: SimpleSVGMutationEvent) = page.html.byId[SVGTag](m.id) match {
    case Some(tag) => {
      val evt = m.event match {
        case "DOMSubtreeModified" => new SVGDOMSubtreeModifiedEvent(tag, m.attrChange, m.attrName, m.newValue, m.prevValue)
        case "DOMNodeInserted" => new SVGDOMNodeInsertedEvent(tag, m.attrChange, m.attrName, m.newValue, m.prevValue)
        case "DOMNodeRemoved" => new SVGDOMNodeRemovedEvent(tag, m.attrChange, m.attrName, m.newValue, m.prevValue)
        case "DOMNodeRemovedFromDocument" => new SVGDOMNodeRemovedFromDocumentEvent(tag, m.attrChange, m.attrName, m.newValue, m.prevValue)
        case "DOMNodeInsertedIntoDocument" => new SVGDOMNodeInsertedIntoDocumentEvent(tag, m.attrChange, m.attrName, m.newValue, m.prevValue)
        case "DOMAttrModified" => new SVGDOMAttrModifiedEvent(tag, m.attrChange, m.attrName, m.newValue, m.prevValue)
        case "DOMCharacterDataModified" => new SVGDOMCharacterDataModifiedEvent(tag, m.attrChange, m.attrName, m.newValue, m.prevValue)
      }
      tag.fire(evt)
    }
    case None => warn("Unable to find svg tag by id: %s to fire mutation event: %s".format(m.id, m.event))
  }

  private def receiveSVGUIEvent(m: SimpleSVGUIEvent) = page.html.byId[SVGTag](m.id) match {
    case Some(tag) => {
      val evt = m.event match {
        case "focusin" => new SVGFocusInEvent(tag, m.detail)
        case "focusout" => new SVGFocusOutEvent(tag, m.detail)
        case "activate" => new SVGActivateEvent(tag, m.detail)
      }
      tag.fire(evt)
    }
    case None => warn("Unable to find svg tag by id: %s to fire ui event: %s".format(m.id, m.event))
  }

  private def receiveSVGEvent(m: SimpleEvent) = page.html.byId[SVGTag](m.id) match {
    case Some(tag) => {
      val evt = m.event match {
        case "SVGLoad" => new SVGLoadEvent(tag)
        case "SVGUnload" => new SVGUnloadEvent(tag)
        case "SVGAbort" => new SVGAbortEvent(tag)
        case "SVGError" => new SVGErrorEvent(tag)
        case "SVGResize" => new SVGResizeEvent(tag)
        case "SVGScroll" => new SVGScrollEvent(tag)
        case "SVGZoom" => new SVGZoomEvent(tag)
        case "beginEvent" => new SVGBeginEvent(tag)
        case "endEvent" => new SVGEndEvent(tag)
        case "repeatEvent" => new SVGRepeatEvent(tag)
      }
      tag.fire(evt)
    }
    case None => warn("Unable to find svg tag by id: %s to fire ui event: %s".format(m.id, m.event))
  }

  def propertyChanged(t: HTMLTag, property: PropertyAttribute[_], oldValue: Any, newValue: Any) = {
//    info("propertyChanged: %s.%s from %s to %s".format(t.xmlLabel, property.name(), oldValue, newValue))
    if (t.root[Webpage].nonEmpty && property != t.style && !t.isInstanceOf[tag.Text]) {
      if (property == t.id && oldValue == null) {
        // Ignore initial id change as it is sent when added
      } else {
        Page().intercept.renderAttribute.fire(property) match {
          case Some(pa) => t match {
            case title: tag.Title if (property == title.content) => send(JavaScriptMessage("document.title = content;", property.attributeValue))
            case textual: Textual if (property == textual.content) => send(JavaScriptMessage("$('#%s').val(content);".format(t.id()), property.attributeValue))
            case _ if (property() == false) => send(JavaScriptMessage("$('#%s').removeAttr('%s');".format(t.id(), property.name())))
            case _ => send(JavaScriptMessage("$('#%s').attr('%s', content);".format(t.id(), property.name()), property.attributeValue))
          }
          case None => // Attribute shouldn't render so we ignore it
        }
      }
    }
  }

//  def styleChanged(ss: StyleSheet, property: PropertyAttribute[_]) = page.tagsByStyleSheet(ss).foreach {
//    case tag => send(JavaScriptMessage("$('#%s').css('%s', content);".format(tag.id(), property.name()), property.attributeValue))
//  }

  def styleChanged(ss: StyleSheet, style: Style[_], value: AnyRef) = page.tagsByStyleSheet(ss).foreach {
    case tag => send(JavaScriptMessage("$('#%s').css('%s', content);".format(tag.id(), style.cssName), style.asInstanceOf[Style[AnyRef]].persistence.toString(value, value.getClass)))
  }

  def childAdded(evt: ChildAddedEvent) = {
    val parent = evt.parent.asInstanceOf[HTMLTag with Container[HTMLTag]]
    evt.child match {
      case child: HTMLTag => {
        // Verifies the parent and child have an id
        parent.identity
        child.identity

        val index = parent.contents.indexOf(child)
        val instruction = if (index == parent.contents.length - 1) {    // Append to the end
          "$('#%s').append(content);".format(parent.id())
        } else if (index == 0) {                                   // Append before
        val after = parent.contents(1)
          "$('#%s').before(content);".format(after.id())
        } else {
          val before = parent.contents(index - 1)
          "$('#%s').after(content);".format(before.id())
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
    case tag: HTMLTag => send(JavaScriptMessage("$('#%s').remove();".format(tag.id())))
    case js: JavaScriptContent => // TODO: evaluate? - Previously nothing was needed here
  }

  def send(js: JavaScriptMessage): Unit = {
    val message = generate(js)
    send("eval", message)
  }

  override def send(event: String, message: String) {
//    info("Sending event: %s, message: %s".format(event, message))
    super.send(event, message)
  }
}

case class SimpleEvent(id: String, event: String)

case class SimpleChangeEvent(id: String, value: String)

case class SimpleKeyEvent(id: String, event: String, altKey: Boolean = false, char: Int = 0, ctrlKey: Boolean = false, key: Int = 0, locale: String = null, location: Long = 0, metaKey: Boolean = false, repeat: Boolean = false, shiftKey: Boolean = false)

case class SimpleSVGMouseEvent(id: String, event: String, altKey: Boolean, button: Int, clientX: Long, clientY: Long, ctrlKey: Boolean, metaKey: Boolean, screenX: Long, screenY: Long, shiftKey: Boolean)

case class SimpleSVGMutationEvent(id: String, event: String, attrChange: Int, attrName: String, newValue: String, prevValue: String)

case class SimpleSVGUIEvent(id: String, event: String, detail: Long)

case class JavaScriptMessage(instruction: String, content: String = null)