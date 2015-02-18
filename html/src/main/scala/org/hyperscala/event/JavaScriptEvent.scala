package org.hyperscala.event

/**
 * @author Matt Hicks <matt@outr.com>
 */

import org.hyperscala.html.HTMLTag
import com.outr.net.Method
import org.powerscala.json.TypedSupport

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScriptEvent extends BrowserEvent {
  def tag: HTMLTag
  def target: Option[HTMLTag]
}

object JavaScriptEvent {
  private var _eventNames = List.empty[String]

  register("afterprint", classOf[AfterPrintEvent])
  register("beforeprint", classOf[BeforePrintEvent])
  register("beforeonload", classOf[BeforeOnLoadEvent])
  register("haschange", classOf[HasChangeEvent])
  register("load", classOf[LoadEvent])
  register("message", classOf[MessageEvent])
  register("offline", classOf[OfflineEvent])
  register("online", classOf[OnlineEvent])
  register("pagehide", classOf[PageHideEvent])
  register("pageshow", classOf[PageShowEvent])
  register("popstate", classOf[PopStateEvent])
  register("redo", classOf[RedoEvent])
  register("resize", classOf[ResizeEvent])
  register("storage", classOf[StorageEvent])
  register("undo", classOf[UndoEvent])
  register("unload", classOf[UnLoadEvent])
  register("blur", classOf[BlurEvent])
  register("change", classOf[ChangeEvent])
  register("contextmenu", classOf[ContextMenuEvent])
  register("focus", classOf[FocusEvent])
  register("formchange", classOf[FormChangeEvent])
  register("forminput", classOf[FormInputEvent])
  register("input", classOf[InputEvent])
  register("invalid", classOf[InvalidEvent])
  register("reset", classOf[ResetEvent])
  register("select", classOf[SelectEvent])
  register("submit", classOf[SubmitEvent])
  register("click", classOf[ClickEvent])
  register("dblclick", classOf[DoubleClickEvent])
  register("drag", classOf[DragEvent])
  register("dragend", classOf[DragEndEvent])
  register("dragenter", classOf[DragEnterEvent])
  register("dragleave", classOf[DragLeaveEvent])
  register("dragover", classOf[DragOverEvent])
  register("dragstart", classOf[DragStartEvent])
  register("drop", classOf[DropEvent])
  register("mousedown", classOf[MouseDownEvent])
  register("mousemove", classOf[MouseMoveEvent])
  register("mouseout", classOf[MouseOutEvent])
  register("mouseover", classOf[MouseOverEvent])
  register("mouseup", classOf[MouseUpEvent])
  register("mousewheel", classOf[MouseWheelEvent])
  register("scroll", classOf[ScrollEvent])
  register("abort", classOf[AbortEvent])
  register("canplay", classOf[CanPlayEvent])
  register("canplaythrough", classOf[CanPlayThroughEvent])
  register("durationchange", classOf[DurationChangeEvent])
  register("emptied", classOf[EmptiedEvent])
  register("ended", classOf[EndedEvent])
  register("error", classOf[ErrorEvent])
  register("loadeddata", classOf[LoadedDataEvent])
  register("loadedmetadata", classOf[LoadedMetaDataEvent])
  register("loadstart", classOf[LoadStartEvent])
  register("pause", classOf[PauseEvent])
  register("play", classOf[PlayEvent])
  register("playing", classOf[PlayingEvent])
  register("progress", classOf[ProgressEvent])
  register("ratechange", classOf[RateChangeEvent])
  register("readystatechange", classOf[ReadyStateChangeEvent])
  register("seeked", classOf[SeekedEvent])
  register("seeking", classOf[SeekingEvent])
  register("stalled", classOf[StalledEvent])
  register("suspend", classOf[SuspendEvent])
  register("timeupdate", classOf[TimeUpdateEvent])
  register("volumechange", classOf[VolumeChangeEvent])
  register("waiting", classOf[WaitingEvent])

  val eventNames = _eventNames

