package io.youi.theme

import io.youi.font.{Material, MaterialIcon}
import io.youi.theme.mixins.HTMLFontTheme

trait MaterialIconViewTheme extends HTMLComponentTheme with HTMLFontTheme {
  private implicit val iconStringify: Stringify[MaterialIcon] = new Stringify[MaterialIcon] {
    override def fromString(value: String): Option[MaterialIcon] = Some(MaterialIcon(value))

    override def toString(value: MaterialIcon): Option[String] = Some(value.name)
  }

  lazy val value: StyleProp[MaterialIcon] = style[MaterialIcon]("value", Material.Icons.Action.Help, StyleConnect.content[MaterialIcon], updatesTransform = false)
}