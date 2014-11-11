package org.hyperscala.svg.event.processor

import org.hyperscala.Markup
import org.hyperscala.javascript.{EventProperty, JavaScriptContent}
import org.hyperscala.svg.event._
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.property.{ReadProperty, WriteProperty}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SVGEventProcessor[T <: SVGEvent](name: String, markup: Markup)(implicit eventManifest: Manifest[T])
      extends StandardHierarchyEventProcessor[T](name)(markup, eventManifest)
      with ReadProperty[JavaScriptContent]
      with WriteProperty[JavaScriptContent] {
  val js = new EventProperty(name)(markup)

  def apply() = js()

  def apply(v1: JavaScriptContent) = js(v1)
}

class SVGFocusInEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGFocusInEvent]("onfocusin", markup)
class SVGFocusOutEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGFocusOutEvent]("onfocusout", markup)
class SVGActivateEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGActivateEvent]("onactivate", markup)
class SVGClickEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGClickEvent]("onclick", markup)
class SVGMouseDownEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGMouseDownEvent]("onmousedown", markup)
class SVGMouseUpEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGMouseUpEvent]("onmouseup", markup)
class SVGMouseOverEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGMouseOverEvent]("onmouseover", markup)
class SVGMouseMoveEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGMouseMoveEvent]("onmousemove", markup)
class SVGMouseOutEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGMouseOutEvent]("onmouseout", markup)
class SVGDOMSubtreeModifiedEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGDOMSubtreeModifiedEvent]("ondomsubtreemodified", markup)
class SVGDOMNodeInsertedEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGDOMNodeInsertedEvent]("ondomnodeinserted", markup)
class SVGDOMNodeRemovedEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGDOMNodeRemovedEvent]("ondomnoderemoved", markup)
class SVGDOMNodeRemovedFromDocumentEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGDOMNodeRemovedFromDocumentEvent]("ondomnoderemovedfromdocument", markup)
class SVGDOMNodeInsertedIntoDocumentEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGDOMNodeInsertedIntoDocumentEvent]("ondomnodeinsertedintodocument", markup)
class SVGDOMAttrModifiedEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGDOMAttrModifiedEvent]("ondomattrmodified", markup)
class SVGDOMCharacterDataModifiedEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGDOMCharacterDataModifiedEvent]("ondomcharacterdatamodified", markup)
class SVGLoadEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGLoadEvent]("onload", markup)
class SVGUnloadEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGUnloadEvent]("onunload", markup)
class SVGAbortEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGAbortEvent]("onabort", markup)
class SVGErrorEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGErrorEvent]("onerror", markup)
class SVGResizeEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGResizeEvent]("onresize", markup)
class SVGScrollEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGScrollEvent]("onscroll", markup)
class SVGZoomEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGZoomEvent]("onzoom", markup)
class SVGBeginEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGBeginEvent]("onbegin", markup)
class SVGEndEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGEndEvent]("onend", markup)
class SVGRepeatEventProcessor(implicit markup: Markup) extends SVGEventProcessor[SVGRepeatEvent]("onrepeat", markup)