  private def register(name: String, clazz: Class[_]) = synchronized {
    TypedSupport.register(name, clazz)
    _eventNames = name :: _eventNames
  }
}

trait KeyboardEvent {
  def char: Option[Char]
  def keyCode: Int
  def keyOption: Option[Key]
  def altKey: Boolean
  def ctrlKey: Boolean
  def metaKey: Boolean
  def shiftKey: Boolean
  def repeat: Boolean
  def location: Long
  def locale: String

  def key = keyOption.orNull

  override def toString = {
    "%s(char = %s, keyCode = %s, key = %s, altKey = %s, ctrlKey = %s, metaKey = %s, shiftKey = %s, repeat = %s, location = %s, locale = %s)"
      .format(getClass.getSimpleName, char, keyCode, key, altKey, ctrlKey, metaKey, shiftKey, repeat, location, locale)
  }
}

case class AfterPrintEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class BeforePrintEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class BeforeOnLoadEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class HasChangeEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class LoadEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class MessageEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class OfflineEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class OnlineEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class PageHideEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class PageShowEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class PopStateEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class RedoEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class ResizeEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class StorageEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class UndoEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class UnLoadEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class BlurEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class ChangeEvent(tag: HTMLTag, value: String, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class ContextMenuEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class FocusEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class FormChangeEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class FormInputEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class InputEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class InvalidEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class ResetEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class SelectEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class SubmitEvent(tag: HTMLTag, target: Option[HTMLTag], method: Method = Method.Post, parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class KeyDownEvent(tag: HTMLTag,
                        target: Option[HTMLTag],
                        char: Option[Char],
                        keyCode: Int,
                        keyOption: Option[Key],
                        altKey: Boolean,
                        ctrlKey: Boolean,
                        metaKey: Boolean,
                        shiftKey: Boolean,
                        repeat: Boolean,
                        location: Long,
                        locale: String,
                        parent: Option[HTMLTag] = None) extends JavaScriptEvent with KeyboardEvent
case class KeyPressEvent(tag: HTMLTag,
                         target: Option[HTMLTag],
                         char: Option[Char],
                         keyCode: Int,
                         keyOption: Option[Key],
                         altKey: Boolean,
                         ctrlKey: Boolean,
                         metaKey: Boolean,
                         shiftKey: Boolean,
                         repeat: Boolean,
                         location: Long,
                         locale: String,
                         parent: Option[HTMLTag] = None) extends JavaScriptEvent with KeyboardEvent
case class KeyUpEvent(tag: HTMLTag,
                 target: Option[HTMLTag],
                 char: Option[Char],
                 keyCode: Int,
                 keyOption: Option[Key],
                 altKey: Boolean,
                 ctrlKey: Boolean,
                 metaKey: Boolean,
                 shiftKey: Boolean,
                 repeat: Boolean,
                 location: Long,
                 locale: String,
                 parent: Option[HTMLTag] = None) extends JavaScriptEvent with KeyboardEvent
case class ClickEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class DoubleClickEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class DragEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class DragEndEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class DragEnterEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class DragLeaveEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class DragOverEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class DragStartEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class DropEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class MouseDownEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class MouseMoveEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class MouseOutEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class MouseOverEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class MouseUpEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class MouseWheelEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class ScrollEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class AbortEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class CanPlayEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class CanPlayThroughEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class DurationChangeEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class EmptiedEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class EndedEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class ErrorEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class LoadedDataEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class LoadedMetaDataEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class LoadStartEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class PauseEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class PlayEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class PlayingEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class ProgressEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class RateChangeEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class ReadyStateChangeEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class SeekedEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class SeekingEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class StalledEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class SuspendEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class TimeUpdateEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class VolumeChangeEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class WaitingEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
case class StyleChangeEvent(tag: HTMLTag,
                            target: Option[HTMLTag],
                            propertyName: String,
                            propertyValue: String,
                            parent: Option[HTMLTag] = None) extends JavaScriptEvent {
//  def property[T] = tag.style().properties[T](propertyName).asInstanceOf[StyleSheetAttribute[T]]
}
