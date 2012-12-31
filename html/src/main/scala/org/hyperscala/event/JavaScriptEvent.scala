package org.hyperscala.event

/**
 * @author Matt Hicks <matt@outr.com>
 */
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.html.HTMLTag
import org.powerscala.event.Event
import org.hyperscala.Page

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class JavaScriptEvent(val tag: HTMLTag) extends Event

object JavaScriptEvent {
  def apply(confirmation: String = null,
            preventDefault: Boolean = true,
            fireChange: Boolean = false,
            onlyLast: Boolean = true) = {
    Page().require("realtime")

    val b = new StringBuilder
    if (confirmation != null) {
      b.append("if (confirm('%s')) { ".format(confirmation))
    }
    b.append("jsEventHandler(event, (typeof data === 'undefined') ? null : data, %s, %s); ".format(fireChange, onlyLast))
    if (confirmation != null) {
      b.append("} ")
    }
    if (preventDefault) {
      b.append("return false;")
    }
    JavaScriptString(b.toString())
  }

  def create(tag: HTMLTag, eventType: String) = {
    eventType match {
      case "afterprint" => new AfterPrintEvent(tag)
      case "beforeprint" => new BeforePrintEvent(tag)
      case "beforeonload" => new BeforeOnLoadEvent(tag)
      case "haschange" => new HasChangeEvent(tag)
      case "load" => new LoadEvent(tag)
      case "message" => new MessageEvent(tag)
      case "offline" => new OfflineEvent(tag)
      case "online" => new OnlineEvent(tag)
      case "pagehide" => new PageHideEvent(tag)
      case "pageshow" => new PageShowEvent(tag)
      case "popstate" => new PopStateEvent(tag)
      case "redo" => new RedoEvent(tag)
      case "resize" => new ResizeEvent(tag)
      case "storage" => new StorageEvent(tag)
      case "undo" => new UndoEvent(tag)
      case "unload" => new UnLoadEvent(tag)
      case "blur" => new BlurEvent(tag)
      case "change" => new ChangeEvent(tag)
      case "contextmenu" => new ContextMenuEvent(tag)
      case "focus" => new FocusEvent(tag)
      case "formchange" => new FormChangeEvent(tag)
      case "forminput" => new FormInputEvent(tag)
      case "input" => new InputEvent(tag)
      case "invalid" => new InvalidEvent(tag)
      case "reset" => new ResetEvent(tag)
      case "select" => new SelectEvent(tag)
      case "submit" => new SubmitEvent(tag)
      case "click" => new ClickEvent(tag)
      case "dblclick" => new DoubleClickEvent(tag)
      case "drag" => new DragEvent(tag)
      case "dragend" => new DragEndEvent(tag)
      case "dragenter" => new DragEnterEvent(tag)
      case "dragleave" => new DragLeaveEvent(tag)
      case "dragover" => new DragOverEvent(tag)
      case "dragstart" => new DragStartEvent(tag)
      case "drop" => new DropEvent(tag)
      case "mousedown" => new MouseDownEvent(tag)
      case "mousemove" => new MouseMoveEvent(tag)
      case "mouseout" => new MouseOutEvent(tag)
      case "mouseover" => new MouseOverEvent(tag)
      case "mouseup" => new MouseUpEvent(tag)
      case "mousewheel" => new MouseWheelEvent(tag)
      case "scroll" => new ScrollEvent(tag)
      case "abort" => new AbortEvent(tag)
      case "canplay" => new CanPlayEvent(tag)
      case "canplaythrough" => new CanPlayThroughEvent(tag)
      case "durationchange" => new DurationChangeEvent(tag)
      case "emptied" => new EmptiedEvent(tag)
      case "ended" => new EndedEvent(tag)
      case "error" => new ErrorEvent(tag)
      case "loadeddata" => new LoadedDataEvent(tag)
      case "loadedmetadata" => new LoadedMetaDataEvent(tag)
      case "loadstart" => new LoadStartEvent(tag)
      case "pause" => new PauseEvent(tag)
      case "play" => new PlayEvent(tag)
      case "playing" => new PlayingEvent(tag)
      case "progress" => new ProgressEvent(tag)
      case "ratechange" => new RateChangeEvent(tag)
      case "readystatechange" => new ReadyStateChangeEvent(tag)
      case "seeked" => new SeekedEvent(tag)
      case "seeking" => new SeekingEvent(tag)
      case "stalled" => new StalledEvent(tag)
      case "suspend" => new SuspendEvent(tag)
      case "timeupdate" => new TimeUpdateEvent(tag)
      case "volumechange" => new VolumeChangeEvent(tag)
      case "waiting" => new WaitingEvent(tag)
    }
  }

