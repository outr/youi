package io.youi.event

import io.youi.key.Key
import org.scalajs.{dom => jsdom}

class KeyEvent(underlying: jsdom.KeyboardEvent, val `type`: KeyEvent.Type) extends HTMLEvent(underlying) {
  lazy val key: Key = Key.get(underlying.key).getOrElse(Key.Unidentified)

  def repeat: Boolean = underlying.repeat
  def altPressed: Boolean = modifierState(Key.Alt)
  def altGraphPressed: Boolean = modifierState(Key.AltGraph)
  def controlPressed: Boolean = modifierState(Key.Control)
  def shiftPressed: Boolean = modifierState(Key.Shift)
  def capsLockOn: Boolean = modifierState(Key.CapsLock)
  def numLockOn: Boolean = modifierState(Key.NumLock)
  def scrollLockOn: Boolean = modifierState(Key.ScrollLock)

  private def modifierState(key: Key): Boolean = underlying.getModifierState(key.value)
}

object KeyEvent {
  sealed trait Type

  object Type {
    case object Down extends Type
    case object Press extends Type
    case object Up extends Type
  }
}