package io.youi.theme

import io.youi.theme.StyleConnect.withElement
import io.youi.theme.mixins.HTMLFontTheme
import org.scalajs.dom.html

trait HTMLTextInputTheme extends HTMLComponentTheme with HTMLFontTheme {
  lazy val value: StyleProp[String] = style[String]("value", "", Some(new StyleConnect[String] {
    override def init(theme: Theme, v: StyleProp[String], name: String): Unit = withElement(theme) { e =>
      val value = v.option.map(valueOption => valueOption.getOrElse(v.value()))
      value.attachAndFire(s => e.asInstanceOf[html.Input].value = s)
    }
  }), updatesTransform = false)
  lazy val placeholder: StyleProp[String] = style[String]("placeholder", "", StyleConnect.field[String], updatesTransform = false)
}