package org.hyperscala.event

/**
 * @author Matt Hicks <matt@outr.com>
 */
import org.hyperscala.html.HTMLTag
import com.outr.net.Method

/**
 * @author Matt Hicks <matt@outr.com>
 */
class JavaScriptEvent(val tag: HTMLTag, val target: Option[HTMLTag])

object JavaScriptEvent {
  private val map = Map[String, (HTMLTag, Option[HTMLTag]) => JavaScriptEvent](
    "afterprint" -> ((t: HTMLTag, target: Option[HTMLTag]) => new AfterPrintEvent(t, target)),
    "beforeprint" -> ((t: HTMLTag, target: Option[HTMLTag]) => new BeforePrintEvent(t, target)),
    "beforeonload" -> ((t: HTMLTag, target: Option[HTMLTag]) => new BeforeOnLoadEvent(t, target)),
    "haschange" -> ((t: HTMLTag, target: Option[HTMLTag]) => new HasChangeEvent(t, target)),
    "load" -> ((t: HTMLTag, target: Option[HTMLTag]) => new LoadEvent(t, target)),
    "message" -> ((t: HTMLTag, target: Option[HTMLTag]) => new MessageEvent(t, target)),
    "offline" -> ((t: HTMLTag, target: Option[HTMLTag]) => new OfflineEvent(t, target)),
    "online" -> ((t: HTMLTag, target: Option[HTMLTag]) => new OnlineEvent(t, target)),
    "pagehide" -> ((t: HTMLTag, target: Option[HTMLTag]) => new PageHideEvent(t, target)),
    "pageshow" -> ((t: HTMLTag, target: Option[HTMLTag]) => new PageShowEvent(t, target)),
    "popstate" -> ((t: HTMLTag, target: Option[HTMLTag]) => new PopStateEvent(t, target)),
    "redo" -> ((t: HTMLTag, target: Option[HTMLTag]) => new RedoEvent(t, target)),
    "resize" -> ((t: HTMLTag, target: Option[HTMLTag]) => new ResizeEvent(t, target)),
    "storage" -> ((t: HTMLTag, target: Option[HTMLTag]) => new StorageEvent(t, target)),
    "undo" -> ((t: HTMLTag, target: Option[HTMLTag]) => new UndoEvent(t, target)),
    "unload" -> ((t: HTMLTag, target: Option[HTMLTag]) => new UnLoadEvent(t, target)),
    "blur" -> ((t: HTMLTag, target: Option[HTMLTag]) => new BlurEvent(t, target)),
    "change" -> ((t: HTMLTag, target: Option[HTMLTag]) => new ChangeEvent(t, target)),
    "contextmenu" -> ((t: HTMLTag, target: Option[HTMLTag]) => new ContextMenuEvent(t, target)),
    "focus" -> ((t: HTMLTag, target: Option[HTMLTag]) => new FocusEvent(t, target)),
    "formchange" -> ((t: HTMLTag, target: Option[HTMLTag]) => new FormChangeEvent(t, target)),
    "forminput" -> ((t: HTMLTag, target: Option[HTMLTag]) => new FormInputEvent(t, target)),
    "input" -> ((t: HTMLTag, target: Option[HTMLTag]) => new InputEvent(t, target)),
    "invalid" -> ((t: HTMLTag, target: Option[HTMLTag]) => new InvalidEvent(t, target)),
    "reset" -> ((t: HTMLTag, target: Option[HTMLTag]) => new ResetEvent(t, target)),
    "select" -> ((t: HTMLTag, target: Option[HTMLTag]) => new SelectEvent(t, target)),
    "submit" -> ((t: HTMLTag, target: Option[HTMLTag]) => new SubmitEvent(t, target)),
    "click" -> ((t: HTMLTag, target: Option[HTMLTag]) => new ClickEvent(t, target)),
    "dblclick" -> ((t: HTMLTag, target: Option[HTMLTag]) => new DoubleClickEvent(t, target)),
    "drag" -> ((t: HTMLTag, target: Option[HTMLTag]) => new DragEvent(t, target)),
    "dragend" -> ((t: HTMLTag, target: Option[HTMLTag]) => new DragEndEvent(t, target)),
    "dragenter" -> ((t: HTMLTag, target: Option[HTMLTag]) => new DragEnterEvent(t, target)),
    "dragleave" -> ((t: HTMLTag, target: Option[HTMLTag]) => new DragLeaveEvent(t, target)),
    "dragover" -> ((t: HTMLTag, target: Option[HTMLTag]) => new DragOverEvent(t, target)),
    "dragstart" -> ((t: HTMLTag, target: Option[HTMLTag]) => new DragStartEvent(t, target)),
    "drop" -> ((t: HTMLTag, target: Option[HTMLTag]) => new DropEvent(t, target)),
    "mousedown" -> ((t: HTMLTag, target: Option[HTMLTag]) => new MouseDownEvent(t, target)),
    "mousemove" -> ((t: HTMLTag, target: Option[HTMLTag]) => new MouseMoveEvent(t, target)),
    "mouseout" -> ((t: HTMLTag, target: Option[HTMLTag]) => new MouseOutEvent(t, target)),
    "mouseover" -> ((t: HTMLTag, target: Option[HTMLTag]) => new MouseOverEvent(t, target)),
    "mouseup" -> ((t: HTMLTag, target: Option[HTMLTag]) => new MouseUpEvent(t, target)),
    "mousewheel" -> ((t: HTMLTag, target: Option[HTMLTag]) => new MouseWheelEvent(t, target)),
    "scroll" -> ((t: HTMLTag, target: Option[HTMLTag]) => new ScrollEvent(t, target)),
    "abort" -> ((t: HTMLTag, target: Option[HTMLTag]) => new AbortEvent(t, target)),
    "canplay" -> ((t: HTMLTag, target: Option[HTMLTag]) => new CanPlayEvent(t, target)),
    "canplaythrough" -> ((t: HTMLTag, target: Option[HTMLTag]) => new CanPlayThroughEvent(t, target)),
    "durationchange" -> ((t: HTMLTag, target: Option[HTMLTag]) => new DurationChangeEvent(t, target)),
    "emptied" -> ((t: HTMLTag, target: Option[HTMLTag]) => new EmptiedEvent(t, target)),
    "ended" -> ((t: HTMLTag, target: Option[HTMLTag]) => new EndedEvent(t, target)),
    "error" -> ((t: HTMLTag, target: Option[HTMLTag]) => new ErrorEvent(t, target)),
    "loadeddata" -> ((t: HTMLTag, target: Option[HTMLTag]) => new LoadedDataEvent(t, target)),
    "loadedmetadata" -> ((t: HTMLTag, target: Option[HTMLTag]) => new LoadedMetaDataEvent(t, target)),
    "loadstart" -> ((t: HTMLTag, target: Option[HTMLTag]) => new LoadStartEvent(t, target)),
    "pause" -> ((t: HTMLTag, target: Option[HTMLTag]) => new PauseEvent(t, target)),
    "play" -> ((t: HTMLTag, target: Option[HTMLTag]) => new PlayEvent(t, target)),
    "playing" -> ((t: HTMLTag, target: Option[HTMLTag]) => new PlayingEvent(t, target)),
    "progress" -> ((t: HTMLTag, target: Option[HTMLTag]) => new ProgressEvent(t, target)),
    "ratechange" -> ((t: HTMLTag, target: Option[HTMLTag]) => new RateChangeEvent(t, target)),
    "readystatechange" -> ((t: HTMLTag, target: Option[HTMLTag]) => new ReadyStateChangeEvent(t, target)),
    "seeked" -> ((t: HTMLTag, target: Option[HTMLTag]) => new SeekedEvent(t, target)),
    "seeking" -> ((t: HTMLTag, target: Option[HTMLTag]) => new SeekingEvent(t, target)),
    "stalled" -> ((t: HTMLTag, target: Option[HTMLTag]) => new StalledEvent(t, target)),
    "suspend" -> ((t: HTMLTag, target: Option[HTMLTag]) => new SuspendEvent(t, target)),
    "timeupdate" -> ((t: HTMLTag, target: Option[HTMLTag]) => new TimeUpdateEvent(t, target)),
    "volumechange" -> ((t: HTMLTag, target: Option[HTMLTag]) => new VolumeChangeEvent(t, target)),
    "waiting" -> ((t: HTMLTag, target: Option[HTMLTag]) => new WaitingEvent(t, target))
  )

