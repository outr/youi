package io.youi.font

import io.youi.event.TouchData

case class Touching(textPath: TextPath, data: TouchData) {
  override def toString: String = s"Touching(char: ${textPath.char}, data: $data)"
}
