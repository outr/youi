package io.youi

/**
  * Color provides a simple wrapper around RGBA color information.
  */
case class Color(red: Double = 0.0, green: Double = 0.0, blue: Double = 0.0, alpha: Double = 0.0) {
  def toCSS: String = Color.toCSS(red, green, blue, alpha)
  override def toString: String = s"Color(red = $red, green = $green, blue = $blue, alpha = $alpha)"
}

object Color {
  lazy val Clear: Color = fromLong(0x00000000)

  lazy val AliceBlue: Color = fromLong(0xF0F8FFFF)
  lazy val AntiqueWhite: Color = fromLong(0xFAEBD7FF)
  lazy val Aqua: Color = fromLong(0x00FFFFFF)
  lazy val Aquamarine: Color = fromLong(0x7FFFD4FF)
  lazy val Azure: Color = fromLong(0xF0FFFFFF)
  lazy val Beige: Color = fromLong(0xF5F5DCFF)
  lazy val Bisque: Color = fromLong(0xFFE4C4FF)
  lazy val Black: Color = fromLong(0x000000FF)
  lazy val BlanchedAlmond: Color = fromLong(0xFFEBCDFF)
  lazy val Blue: Color = fromLong(0x0000FFFF)
  lazy val BlueViolet: Color = fromLong(0x8A2BE2FF)
  lazy val Brown: Color = fromLong(0xA52A2AFF)
  lazy val BurlyWood: Color = fromLong(0xDEB887FF)
  lazy val CadetBlue: Color = fromLong(0x5F9EA0FF)
  lazy val Chartreuse: Color = fromLong(0x7FFF00FF)
  lazy val Chocolate: Color = fromLong(0xD2691EFF)
  lazy val Coral: Color = fromLong(0xFF7F50FF)
  lazy val CornflowerBlue: Color = fromLong(0x6495EDFF)
  lazy val Cornsilk: Color = fromLong(0xFFF8DCFF)
  lazy val Crimson: Color = fromLong(0xDC143CFF)
  lazy val Cyan: Color = fromLong(0x00FFFFFF)
  lazy val DarkBlue: Color = fromLong(0x00008BFF)
  lazy val DarkCyan: Color = fromLong(0x008B8BFF)
  lazy val DarkGoldenRod: Color = fromLong(0xB8860BFF)
  lazy val DarkGray: Color = fromLong(0xA9A9A9FF)
  lazy val DarkGreen: Color = fromLong(0x006400FF)
  lazy val DarkKhaki: Color = fromLong(0xBDB76BFF)
  lazy val DarkMagenta: Color = fromLong(0x8B008BFF)
  lazy val DarkOliveGreen: Color = fromLong(0x556B2FFF)
  lazy val DarkOrange: Color = fromLong(0xFF8C00FF)
  lazy val DarkOrchid: Color = fromLong(0x9932CCFF)
  lazy val DarkRed: Color = fromLong(0x8B0000FF)
  lazy val DarkSalmon: Color = fromLong(0xE9967AFF)
  lazy val DarkSeaGreen: Color = fromLong(0x8FBC8FFF)
  lazy val DarkSlateBlue: Color = fromLong(0x483D8BFF)
  lazy val DarkSlateGray: Color = fromLong(0x2F4F4FFF)
  lazy val DarkTurquoise: Color = fromLong(0x00CED1FF)
  lazy val DarkViolet: Color = fromLong(0x9400D3FF)
  lazy val DeepPink: Color = fromLong(0xFF1493FF)
  lazy val DeepSkyBlue: Color = fromLong(0x00BFFFFF)
  lazy val DimGray: Color = fromLong(0x696969FF)
  lazy val DodgerBlue: Color = fromLong(0x1E90FFFF)
  lazy val FireBrick: Color = fromLong(0xB22222FF)
  lazy val FloralWhite: Color = fromLong(0xFFFAF0FF)
  lazy val ForestGreen: Color = fromLong(0x228B22FF)
  lazy val Fuchsia: Color = fromLong(0xFF00FFFF)
  lazy val Gainsboro: Color = fromLong(0xDCDCDCFF)
  lazy val GhostWhite: Color = fromLong(0xF8F8FFFF)
  lazy val Gold: Color = fromLong(0xFFD700FF)
  lazy val GoldenRod: Color = fromLong(0xDAA520FF)
  lazy val Gray: Color = fromLong(0x808080FF)
  lazy val Green: Color = fromLong(0x008000FF)
  lazy val GreenYellow: Color = fromLong(0xADFF2FFF)
  lazy val HoneyDew: Color = fromLong(0xF0FFF0FF)
  lazy val HotPink: Color = fromLong(0xFF69B4FF)
  lazy val IndianRed: Color = fromLong(0xCD5C5CFF)
  lazy val Indigo: Color = fromLong(0x4B0082FF)
  lazy val Ivory: Color = fromLong(0xFFFFF0FF)
  lazy val Khaki: Color = fromLong(0xF0E68CFF)
  lazy val Lavender: Color = fromLong(0xE6E6FAFF)
  lazy val LavenderBlush: Color = fromLong(0xFFF0F5FF)
  lazy val LawnGreen: Color = fromLong(0x7CFC00FF)
  lazy val LemonChiffon: Color = fromLong(0xFFFACDFF)
  lazy val LightBlue: Color = fromLong(0xADD8E6FF)
  lazy val LightCoral: Color = fromLong(0xF08080FF)
  lazy val LightCyan: Color = fromLong(0xE0FFFFFF)
  lazy val LightGoldenRodYellow: Color = fromLong(0xFAFAD2FF)
  lazy val LightGray: Color = fromLong(0xD3D3D3FF)
  lazy val LightGreen: Color = fromLong(0x90EE90FF)
  lazy val LightPink: Color = fromLong(0xFFB6C1FF)
  lazy val LightSalmon: Color = fromLong(0xFFA07AFF)
  lazy val LightSeaGreen: Color = fromLong(0x20B2AAFF)
  lazy val LightSkyBlue: Color = fromLong(0x87CEFAFF)
  lazy val LightSlateGray: Color = fromLong(0x778899FF)
  lazy val LightSteelBlue: Color = fromLong(0xB0C4DEFF)
  lazy val LightYellow: Color = fromLong(0xFFFFE0FF)
  lazy val Lime: Color = fromLong(0x00FF00FF)
  lazy val LimeGreen: Color = fromLong(0x32CD32FF)
  lazy val Linen: Color = fromLong(0xFAF0E6FF)
  lazy val Magenta: Color = fromLong(0xFF00FFFF)
  lazy val Maroon: Color = fromLong(0x800000FF)
  lazy val MediumAquaMarine: Color = fromLong(0x66CDAAFF)
  lazy val MediumBlue: Color = fromLong(0x0000CDFF)
  lazy val MediumOrchid: Color = fromLong(0xBA55D3FF)
  lazy val MediumPurple: Color = fromLong(0x9370DBFF)
  lazy val MediumSeaGreen: Color = fromLong(0x3CB371FF)
  lazy val MediumSlateBlue: Color = fromLong(0x7B68EEFF)
  lazy val MediumSpringGreen: Color = fromLong(0x00FA9AFF)
  lazy val MediumTurquoise: Color = fromLong(0x48D1CCFF)
  lazy val MediumVioletRed: Color = fromLong(0xC71585FF)
  lazy val MidnightBlue: Color = fromLong(0x191970FF)
  lazy val MintCream: Color = fromLong(0xF5FFFAFF)
  lazy val MistyRose: Color = fromLong(0xFFE4E1FF)
  lazy val Moccasin: Color = fromLong(0xFFE4B5FF)
  lazy val NavajoWhite: Color = fromLong(0xFFDEADFF)
  lazy val Navy: Color = fromLong(0x000080FF)
  lazy val OldLace: Color = fromLong(0xFDF5E6FF)
  lazy val Olive: Color = fromLong(0x808000FF)
  lazy val OliveDrab: Color = fromLong(0x6B8E23FF)
  lazy val Orange: Color = fromLong(0xFFA500FF)
  lazy val OrangeRed: Color = fromLong(0xFF4500FF)
  lazy val Orchid: Color = fromLong(0xDA70D6FF)
  lazy val PaleGoldenRod: Color = fromLong(0xEEE8AAFF)
  lazy val PaleGreen: Color = fromLong(0x98FB98FF)
  lazy val PaleTurquoise: Color = fromLong(0xAFEEEEFF)
  lazy val PaleVioletRed: Color = fromLong(0xDB7093FF)
  lazy val PapayaWhip: Color = fromLong(0xFFEFD5FF)
  lazy val PeachPuff: Color = fromLong(0xFFDAB9FF)
  lazy val Peru: Color = fromLong(0xCD853FFF)
  lazy val Pink: Color = fromLong(0xFFC0CBFF)
  lazy val Plum: Color = fromLong(0xDDA0DDFF)
  lazy val PowderBlue: Color = fromLong(0xB0E0E6FF)
  lazy val Purple: Color = fromLong(0x800080FF)
  lazy val RebeccaPurple: Color = fromLong(0x663399FF)
  lazy val Red: Color = fromLong(0xFF0000FF)
  lazy val RosyBrown: Color = fromLong(0xBC8F8FFF)
  lazy val RoyalBlue: Color = fromLong(0x4169E1FF)
  lazy val SaddleBrown: Color = fromLong(0x8B4513FF)
  lazy val Salmon: Color = fromLong(0xFA8072FF)
  lazy val SandyBrown: Color = fromLong(0xF4A460FF)
  lazy val SeaGreen: Color = fromLong(0x2E8B57FF)
  lazy val SeaShell: Color = fromLong(0xFFF5EEFF)
  lazy val Sienna: Color = fromLong(0xA0522DFF)
  lazy val Silver: Color = fromLong(0xC0C0C0FF)
  lazy val SkyBlue: Color = fromLong(0x87CEEBFF)
  lazy val SlateBlue: Color = fromLong(0x6A5ACDFF)
  lazy val SlateGray: Color = fromLong(0x708090FF)
  lazy val Snow: Color = fromLong(0xFFFAFAFF)
  lazy val SpringGreen: Color = fromLong(0x00FF7FFF)
  lazy val SteelBlue: Color = fromLong(0x4682B4FF)
  lazy val Tan: Color = fromLong(0xD2B48CFF)
  lazy val Teal: Color = fromLong(0x008080FF)
  lazy val Thistle: Color = fromLong(0xD8BFD8FF)
  lazy val Tomato: Color = fromLong(0xFF6347FF)
  lazy val Turquoise: Color = fromLong(0x40E0D0FF)
  lazy val Violet: Color = fromLong(0xEE82EEFF)
  lazy val Wheat: Color = fromLong(0xF5DEB3FF)
  lazy val White: Color = fromLong(0xFFFFFFFF)
  lazy val WhiteSmoke: Color = fromLong(0xF5F5F5FF)
  lazy val Yellow: Color = fromLong(0xFFFF00FF)
  lazy val YellowGreen: Color = fromLong(0x9ACD32FF)

