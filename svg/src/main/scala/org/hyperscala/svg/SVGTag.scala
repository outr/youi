package org.hyperscala.svg

import attributes.{Transform, XMLSpace}
import event._
import org.hyperscala.{IdentifiableTag, PropertyAttribute, Message}
import org.hyperscala.io.HTMLWriter
import org.hyperscala.svg.event.processor._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait SVGTag extends IdentifiableTag {
  val xmlBase = PropertyAttribute[String]("xml:base", null)
  val xmlLang = PropertyAttribute[String]("xml:lang", null)
  val xmlSpace = PropertyAttribute[XMLSpace]("xml:space", null)
  val clazz = PropertyAttribute[List[String]]("class", Nil)
  val style = PropertyAttribute[String]("style", null)
  val externalResourcesRequired = PropertyAttribute[Boolean]("externalResourcesRequired", false)
  val transform = PropertyAttribute[List[Transform]]("transform", Nil)

  val focusInEvent = new SVGFocusInEventProcessor()
  val focusOutEvent = new SVGFocusOutEventProcessor()
  val activateEvent = new SVGActivateEventProcessor()
  val clickEvent = new SVGClickEventProcessor()
  val mouseDownEvent = new SVGMouseDownEventProcessor()
  val mouseUpEvent = new SVGMouseUpEventProcessor()
  val mouseOverEvent = new SVGMouseOverEventProcessor()
  val mouseMoveEvent = new SVGMouseMoveEventProcessor()
  val mouseOutEvent = new SVGMouseOutEventProcessor()
  val dOMSubtreeModifiedEvent = new SVGDOMSubtreeModifiedEventProcessor()
  val dOMNodeInsertedEvent = new SVGDOMNodeInsertedEventProcessor()
  val dOMNodeRemovedEvent = new SVGDOMNodeRemovedEventProcessor()
  val dOMNodeRemovedFromDocumentEvent = new SVGDOMNodeRemovedFromDocumentEventProcessor()
  val dOMNodeInsertedIntoDocumentEvent = new SVGDOMNodeInsertedIntoDocumentEventProcessor()
  val dOMAttrModifiedEvent = new SVGDOMAttrModifiedEventProcessor()
  val dOMCharacterDataModifiedEvent = new SVGDOMCharacterDataModifiedEventProcessor()
  val loadEvent = new SVGLoadEventProcessor()
  val unloadEvent = new SVGUnloadEventProcessor()
  val abortEvent = new SVGAbortEventProcessor()
  val errorEvent = new SVGErrorEventProcessor()
  val resizeEvent = new SVGResizeEventProcessor()
  val scrollEvent = new SVGScrollEventProcessor()
  val zoomEvent = new SVGZoomEventProcessor()
  val beginEvent = new SVGBeginEventProcessor()
  val endEvent = new SVGEndEventProcessor()
  val repeatEvent = new SVGRepeatEventProcessor()

  def outputString = {
    val b = new StringBuilder
    val writer: String => Unit = (s: String) => b.append(s)
    val htmlWriter = HTMLWriter(writer)
    write(htmlWriter)
    b.toString()
  }

  def rendered() = {}

  override def receive(event: String, message: Message) = event match {
    case "svgMouseEvent" => {
      val eventType = message[String]("event")
      val altKey = message[Boolean]("altKey")
      val button = MouseButton(message[Int]("button"))
      val clientX = message[Long]("clientX")
      val clientY = message[Long]("clientY")
      val ctrlKey = message[Boolean]("ctrlKey")
      val metaKey = message[Boolean]("metaKey")
      val screenX = message[Long]("screenX")
      val screenY = message[Long]("screenY")
      val shiftKey = message[Boolean]("shiftKey")
      val evt = eventType match {
        case "click" => new SVGClickEvent(this, altKey, button, clientX, clientY, ctrlKey, metaKey, screenX, screenY, shiftKey)
        case "mousedown" => new SVGMouseDownEvent(this, altKey, button, clientX, clientY, ctrlKey, metaKey, screenX, screenY, shiftKey)
        case "mouseup" => new SVGMouseUpEvent(this, altKey, button, clientX, clientY, ctrlKey, metaKey, screenX, screenY, shiftKey)
        case "mouseover" => new SVGMouseOverEvent(this, altKey, button, clientX, clientY, ctrlKey, metaKey, screenX, screenY, shiftKey)
        case "mousemove" => new SVGMouseMoveEvent(this, altKey, button, clientX, clientY, ctrlKey, metaKey, screenX, screenY, shiftKey)
        case "mouseout" => new SVGMouseOutEvent(this, altKey, button, clientX, clientY, ctrlKey, metaKey, screenX, screenY, shiftKey)
      }
      fire(evt)
    }
    case "svgMutationEvent" => {
      val eventType = message[String]("event")
      val attrChange = message[Int]("attrChange")
      val attrName = message[String]("attrName")
      val newValue = message[String]("newValue")
      val prevValue = message[String]("prevValue")
      val evt = eventType match {
        case "DOMSubtreeModified" => new SVGDOMSubtreeModifiedEvent(this, attrChange, attrName, newValue, prevValue)
        case "DOMNodeInserted" => new SVGDOMNodeInsertedEvent(this, attrChange, attrName, newValue, prevValue)
        case "DOMNodeRemoved" => new SVGDOMNodeRemovedEvent(this, attrChange, attrName, newValue, prevValue)
        case "DOMNodeRemovedFromDocument" => new SVGDOMNodeRemovedFromDocumentEvent(this, attrChange, attrName, newValue, prevValue)
        case "DOMNodeInsertedIntoDocument" => new SVGDOMNodeInsertedIntoDocumentEvent(this, attrChange, attrName, newValue, prevValue)
        case "DOMAttrModified" => new SVGDOMAttrModifiedEvent(this, attrChange, attrName, newValue, prevValue)
        case "DOMCharacterDataModified" => new SVGDOMCharacterDataModifiedEvent(this, attrChange, attrName, newValue, prevValue)
      }
      fire(evt)
    }
    case "svgUIEvent" => {
      val eventType = message[String]("event")
      val detail = message[Long]("detail")
      val evt = eventType match {
        case "focusin" => new SVGFocusInEvent(this, detail)
        case "focusout" => new SVGFocusOutEvent(this, detail)
        case "activate" => new SVGActivateEvent(this, detail)
      }
      fire(evt)
    }
    case "svgEvent" => {
      val eventType = message[String]("event")
      val evt = eventType match {
        case "SVGLoad" => new SVGLoadEvent(this)
        case "SVGUnload" => new SVGUnloadEvent(this)
        case "SVGAbort" => new SVGAbortEvent(this)
        case "SVGError" => new SVGErrorEvent(this)
        case "SVGResize" => new SVGResizeEvent(this)
        case "SVGScroll" => new SVGScrollEvent(this)
        case "SVGZoom" => new SVGZoomEvent(this)
        case "beginEvent" => new SVGBeginEvent(this)
        case "endEvent" => new SVGEndEvent(this)
        case "repeatEvent" => new SVGRepeatEvent(this)
      }
      fire(evt)
    }
    case _ => super.receive(event, message)
  }
  
  protected def fire(event: SVGEvent) = event match {
    case evt: SVGFocusInEvent => focusInEvent.fire(evt)
    case evt: SVGFocusOutEvent => focusOutEvent.fire(evt)
    case evt: SVGActivateEvent => activateEvent.fire(evt)
    case evt: SVGClickEvent => clickEvent.fire(evt)
    case evt: SVGMouseDownEvent => mouseDownEvent.fire(evt)
    case evt: SVGMouseUpEvent => mouseUpEvent.fire(evt)
    case evt: SVGMouseOverEvent => mouseOverEvent.fire(evt)
    case evt: SVGMouseMoveEvent => mouseMoveEvent.fire(evt)
    case evt: SVGMouseOutEvent => mouseOutEvent.fire(evt)
    case evt: SVGDOMSubtreeModifiedEvent => dOMSubtreeModifiedEvent.fire(evt)
    case evt: SVGDOMNodeInsertedEvent => dOMNodeInsertedEvent.fire(evt)
    case evt: SVGDOMNodeRemovedEvent => dOMNodeRemovedEvent.fire(evt)
    case evt: SVGDOMNodeRemovedFromDocumentEvent => dOMNodeRemovedFromDocumentEvent.fire(evt)
    case evt: SVGDOMNodeInsertedIntoDocumentEvent => dOMNodeInsertedIntoDocumentEvent.fire(evt)
    case evt: SVGDOMAttrModifiedEvent => dOMAttrModifiedEvent.fire(evt)
    case evt: SVGDOMCharacterDataModifiedEvent => dOMCharacterDataModifiedEvent.fire(evt)
    case evt: SVGLoadEvent => loadEvent.fire(evt)
    case evt: SVGUnloadEvent => unloadEvent.fire(evt)
    case evt: SVGAbortEvent => abortEvent.fire(evt)
    case evt: SVGErrorEvent => errorEvent.fire(evt)
    case evt: SVGResizeEvent => resizeEvent.fire(evt)
    case evt: SVGScrollEvent => scrollEvent.fire(evt)
    case evt: SVGZoomEvent => zoomEvent.fire(evt)
    case evt: SVGBeginEvent => beginEvent.fire(evt)
    case evt: SVGEndEvent => endEvent.fire(evt)
    case evt: SVGRepeatEvent => repeatEvent.fire(evt)
  }
}