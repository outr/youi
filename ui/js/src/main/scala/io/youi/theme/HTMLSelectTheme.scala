package io.youi.theme

import io.youi.theme.StyleConnect.withElement
import io.youi.theme.mixins.HTMLFontTheme
import org.scalajs.dom.html

trait HTMLSelectTheme extends HTMLComponentTheme with HTMLFontTheme {
  protected var valueChanging = false

  lazy val value: StyleProp[String] = style[String]("value", "", Some(new StyleConnect[String] {
    override def init(theme: Theme, v: StyleProp[String], name: String): Unit = withElement(theme) { e =>
      val value = v.option.map(valueOption => valueOption.getOrElse(v.value()))
      value.attachAndFire { s =>
        if (!valueChanging) {
          e.asInstanceOf[html.Select].value = s
        }
      }
    }
  }))
}