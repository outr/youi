package io.youi.theme

trait FontAwesomeViewTheme extends HTMLComponentTheme with HTMLFontTheme {
  lazy val value: StyleProp[FontAwesomeIcon] = style[FontAwesomeIcon]("value", FontAwesome.None, Some(new StyleConnect[FontAwesomeIcon] {
    override def init(theme: Theme, v: StyleProp[FontAwesomeIcon], name: String): Unit = {
      var previous = Option.empty[FontAwesomeIcon]
      v.attachAndFire { icon =>
        withElement(theme) { e =>
          previous.foreach { i =>
            e.classList.remove(i.prefix)
            e.classList.remove(s"fa-${i.name}")
          }
          if (icon != FontAwesome.None) {
            e.classList.add(icon.prefix)
            e.classList.add(s"fa-${icon.name}")
            previous = Some(icon)
          } else {
            previous = None
          }
        }
      }
    }
  }), updatesTransform = true)
}