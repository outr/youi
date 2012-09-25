package org.hyperscala.web.live

import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.html.HTMLTag
import org.powerscala.event.Event
import org.hyperscala.css.StyleSheetAttribute

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LiveEvent(tag: HTMLTag) extends Event

object LiveEvent {
  def apply(confirmation: String = null,
            preventDefault: Boolean = true,
            fireChange: Boolean = false,
            onlyLast: Boolean = true) = {
    val b = new StringBuilder
    if (confirmation != null) {
      b.append("if (confirm('%s')) { ".format(confirmation))
    }
    b.append("liveEventHandler(event, (typeof data === 'undefined') ? null : data, %s, %s); ".format(fireChange, onlyLast))
    if (confirmation != null) {
      b.append("} ")
    }
    if (preventDefault) {
      b.append("return false;")
    }
    JavaScriptString(b.toString())
  }

  def create(tag: HTMLTag, map: Map[String, Any]) = {
    val eventType = map("event").asInstanceOf[String]
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
      case "keydown" | "keypress" | "keyup" => {
        val altKey = map("altKey").asInstanceOf[Boolean]
        val char = map("char").asInstanceOf[Double].toInt match {
          case 0 => None
          case i => Some(i.toChar)
        }
        val ctrlKey = map("ctrlKey").asInstanceOf[Boolean]
        val key = map("key").asInstanceOf[Double].toInt
        val locale = map.getOrElse("locale", null).asInstanceOf[String]
        val location = map.getOrElse("location", 0.0).asInstanceOf[Double].toLong
        val metaKey = map("metaKey").asInstanceOf[Boolean]
        val repeat = map.getOrElse("repeat", false).asInstanceOf[Boolean]
        val shiftKey = map("shiftKey").asInstanceOf[Boolean]
        eventType match {
          case "keydown" => new KeyDownEvent(tag = tag,
                                             char = char,
                                             keyCode = key,
                                             keyOption = Key.byCode(key),
                                             altKey = altKey,
                                             ctrlKey = ctrlKey,
                                             metaKey = metaKey,
                                             shiftKey = shiftKey,
                                             repeat = repeat,
                                             location = location,
                                             locale = locale)
          case "keypress" => new KeyPressEvent(tag = tag,
                                                char = char,
                                                keyCode = key,
                                                keyOption = Key.byCode(key),
                                                altKey = altKey,
                                                ctrlKey = ctrlKey,
                                                metaKey = metaKey,
                                                shiftKey = shiftKey,
                                                repeat = repeat,
                                                location = location,
                                                locale = locale)
          case "keyup" => new KeyUpEvent(tag = tag,
                                          char = char,
                                          keyCode = key,
                                          keyOption = Key.byCode(key),
                                          altKey = altKey,
                                          ctrlKey = ctrlKey,
                                          metaKey = metaKey,
                                          shiftKey = shiftKey,
                                          repeat = repeat,
                                          location = location,
                                          locale = locale)
        }
      }
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
      case "stylechange" => {
        val event = new StyleChangeEvent(tag, map("propertyName").asInstanceOf[String], map("propertyValue").asInstanceOf[String])
        if (tag.applyStyleChanges()) {
          val p = event.property[Any]
          println("Changing %s (%s) to %s".format(event.propertyName, p, event.propertyValue))
          p.attributeValue = event.propertyValue
        }
        event
      }
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

class AfterPrintEvent(tag: HTMLTag) extends LiveEvent(tag)
class BeforePrintEvent(tag: HTMLTag) extends LiveEvent(tag)
class BeforeOnLoadEvent(tag: HTMLTag) extends LiveEvent(tag)
class HasChangeEvent(tag: HTMLTag) extends LiveEvent(tag)
class LoadEvent(tag: HTMLTag) extends LiveEvent(tag)
class MessageEvent(tag: HTMLTag) extends LiveEvent(tag)
class OfflineEvent(tag: HTMLTag) extends LiveEvent(tag)
class OnlineEvent(tag: HTMLTag) extends LiveEvent(tag)
class PageHideEvent(tag: HTMLTag) extends LiveEvent(tag)
class PageShowEvent(tag: HTMLTag) extends LiveEvent(tag)
class PopStateEvent(tag: HTMLTag) extends LiveEvent(tag)
class RedoEvent(tag: HTMLTag) extends LiveEvent(tag)
class ResizeEvent(tag: HTMLTag) extends LiveEvent(tag)
class StorageEvent(tag: HTMLTag) extends LiveEvent(tag)
class UndoEvent(tag: HTMLTag) extends LiveEvent(tag)
class UnLoadEvent(tag: HTMLTag) extends LiveEvent(tag)
class BlurEvent(tag: HTMLTag) extends LiveEvent(tag)
class ChangeEvent(tag: HTMLTag) extends LiveEvent(tag)
class ContextMenuEvent(tag: HTMLTag) extends LiveEvent(tag)
class FocusEvent(tag: HTMLTag) extends LiveEvent(tag)
class FormChangeEvent(tag: HTMLTag) extends LiveEvent(tag)
class FormInputEvent(tag: HTMLTag) extends LiveEvent(tag)
class InputEvent(tag: HTMLTag) extends LiveEvent(tag)
class InvalidEvent(tag: HTMLTag) extends LiveEvent(tag)
class ResetEvent(tag: HTMLTag) extends LiveEvent(tag)
class SelectEvent(tag: HTMLTag) extends LiveEvent(tag)
class SubmitEvent(tag: HTMLTag) extends LiveEvent(tag)
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
                   val locale: String) extends LiveEvent(tag) with KeyboardEvent
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
                    val locale: String) extends LiveEvent(tag) with KeyboardEvent
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
                 val locale: String) extends LiveEvent(tag) with KeyboardEvent
