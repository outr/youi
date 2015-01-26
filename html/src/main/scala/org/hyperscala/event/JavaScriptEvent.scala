package org.hyperscala.event

/**
 * @author Matt Hicks <matt@outr.com>
 */

import org.hyperscala.event.processor.EventReceivedProcessor
import org.hyperscala.html.HTMLTag
import com.outr.net.Method

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScriptEvent extends TagClientEvent {
  def tag: HTMLTag
  def target: Option[HTMLTag]
}

object JavaScriptEvent {
  EventReceivedProcessor.register[AfterPrintEvent]("afterprint")
  EventReceivedProcessor.register[BeforePrintEvent]("beforeprint")
  EventReceivedProcessor.register[BeforeOnLoadEvent]("beforeonload")
  EventReceivedProcessor.register[HasChangeEvent]("haschange")
  EventReceivedProcessor.register[LoadEvent]("load")
  EventReceivedProcessor.register[MessageEvent]("message")
  EventReceivedProcessor.register[OfflineEvent]("offline")
  EventReceivedProcessor.register[OnlineEvent]("online")
  EventReceivedProcessor.register[PageHideEvent]("pagehide")
  EventReceivedProcessor.register[PageShowEvent]("pageshow")
  EventReceivedProcessor.register[PopStateEvent]("popstate")
  EventReceivedProcessor.register[RedoEvent]("redo")
  EventReceivedProcessor.register[ResizeEvent]("resize")
  EventReceivedProcessor.register[StorageEvent]("storage")
  EventReceivedProcessor.register[UndoEvent]("undo")
  EventReceivedProcessor.register[UnLoadEvent]("unload")
  EventReceivedProcessor.register[BlurEvent]("blur")
  EventReceivedProcessor.register[ChangeEvent]("change")
  EventReceivedProcessor.register[ContextMenuEvent]("contextmenu")
  EventReceivedProcessor.register[FocusEvent]("focus")
  EventReceivedProcessor.register[FormChangeEvent]("formchange")
  EventReceivedProcessor.register[FormInputEvent]("forminput")
  EventReceivedProcessor.register[InputEvent]("input")
  EventReceivedProcessor.register[InvalidEvent]("invalid")
  EventReceivedProcessor.register[ResetEvent]("reset")
  EventReceivedProcessor.register[SelectEvent]("select")
  EventReceivedProcessor.register[SubmitEvent]("submit")
  EventReceivedProcessor.register[ClickEvent]("click")
  EventReceivedProcessor.register[DoubleClickEvent]("dblclick")
  EventReceivedProcessor.register[DragEvent]("drag")
  EventReceivedProcessor.register[DragEndEvent]("dragend")
  EventReceivedProcessor.register[DragEnterEvent]("dragenter")
  EventReceivedProcessor.register[DragLeaveEvent]("dragleave")
  EventReceivedProcessor.register[DragOverEvent]("dragover")
  EventReceivedProcessor.register[DragStartEvent]("dragstart")
  EventReceivedProcessor.register[DropEvent]("drop")
  EventReceivedProcessor.register[MouseDownEvent]("mousedown")
  EventReceivedProcessor.register[MouseMoveEvent]("mousemove")
  EventReceivedProcessor.register[MouseOutEvent]("mouseout")
  EventReceivedProcessor.register[MouseOverEvent]("mouseover")
  EventReceivedProcessor.register[MouseUpEvent]("mouseup")
  EventReceivedProcessor.register[MouseWheelEvent]("mousewheel")
  EventReceivedProcessor.register[ScrollEvent]("scroll")
  EventReceivedProcessor.register[AbortEvent]("abort")
  EventReceivedProcessor.register[CanPlayEvent]("canplay")
  EventReceivedProcessor.register[CanPlayThroughEvent]("canplaythrough")
  EventReceivedProcessor.register[DurationChangeEvent]("durationchange")
  EventReceivedProcessor.register[EmptiedEvent]("emptied")
  EventReceivedProcessor.register[EndedEvent]("ended")
  EventReceivedProcessor.register[ErrorEvent]("error")
  EventReceivedProcessor.register[LoadedDataEvent]("loadeddata")
  EventReceivedProcessor.register[LoadedMetaDataEvent]("loadedmetadata")
  EventReceivedProcessor.register[LoadStartEvent]("loadstart")
  EventReceivedProcessor.register[PauseEvent]("pause")
  EventReceivedProcessor.register[PlayEvent]("play")
  EventReceivedProcessor.register[PlayingEvent]("playing")
  EventReceivedProcessor.register[ProgressEvent]("progress")
  EventReceivedProcessor.register[RateChangeEvent]("ratechange")
  EventReceivedProcessor.register[ReadyStateChangeEvent]("readystatechange")
  EventReceivedProcessor.register[SeekedEvent]("seeked")
  EventReceivedProcessor.register[SeekingEvent]("seeking")
  EventReceivedProcessor.register[StalledEvent]("stalled")
  EventReceivedProcessor.register[SuspendEvent]("suspend")
  EventReceivedProcessor.register[TimeUpdateEvent]("timeupdate")
  EventReceivedProcessor.register[VolumeChangeEvent]("volumechange")
  EventReceivedProcessor.register[WaitingEvent]("waiting")
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
