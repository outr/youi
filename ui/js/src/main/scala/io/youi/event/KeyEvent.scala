package io.youi.event

class KeyEvent (target: Component,
                val `type`: KeyEvent.Type,
                val key: Key,
                val repeat: Boolean,
                htmlEvent: KeyboardEvent) extends HTMLEvent(target, htmlEvent) {
  def altPressed: Boolean = modifierState(Key.Alt)
  def altGraphPressed: Boolean = modifierState(Key.AltGraph)
  def controlPressed: Boolean = modifierState(Key.Control)
  def shiftPressed: Boolean = modifierState(Key.Shift)
  def capsLockOn: Boolean = modifierState(Key.CapsLock)
  def numLockOn: Boolean = modifierState(Key.NumLock)
  def scrollLockOn: Boolean = modifierState(Key.ScrollLock)

  private def modifierState(key: Key): Boolean = htmlEvent.getModifierState(key.value)

  override def toString: String = s"KeyEvent(key: $key, repeat: $repeat, alt: $altPressed, ctrl: $controlPressed, shift: $shiftPressed)"
}

object KeyEvent {
  sealed trait Type

  object Type {
    case object Down extends Type
    case object Press extends Type
    case object Up extends Type
  }
}