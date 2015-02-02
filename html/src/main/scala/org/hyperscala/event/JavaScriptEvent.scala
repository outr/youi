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
  TypedSupport.register("afterprint", classOf[AfterPrintEvent])
  TypedSupport.register("beforeprint", classOf[BeforePrintEvent])
  TypedSupport.register("beforeonload", classOf[BeforeOnLoadEvent])
  TypedSupport.register("haschange", classOf[HasChangeEvent])
  TypedSupport.register("load", classOf[LoadEvent])
  TypedSupport.register("message", classOf[MessageEvent])
  TypedSupport.register("offline", classOf[OfflineEvent])
  TypedSupport.register("online", classOf[OnlineEvent])
  TypedSupport.register("pagehide", classOf[PageHideEvent])
  TypedSupport.register("pageshow", classOf[PageShowEvent])
  TypedSupport.register("popstate", classOf[PopStateEvent])
  TypedSupport.register("redo", classOf[RedoEvent])
  TypedSupport.register("resize", classOf[ResizeEvent])
  TypedSupport.register("storage", classOf[StorageEvent])
  TypedSupport.register("undo", classOf[UndoEvent])
  TypedSupport.register("unload", classOf[UnLoadEvent])
  TypedSupport.register("blur", classOf[BlurEvent])
  TypedSupport.register("change", classOf[ChangeEvent])
  TypedSupport.register("contextmenu", classOf[ContextMenuEvent])
  TypedSupport.register("focus", classOf[FocusEvent])
  TypedSupport.register("formchange", classOf[FormChangeEvent])
  TypedSupport.register("forminput", classOf[FormInputEvent])
  TypedSupport.register("input", classOf[InputEvent])
  TypedSupport.register("invalid", classOf[InvalidEvent])
  TypedSupport.register("reset", classOf[ResetEvent])
  TypedSupport.register("select", classOf[SelectEvent])
  TypedSupport.register("submit", classOf[SubmitEvent])
  TypedSupport.register("click", classOf[ClickEvent])
  TypedSupport.register("dblclick", classOf[DoubleClickEvent])
  TypedSupport.register("drag", classOf[DragEvent])
  TypedSupport.register("dragend", classOf[DragEndEvent])
  TypedSupport.register("dragenter", classOf[DragEnterEvent])
  TypedSupport.register("dragleave", classOf[DragLeaveEvent])
  TypedSupport.register("dragover", classOf[DragOverEvent])
  TypedSupport.register("dragstart", classOf[DragStartEvent])
  TypedSupport.register("drop", classOf[DropEvent])
  TypedSupport.register("mousedown", classOf[MouseDownEvent])
  TypedSupport.register("mousemove", classOf[MouseMoveEvent])
  TypedSupport.register("mouseout", classOf[MouseOutEvent])
  TypedSupport.register("mouseover", classOf[MouseOverEvent])
  TypedSupport.register("mouseup", classOf[MouseUpEvent])
  TypedSupport.register("mousewheel", classOf[MouseWheelEvent])
  TypedSupport.register("scroll", classOf[ScrollEvent])
  TypedSupport.register("abort", classOf[AbortEvent])
  TypedSupport.register("canplay", classOf[CanPlayEvent])
  TypedSupport.register("canplaythrough", classOf[CanPlayThroughEvent])
  TypedSupport.register("durationchange", classOf[DurationChangeEvent])
  TypedSupport.register("emptied", classOf[EmptiedEvent])
  TypedSupport.register("ended", classOf[EndedEvent])
  TypedSupport.register("error", classOf[ErrorEvent])
  TypedSupport.register("loadeddata", classOf[LoadedDataEvent])
  TypedSupport.register("loadedmetadata", classOf[LoadedMetaDataEvent])
  TypedSupport.register("loadstart", classOf[LoadStartEvent])
  TypedSupport.register("pause", classOf[PauseEvent])
  TypedSupport.register("play", classOf[PlayEvent])
  TypedSupport.register("playing", classOf[PlayingEvent])
  TypedSupport.register("progress", classOf[ProgressEvent])
  TypedSupport.register("ratechange", classOf[RateChangeEvent])
  TypedSupport.register("readystatechange", classOf[ReadyStateChangeEvent])
  TypedSupport.register("seeked", classOf[SeekedEvent])
  TypedSupport.register("seeking", classOf[SeekingEvent])
  TypedSupport.register("stalled", classOf[StalledEvent])
  TypedSupport.register("suspend", classOf[SuspendEvent])
  TypedSupport.register("timeupdate", classOf[TimeUpdateEvent])
  TypedSupport.register("volumechange", classOf[VolumeChangeEvent])
  TypedSupport.register("waiting", classOf[WaitingEvent])
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
case class ChangeEvent(tag: HTMLTag, target: Option[HTMLTag], parent: Option[HTMLTag] = None) extends JavaScriptEvent
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