  def createKeyEvent(tag: HTMLTag, eventType: String, altKey: Boolean, char: Int, ctrlKey: Boolean, key: Int, locale: String, location: Long, metaKey: Boolean, repeat: Boolean, shiftKey: Boolean) = {
    val c = char match {
      case 0 => None
      case _ => Some(char.toChar)
    }
    eventType match {
      case "keydown" => new KeyDownEvent(tag = tag, char = c, keyCode = key, keyOption = Key.byCode(key), altKey = altKey, ctrlKey = ctrlKey, metaKey = metaKey, shiftKey = shiftKey, repeat = repeat, location = location, locale = locale)
      case "keyup" => new KeyUpEvent(tag = tag, char = c, keyCode = key, keyOption = Key.byCode(key), altKey = altKey, ctrlKey = ctrlKey, metaKey = metaKey, shiftKey = shiftKey, repeat = repeat, location = location, locale = locale)
      case "keypress" => new KeyPressEvent(tag = tag, char = c, keyCode = key, keyOption = Key.byCode(key), altKey = altKey, ctrlKey = ctrlKey, metaKey = metaKey, shiftKey = shiftKey, repeat = repeat, location = location, locale = locale)
    }
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

  def key = keyOption.getOrElse(null)

  override def toString = {
    "%s(char = %s, keyCode = %s, key = %s, altKey = %s, ctrlKey = %s, metaKey = %s, shiftKey = %s, repeat = %s, location = %s, locale = %s)"
      .format(getClass.getSimpleName, char, keyCode, key, altKey, ctrlKey, metaKey, shiftKey, repeat, location, locale)
  }
}

class AfterPrintEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class BeforePrintEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class BeforeOnLoadEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class HasChangeEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class LoadEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class MessageEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class OfflineEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class OnlineEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class PageHideEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class PageShowEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class PopStateEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class RedoEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class ResizeEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class StorageEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class UndoEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class UnLoadEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class BlurEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class ChangeEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class ContextMenuEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class FocusEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class FormChangeEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class FormInputEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class InputEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class InvalidEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class ResetEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class SelectEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class SubmitEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class KeyDownEvent(tag: HTMLTag,
                   val char: Option[Char],
                   val keyCode: Int,
                   val keyOption: Option[Key],
                   val altKey: Boolean,
                   val ctrlKey: Boolean,
                   val metaKey: Boolean,
                   val shiftKey: Boolean,
                   val repeat: Boolean,
                   val location: Long,
                   val locale: String) extends JavaScriptEvent(tag) with KeyboardEvent
class KeyPressEvent(tag: HTMLTag,
                    val char: Option[Char],
                    val keyCode: Int,
                    val keyOption: Option[Key],
                    val altKey: Boolean,
                    val ctrlKey: Boolean,
                    val metaKey: Boolean,
                    val shiftKey: Boolean,
                    val repeat: Boolean,
                    val location: Long,
                    val locale: String) extends JavaScriptEvent(tag) with KeyboardEvent
class KeyUpEvent(tag: HTMLTag,
                 val char: Option[Char],
                 val keyCode: Int,
                 val keyOption: Option[Key],
                 val altKey: Boolean,
                 val ctrlKey: Boolean,
                 val metaKey: Boolean,
                 val shiftKey: Boolean,
                 val repeat: Boolean,
                 val location: Long,
                 val locale: String) extends JavaScriptEvent(tag) with KeyboardEvent
class ClickEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class DoubleClickEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class DragEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class DragEndEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class DragEnterEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class DragLeaveEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class DragOverEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class DragStartEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class DropEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class MouseDownEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class MouseMoveEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class MouseOutEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class MouseOverEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class MouseUpEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class MouseWheelEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class ScrollEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class AbortEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class CanPlayEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class CanPlayThroughEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class DurationChangeEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class EmptiedEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class EndedEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class ErrorEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class LoadedDataEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class LoadedMetaDataEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class LoadStartEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class PauseEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class PlayEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class PlayingEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class ProgressEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class RateChangeEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class ReadyStateChangeEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class SeekedEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class SeekingEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class StalledEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class SuspendEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class TimeUpdateEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class VolumeChangeEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class WaitingEvent(tag: HTMLTag) extends JavaScriptEvent(tag)
class StyleChangeEvent(tag: HTMLTag,
                       val propertyName: String,
                       val propertyValue: String) extends JavaScriptEvent(tag) {
//  def property[T] = tag.style().properties[T](propertyName).asInstanceOf[StyleSheetAttribute[T]]
}
