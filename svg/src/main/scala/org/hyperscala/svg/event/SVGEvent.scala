package org.hyperscala.svg.event

import org.hyperscala.svg.SVGTag
import org.powerscala.event.Event
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.Page

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class SVGEvent(val tag: SVGTag) extends Event

object SVGEvent {
  def apply(confirmation: String = null,
            preventDefault: Boolean = true) = {
    Page().require("realtime")

    val b = new StringBuilder
    if (confirmation != null) {
      b.append("if (confirm('%s')) { ".format(confirmation))
    }
    b.append("svgEventHandler(evt); ")
    if (confirmation != null) {
      b.append("} ")
    }
    if (preventDefault) {
      b.append("return false;")
    }
    JavaScriptString(b.toString())
  }
}

trait SVGUIEvent {
  this: SVGEvent =>

  def detail: Long
}

trait SVGMouseEvent {
  this: SVGEvent =>

  def altKey: Boolean
  def button: MouseButton
  def clientX: Long
  def clientY: Long
  def ctrlKey: Boolean
  def metaKey: Boolean
  def screenX: Long
  def screenY: Long
  def shiftKey: Boolean
}

trait SVGMutationEvent {
  this: SVGEvent =>

  def attrChange: Int
  def attrName: String
  def newValue: String
  def prevValue: String
}

class SVGFocusInEvent(tag: SVGTag, val detail: Long) extends SVGEvent(tag) with SVGUIEvent
class SVGFocusOutEvent(tag: SVGTag, val detail: Long) extends SVGEvent(tag) with SVGUIEvent
class SVGActivateEvent(tag: SVGTag, val detail: Long) extends SVGEvent(tag) with SVGUIEvent
class SVGClickEvent(tag: SVGTag,
                        val altKey: Boolean,
                        val button: MouseButton,
                        val clientX: Long,
                        val clientY: Long,
                        val ctrlKey: Boolean,
                        val metaKey: Boolean,
                        val screenX: Long,
                        val screenY: Long,
                        val shiftKey: Boolean) extends SVGEvent(tag) with SVGMouseEvent
class SVGMouseDownEvent(tag: SVGTag,
                        val altKey: Boolean,
                        val button: MouseButton,
                        val clientX: Long,
                        val clientY: Long,
                        val ctrlKey: Boolean,
                        val metaKey: Boolean,
                        val screenX: Long,
                        val screenY: Long,
                        val shiftKey: Boolean) extends SVGEvent(tag) with SVGMouseEvent
class SVGMouseUpEvent(tag: SVGTag,
                        val altKey: Boolean,
                        val button: MouseButton,
                        val clientX: Long,
                        val clientY: Long,
                        val ctrlKey: Boolean,
                        val metaKey: Boolean,
                        val screenX: Long,
                        val screenY: Long,
                        val shiftKey: Boolean) extends SVGEvent(tag) with SVGMouseEvent
class SVGMouseOverEvent(tag: SVGTag,
                        val altKey: Boolean,
                        val button: MouseButton,
                        val clientX: Long,
                        val clientY: Long,
                        val ctrlKey: Boolean,
                        val metaKey: Boolean,
                        val screenX: Long,
                        val screenY: Long,
                        val shiftKey: Boolean) extends SVGEvent(tag) with SVGMouseEvent
class SVGMouseMoveEvent(tag: SVGTag,
                        val altKey: Boolean,
                        val button: MouseButton,
                        val clientX: Long,
                        val clientY: Long,
                        val ctrlKey: Boolean,
                        val metaKey: Boolean,
                        val screenX: Long,
                        val screenY: Long,
                        val shiftKey: Boolean) extends SVGEvent(tag) with SVGMouseEvent
class SVGMouseOutEvent(tag: SVGTag,
                        val altKey: Boolean,
                        val button: MouseButton,
                        val clientX: Long,
                        val clientY: Long,
                        val ctrlKey: Boolean,
                        val metaKey: Boolean,
                        val screenX: Long,
                        val screenY: Long,
                        val shiftKey: Boolean) extends SVGEvent(tag) with SVGMouseEvent
class SVGDOMSubtreeModifiedEvent(tag: SVGTag,
                                 val attrChange: Int,
                                 val attrName: String,
                                 val newValue: String,
                                 val prevValue: String) extends SVGEvent(tag) with SVGMutationEvent
class SVGDOMNodeInsertedEvent(tag: SVGTag,
                                 val attrChange: Int,
                                 val attrName: String,
                                 val newValue: String,
                                 val prevValue: String) extends SVGEvent(tag) with SVGMutationEvent
class SVGDOMNodeRemovedEvent(tag: SVGTag,
                                 val attrChange: Int,
                                 val attrName: String,
                                 val newValue: String,
                                 val prevValue: String) extends SVGEvent(tag) with SVGMutationEvent
class SVGDOMNodeRemovedFromDocumentEvent(tag: SVGTag,
                                 val attrChange: Int,
                                 val attrName: String,
                                 val newValue: String,
                                 val prevValue: String) extends SVGEvent(tag) with SVGMutationEvent
class SVGDOMNodeInsertedIntoDocumentEvent(tag: SVGTag,
                                 val attrChange: Int,
                                 val attrName: String,
                                 val newValue: String,
                                 val prevValue: String) extends SVGEvent(tag) with SVGMutationEvent
class SVGDOMAttrModifiedEvent(tag: SVGTag,
                                 val attrChange: Int,
                                 val attrName: String,
                                 val newValue: String,
                                 val prevValue: String) extends SVGEvent(tag) with SVGMutationEvent
class SVGDOMCharacterDataModifiedEvent(tag: SVGTag,
                                 val attrChange: Int,
                                 val attrName: String,
                                 val newValue: String,
                                 val prevValue: String) extends SVGEvent(tag) with SVGMutationEvent
class SVGLoadEvent(tag: SVGTag) extends SVGEvent(tag)
class SVGUnloadEvent(tag: SVGTag) extends SVGEvent(tag)
class SVGAbortEvent(tag: SVGTag) extends SVGEvent(tag)
class SVGErrorEvent(tag: SVGTag) extends SVGEvent(tag)
class SVGResizeEvent(tag: SVGTag) extends SVGEvent(tag)
class SVGScrollEvent(tag: SVGTag) extends SVGEvent(tag)
class SVGZoomEvent(tag: SVGTag) extends SVGEvent(tag)
class SVGBeginEvent(tag: SVGTag) extends SVGEvent(tag)
class SVGEndEvent(tag: SVGTag) extends SVGEvent(tag)
class SVGRepeatEvent(tag: SVGTag) extends SVGEvent(tag)