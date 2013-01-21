package org.hyperscala.svg

import attributes.{Transform, XMLSpace}
import event._
import org.hyperscala.{IdentifiableTag, PropertyAttribute}
import org.hyperscala.Message
import org.hyperscala.io.HTMLWriter

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

  def byTag[T <: SVGTag](implicit manifest: Manifest[T]) = hierarchy.findAll[T](t => true)(manifest)

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
}