  /**
    * Creates a new Color instance from a Long value 0xRRGGBBAA
    *
    * @param value the numeric RGBA value
    */
  def fromLong(value: Long): Color = Color(
    red = (value >> 24 & 0xff) / 255.0,
    green = (value >> 16 & 0xff) / 255.0,
    blue = (value >> 8 & 0xff) / 255.0,
    alpha = (value >> 0 & 0xff) / 255.0
  )

  /**
    * Creates a new Color instance from a hex value. It is flexible for 3-digit and 6-digit with or without a leading
    * hash.
    *
    * @param hex String representation of a hex String
    */
  def fromHex(hex: String): Color = {
    if (hex.startsWith("#")) {
      fromHex(hex.substring(1))
    } else if (hex.length == 6) {
      fromHex(s"${hex}ff")
    } else if (hex.length == 3) {
      val r = hex.charAt(0)
      val g = hex.charAt(1)
      val b = hex.charAt(2)
      fromHex(s"$r$r$g$g$b${b}ff")
    } else {
      fromLong(java.lang.Long.parseLong(hex, 16))
    }
  }

  def toHex(color: Color): String = toHex(color.red, color.green, color.blue)

  def toHex(red: Double, green: Double, blue: Double): String = {
    f"#${(red * 255.0).toInt}%02x${(green * 255.0).toInt}%02x${(blue * 255.0).toInt}%02x"
  }

  def toCSS(red: Double, green: Double, blue: Double, alpha: Double): String = {
    def i(d: Double): Int = math.round(d * 255.0).toInt
    if (alpha != 1.0) {
      s"rgb(${i(red)}, ${i(green)}, ${i(blue)})"
    } else {
      s"rgba(${i(red)}, ${i(green)}, ${i(blue)}, $alpha)"
    }
  }
}