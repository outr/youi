package org.hyperscala.fabricjs

import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Text(name: String = "Text") extends Object(name) {
  lazy val fontFamily = prop("fontFamily", "Times New Roman")
  lazy val fontSize = prop("fontSize", 40.0)
  lazy val fontStyle = prop("fontStyle", "normal")
  lazy val fontWeight = prop("fontWeight", "normal")
  lazy val lineHeight = prop("lineHeight", 1.3)
  lazy val path = prop[String]("path", null)
  lazy val textAlign = prop("textAlign", "left")
  lazy val textBackgroundColor = prop[Color]("textBackgroundColor", null)
  lazy val textDecoration = prop("textDecoration", "")
  lazy val useNative = prop("useNative", true)
}