class ClickEvent(tag: HTMLTag) extends LiveEvent(tag)
class DoubleClickEvent(tag: HTMLTag) extends LiveEvent(tag)
class DragEvent(tag: HTMLTag) extends LiveEvent(tag)
class DragEndEvent(tag: HTMLTag) extends LiveEvent(tag)
class DragEnterEvent(tag: HTMLTag) extends LiveEvent(tag)
class DragLeaveEvent(tag: HTMLTag) extends LiveEvent(tag)
class DragOverEvent(tag: HTMLTag) extends LiveEvent(tag)
class DragStartEvent(tag: HTMLTag) extends LiveEvent(tag)
class DropEvent(tag: HTMLTag) extends LiveEvent(tag)
class MouseDownEvent(tag: HTMLTag) extends LiveEvent(tag)
class MouseMoveEvent(tag: HTMLTag) extends LiveEvent(tag)
class MouseOutEvent(tag: HTMLTag) extends LiveEvent(tag)
class MouseOverEvent(tag: HTMLTag) extends LiveEvent(tag)
class MouseUpEvent(tag: HTMLTag) extends LiveEvent(tag)
class MouseWheelEvent(tag: HTMLTag) extends LiveEvent(tag)
class ScrollEvent(tag: HTMLTag) extends LiveEvent(tag)
class AbortEvent(tag: HTMLTag) extends LiveEvent(tag)
class CanPlayEvent(tag: HTMLTag) extends LiveEvent(tag)
class CanPlayThroughEvent(tag: HTMLTag) extends LiveEvent(tag)
class DurationChangeEvent(tag: HTMLTag) extends LiveEvent(tag)
class EmptiedEvent(tag: HTMLTag) extends LiveEvent(tag)
class EndedEvent(tag: HTMLTag) extends LiveEvent(tag)
class ErrorEvent(tag: HTMLTag) extends LiveEvent(tag)
class LoadedDataEvent(tag: HTMLTag) extends LiveEvent(tag)
class LoadedMetaDataEvent(tag: HTMLTag) extends LiveEvent(tag)
class LoadStartEvent(tag: HTMLTag) extends LiveEvent(tag)
class PauseEvent(tag: HTMLTag) extends LiveEvent(tag)
class PlayEvent(tag: HTMLTag) extends LiveEvent(tag)
class PlayingEvent(tag: HTMLTag) extends LiveEvent(tag)
class ProgressEvent(tag: HTMLTag) extends LiveEvent(tag)
class RateChangeEvent(tag: HTMLTag) extends LiveEvent(tag)
class ReadyStateChangeEvent(tag: HTMLTag) extends LiveEvent(tag)
class SeekedEvent(tag: HTMLTag) extends LiveEvent(tag)
class SeekingEvent(tag: HTMLTag) extends LiveEvent(tag)
class StalledEvent(tag: HTMLTag) extends LiveEvent(tag)
class SuspendEvent(tag: HTMLTag) extends LiveEvent(tag)
class TimeUpdateEvent(tag: HTMLTag) extends LiveEvent(tag)
class VolumeChangeEvent(tag: HTMLTag) extends LiveEvent(tag)
class WaitingEvent(tag: HTMLTag) extends LiveEvent(tag)
class StyleChangeEvent(tag: HTMLTag,
                       val propertyName: String,
                       val propertyValue: String) extends LiveEvent(tag) {
  def property[T] = tag.style().properties[T](propertyName).asInstanceOf[StyleSheetAttribute[T]]
}