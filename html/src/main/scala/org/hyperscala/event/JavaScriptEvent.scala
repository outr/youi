package org.hyperscala.event

/**
 * @author Matt Hicks <matt@outr.com>
 */
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.attributes.Method

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class JavaScriptEvent(val tag: HTMLTag)

object JavaScriptEvent {
  private val map = Map[String, (HTMLTag) => JavaScriptEvent](
    "afterprint" -> ((t: HTMLTag) => new AfterPrintEvent(t)),
    "beforeprint" -> ((t: HTMLTag) => new BeforePrintEvent(t)),
    "beforeonload" -> ((t: HTMLTag) => new BeforeOnLoadEvent(t)),
    "haschange" -> ((t: HTMLTag) => new HasChangeEvent(t)),
    "load" -> ((t: HTMLTag) => new LoadEvent(t)),
    "message" -> ((t: HTMLTag) => new MessageEvent(t)),
    "offline" -> ((t: HTMLTag) => new OfflineEvent(t)),
    "online" -> ((t: HTMLTag) => new OnlineEvent(t)),
    "pagehide" -> ((t: HTMLTag) => new PageHideEvent(t)),
    "pageshow" -> ((t: HTMLTag) => new PageShowEvent(t)),
    "popstate" -> ((t: HTMLTag) => new PopStateEvent(t)),
    "redo" -> ((t: HTMLTag) => new RedoEvent(t)),
    "resize" -> ((t: HTMLTag) => new ResizeEvent(t)),
    "storage" -> ((t: HTMLTag) => new StorageEvent(t)),
    "undo" -> ((t: HTMLTag) => new UndoEvent(t)),
    "unload" -> ((t: HTMLTag) => new UnLoadEvent(t)),
    "blur" -> ((t: HTMLTag) => new BlurEvent(t)),
    "change" -> ((t: HTMLTag) => new ChangeEvent(t)),
    "contextmenu" -> ((t: HTMLTag) => new ContextMenuEvent(t)),
    "focus" -> ((t: HTMLTag) => new FocusEvent(t)),
    "formchange" -> ((t: HTMLTag) => new FormChangeEvent(t)),
    "forminput" -> ((t: HTMLTag) => new FormInputEvent(t)),
    "input" -> ((t: HTMLTag) => new InputEvent(t)),
    "invalid" -> ((t: HTMLTag) => new InvalidEvent(t)),
    "reset" -> ((t: HTMLTag) => new ResetEvent(t)),
    "select" -> ((t: HTMLTag) => new SelectEvent(t)),
    "submit" -> ((t: HTMLTag) => new SubmitEvent(t)),
    "click" -> ((t: HTMLTag) => new ClickEvent(t)),
    "dblclick" -> ((t: HTMLTag) => new DoubleClickEvent(t)),
    "drag" -> ((t: HTMLTag) => new DragEvent(t)),
    "dragend" -> ((t: HTMLTag) => new DragEndEvent(t)),
    "dragenter" -> ((t: HTMLTag) => new DragEnterEvent(t)),
    "dragleave" -> ((t: HTMLTag) => new DragLeaveEvent(t)),
    "dragover" -> ((t: HTMLTag) => new DragOverEvent(t)),
    "dragstart" -> ((t: HTMLTag) => new DragStartEvent(t)),
    "drop" -> ((t: HTMLTag) => new DropEvent(t)),
    "mousedown" -> ((t: HTMLTag) => new MouseDownEvent(t)),
    "mousemove" -> ((t: HTMLTag) => new MouseMoveEvent(t)),
    "mouseout" -> ((t: HTMLTag) => new MouseOutEvent(t)),
    "mouseover" -> ((t: HTMLTag) => new MouseOverEvent(t)),
    "mouseup" -> ((t: HTMLTag) => new MouseUpEvent(t)),
    "mousewheel" -> ((t: HTMLTag) => new MouseWheelEvent(t)),
    "scroll" -> ((t: HTMLTag) => new ScrollEvent(t)),
    "abort" -> ((t: HTMLTag) => new AbortEvent(t)),
    "canplay" -> ((t: HTMLTag) => new CanPlayEvent(t)),
    "canplaythrough" -> ((t: HTMLTag) => new CanPlayThroughEvent(t)),
    "durationchange" -> ((t: HTMLTag) => new DurationChangeEvent(t)),
    "emptied" -> ((t: HTMLTag) => new EmptiedEvent(t)),
    "ended" -> ((t: HTMLTag) => new EndedEvent(t)),
    "error" -> ((t: HTMLTag) => new ErrorEvent(t)),
    "loadeddata" -> ((t: HTMLTag) => new LoadedDataEvent(t)),
    "loadedmetadata" -> ((t: HTMLTag) => new LoadedMetaDataEvent(t)),
    "loadstart" -> ((t: HTMLTag) => new LoadStartEvent(t)),
    "pause" -> ((t: HTMLTag) => new PauseEvent(t)),
    "play" -> ((t: HTMLTag) => new PlayEvent(t)),
    "playing" -> ((t: HTMLTag) => new PlayingEvent(t)),
    "progress" -> ((t: HTMLTag) => new ProgressEvent(t)),
    "ratechange" -> ((t: HTMLTag) => new RateChangeEvent(t)),
    "readystatechange" -> ((t: HTMLTag) => new ReadyStateChangeEvent(t)),
    "seeked" -> ((t: HTMLTag) => new SeekedEvent(t)),
    "seeking" -> ((t: HTMLTag) => new SeekingEvent(t)),
    "stalled" -> ((t: HTMLTag) => new StalledEvent(t)),
    "suspend" -> ((t: HTMLTag) => new SuspendEvent(t)),
    "timeupdate" -> ((t: HTMLTag) => new TimeUpdateEvent(t)),
    "volumechange" -> ((t: HTMLTag) => new VolumeChangeEvent(t)),
    "waiting" -> ((t: HTMLTag) => new WaitingEvent(t))
  )

  def unapply(eventType: String) = creator(eventType)

  def creator(eventType: String) = map.get(eventType)
  
  def create(tag: HTMLTag, eventType: String) = creator(eventType) match {
    case Some(c) => c(tag)
    case None => throw new RuntimeException(s"No creator for $eventType")
  }

  def createKeyEvent(tag: HTMLTag,
                     eventType: String,
                     altKey: Boolean,
                     char: Int,
                     ctrlKey: Boolean,
                     key: Int,
                     locale: String,
                     location: Long,
                     metaKey: Boolean,
                     repeat: Boolean,
                     shiftKey: Boolean) = {
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
class SubmitEvent(tag: HTMLTag, method: Method = Method.Post) extends JavaScriptEvent(tag)
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