  val eventNames = map.values.toList.map(f => f(null, None).getClass.getSimpleName).map(name => name.charAt(0).toLower + name.substring(1, name.length - 5)).sorted

  def unapply(eventType: String) = creator(eventType)

  def creator(eventType: String) = map.get(eventType)
  
  def create(tag: HTMLTag, target: Option[HTMLTag], eventType: String) = creator(eventType) match {
    case Some(c) => c(tag, target)
    case None => throw new RuntimeException(s"No creator for $eventType")
  }

  def createKeyEvent(tag: HTMLTag,
                     target: Option[HTMLTag],
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
      case "keydown" => new KeyDownEvent(tag = tag, target = target, char = c, keyCode = key, keyOption = Key.byCode(key), altKey = altKey, ctrlKey = ctrlKey, metaKey = metaKey, shiftKey = shiftKey, repeat = repeat, location = location, locale = locale)
      case "keyup" => new KeyUpEvent(tag = tag, target = target, char = c, keyCode = key, keyOption = Key.byCode(key), altKey = altKey, ctrlKey = ctrlKey, metaKey = metaKey, shiftKey = shiftKey, repeat = repeat, location = location, locale = locale)
      case "keypress" => new KeyPressEvent(tag = tag, target = target, char = c, keyCode = key, keyOption = Key.byCode(key), altKey = altKey, ctrlKey = ctrlKey, metaKey = metaKey, shiftKey = shiftKey, repeat = repeat, location = location, locale = locale)
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

class AfterPrintEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class BeforePrintEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class BeforeOnLoadEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class HasChangeEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class LoadEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class MessageEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class OfflineEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class OnlineEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class PageHideEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class PageShowEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class PopStateEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class RedoEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class ResizeEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class StorageEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class UndoEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class UnLoadEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class BlurEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class ChangeEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class ContextMenuEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class FocusEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class FormChangeEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class FormInputEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class InputEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class InvalidEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class ResetEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class SelectEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class SubmitEvent(tag: HTMLTag, target: Option[HTMLTag], method: Method = Method.Post) extends JavaScriptEvent(tag, target)
class KeyDownEvent(tag: HTMLTag,
                   target: Option[HTMLTag],
                   val char: Option[Char],
                   val keyCode: Int,
                   val keyOption: Option[Key],
                   val altKey: Boolean,
                   val ctrlKey: Boolean,
                   val metaKey: Boolean,
                   val shiftKey: Boolean,
                   val repeat: Boolean,
                   val location: Long,
                   val locale: String) extends JavaScriptEvent(tag, target) with KeyboardEvent
class KeyPressEvent(tag: HTMLTag,
                    target: Option[HTMLTag],
                    val char: Option[Char],
                    val keyCode: Int,
                    val keyOption: Option[Key],
                    val altKey: Boolean,
                    val ctrlKey: Boolean,
                    val metaKey: Boolean,
                    val shiftKey: Boolean,
                    val repeat: Boolean,
                    val location: Long,
                    val locale: String) extends JavaScriptEvent(tag, target) with KeyboardEvent
class KeyUpEvent(tag: HTMLTag,
                 target: Option[HTMLTag],
                 val char: Option[Char],
                 val keyCode: Int,
                 val keyOption: Option[Key],
                 val altKey: Boolean,
                 val ctrlKey: Boolean,
                 val metaKey: Boolean,
                 val shiftKey: Boolean,
                 val repeat: Boolean,
                 val location: Long,
                 val locale: String) extends JavaScriptEvent(tag, target) with KeyboardEvent
class ClickEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class DoubleClickEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class DragEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class DragEndEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class DragEnterEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class DragLeaveEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class DragOverEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class DragStartEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class DropEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class MouseDownEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class MouseMoveEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class MouseOutEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class MouseOverEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class MouseUpEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class MouseWheelEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class ScrollEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class AbortEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class CanPlayEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class CanPlayThroughEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class DurationChangeEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class EmptiedEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class EndedEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class ErrorEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class LoadedDataEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class LoadedMetaDataEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class LoadStartEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class PauseEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class PlayEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class PlayingEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class ProgressEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class RateChangeEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class ReadyStateChangeEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class SeekedEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class SeekingEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class StalledEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class SuspendEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class TimeUpdateEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class VolumeChangeEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class WaitingEvent(tag: HTMLTag, target: Option[HTMLTag]) extends JavaScriptEvent(tag, target)
class StyleChangeEvent(tag: HTMLTag,
                       target: Option[HTMLTag],
                       val propertyName: String,
                       val propertyValue: String) extends JavaScriptEvent(tag, target) {
//  def property[T] = tag.style().properties[T](propertyName).asInstanceOf[StyleSheetAttribute[T]]
}
