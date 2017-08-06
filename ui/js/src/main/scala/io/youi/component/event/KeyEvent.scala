package io.youi.component.event

import io.youi.Key
import io.youi.component.Component
import org.scalajs.dom.raw.KeyboardEvent

case class KeyEvent(key: Key, originalEvent: KeyboardEvent) {
  def repeat: Boolean = originalEvent.repeat
  def modifierState(key: Key): Boolean = originalEvent.getModifierState(key.value)
  def preventDefault(): Unit = originalEvent.preventDefault()
  def defaultPrevented(): Boolean = originalEvent.defaultPrevented
  def stopImmediatePropagation(): Unit = originalEvent.stopImmediatePropagation()
  def stopPropagation(): Unit = originalEvent.stopPropagation()

  def altPressed: Boolean = modifierState(Key.Alt)
  def altGraphPressed: Boolean = modifierState(Key.AltGraph)
  def controlPressed: Boolean = modifierState(Key.Control)
  def shiftPressed: Boolean = modifierState(Key.Shift)

  def capsLockOn: Boolean = modifierState(Key.CapsLock)
  def numLockOn: Boolean = modifierState(Key.NumLock)
  def scrollLockOn: Boolean = modifierState(Key.ScrollLock)

  override def toString: String = s"KeyEvent(key: $key, repeat: $repeat, alt: $altPressed, ctrl: $controlPressed, shift: $shiftPressed)"
}

object KeyEvent {
  def apply(event: KeyboardEvent): KeyEvent = {
    val key = Key(event.key)
    KeyEvent(key, event)
  }
}