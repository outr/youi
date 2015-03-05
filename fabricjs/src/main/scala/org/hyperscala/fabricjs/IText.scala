package org.hyperscala.fabricjs

import org.hyperscala.javascript.JavaScriptContent
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class IText extends Text("IText") {
  lazy val caching = prop("caching", true)
  lazy val cursorColor = prop[Color]("cursorColor", Color.immutable("#333"))
  lazy val cursorDelay = prop("cursorDelay", 1000)
  lazy val cursorDuration = prop("cursorDuration", 600)
  lazy val cursorWidth = prop("cursorWidth", 2.0)
  lazy val editable = prop("editable", true)
  lazy val editingBorderColor = prop("editingBorderColor", Color.immutable(102, 153, 255, 0.25))
  lazy val selectionColor = prop("selectionColor", Color.immutable(17, 119, 255, 0.3))
  lazy val styles = prop[JavaScriptContent]("styles", null)
}
