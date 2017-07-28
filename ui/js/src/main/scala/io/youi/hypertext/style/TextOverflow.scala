package io.youi.hypertext.style

sealed abstract class TextOverflow(val value: String)

object TextOverflow {
  case object NoClip extends TextOverflow("")
  case object Clip extends TextOverflow("clip")
  case object Ellipsis extends TextOverflow("ellipsis")
}