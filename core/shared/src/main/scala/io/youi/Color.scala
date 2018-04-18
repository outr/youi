package io.youi

/**
  * Color provides a primitive wrapper around a Long representing a Color. This class takes up no more memory space than
  * a `Long` value and can be used to work powerfully around RGBA values.
  */
class Color private(val value: Long) extends AnyVal {
  def red: Double = redInt / 255.0
  def green: Double = greenInt / 255.0
  def blue: Double = blueInt / 255.0
  def alpha: Double = alphaInt / 255.0

  def redInt: Int = (value >> 24 & 0xff).toInt
  def greenInt: Int = (value >> 16 & 0xff).toInt
  def blueInt: Int = (value >> 8 & 0xff).toInt
  def alphaInt: Int = (value >> 0 & 0xff).toInt

  def redHex: String = Color.hexify(redInt)
  def greenHex: String = Color.hexify(greenInt)
  def blueHex: String = Color.hexify(blueInt)
  def alphaHex: String = Color.hexify(alphaInt)

  def withRed(red: Double): Color = Color.fromHex(s"${Color.hexify(red)}$greenHex$blueHex$alphaHex")
  def withGreen(green: Double): Color = Color.fromHex(s"$redHex${Color.hexify(green)}$blueHex$alphaHex")
  def withBlue(blue: Double): Color = Color.fromHex(s"$redHex$greenHex${Color.hexify(blue)}$alphaHex")
  def withAlpha(alpha: Double): Color = Color.fromHex(s"$redHex$greenHex$blueHex${Color.hexify(alpha)}")

  def toRGBA: String = s"rgba($redInt, $greenInt, $blueInt, $alpha)"
  def toHex: String = s"#$redHex$greenHex$blueHex"

  def hue: Double = {
    val colorMax = math.max(redInt, math.max(greenInt, blueInt))
    val colorMin = math.min(redInt, math.min(greenInt, blueInt))
    if (saturation == 0.0) {
      0.0
    } else {
      val red = (colorMax - redInt) / (colorMax - colorMin)
      val green = (colorMax - greenInt) / (colorMax - colorMin)
      val blue = (colorMax - blueInt) / (colorMax - colorMin)
      val t = if (redInt == colorMax) {
        blue - green
      } else if (greenInt == colorMax) {
        2.0 + red - blue
      } else {
        4.0 + green - red
      }
      t / 6.0 match {
        case v if v < 0.0 => v + 1.0
        case v => v
      }
    }
  }

  def saturation: Double = {
    val colorMax = math.max(redInt, math.max(greenInt, blueInt))
    val colorMin = math.min(redInt, math.min(greenInt, blueInt))
    if (colorMax == 0) {
      0
    } else {
      (colorMax - colorMin).toDouble / colorMax.toDouble
    }
  }

  def brightness: Double = {
    val colorMax = math.max(redInt, math.max(greenInt, blueInt)).toDouble
    colorMax / 255.0
  }

  def withHue(hue: Double): Color = Color.fromHSB(hue, saturation, brightness)
  def withSaturation(saturation: Double): Color = Color.fromHSB(hue, saturation, brightness)
  def withBrightness(brightness: Double): Color = Color.fromHSB(hue, saturation, brightness)
}

object Color {
  val Clear: Color = fromLong(0x00000000)
  val AliceBlue: Color = fromLong(0xF0F8FFFF)
  val AntiqueWhite: Color = fromLong(0xFAEBD7FF)
  val Aqua: Color = fromLong(0x00FFFFFF)
  val Aquamarine: Color = fromLong(0x7FFFD4FF)
  val Azure: Color = fromLong(0xF0FFFFFF)
  val Beige: Color = fromLong(0xF5F5DCFF)
  val Bisque: Color = fromLong(0xFFE4C4FF)
  val Black: Color = fromLong(0x000000FF)
  val BlanchedAlmond: Color = fromLong(0xFFEBCDFF)
  val Blue: Color = fromLong(0x0000FFFF)
  val BlueViolet: Color = fromLong(0x8A2BE2FF)
  val Brown: Color = fromLong(0xA52A2AFF)
  val BurlyWood: Color = fromLong(0xDEB887FF)
  val CadetBlue: Color = fromLong(0x5F9EA0FF)
  val Chartreuse: Color = fromLong(0x7FFF00FF)
  val Chocolate: Color = fromLong(0xD2691EFF)
  val Coral: Color = fromLong(0xFF7F50FF)
  val CornflowerBlue: Color = fromLong(0x6495EDFF)
  val Cornsilk: Color = fromLong(0xFFF8DCFF)
  val Crimson: Color = fromLong(0xDC143CFF)
  val Cyan: Color = fromLong(0x00FFFFFF)
  val DarkBlue: Color = fromLong(0x00008BFF)
  val DarkCyan: Color = fromLong(0x008B8BFF)
  val DarkGoldenRod: Color = fromLong(0xB8860BFF)
  val DarkGray: Color = fromLong(0xA9A9A9FF)
  val DarkGreen: Color = fromLong(0x006400FF)
  val DarkKhaki: Color = fromLong(0xBDB76BFF)
  val DarkMagenta: Color = fromLong(0x8B008BFF)
  val DarkOliveGreen: Color = fromLong(0x556B2FFF)
  val DarkOrange: Color = fromLong(0xFF8C00FF)
  val DarkOrchid: Color = fromLong(0x9932CCFF)
  val DarkRed: Color = fromLong(0x8B0000FF)
  val DarkSalmon: Color = fromLong(0xE9967AFF)
  val DarkSeaGreen: Color = fromLong(0x8FBC8FFF)
  val DarkSlateBlue: Color = fromLong(0x483D8BFF)
  val DarkSlateGray: Color = fromLong(0x2F4F4FFF)
  val DarkTurquoise: Color = fromLong(0x00CED1FF)
  val DarkViolet: Color = fromLong(0x9400D3FF)
  val DeepPink: Color = fromLong(0xFF1493FF)
  val DeepSkyBlue: Color = fromLong(0x00BFFFFF)
  val DimGray: Color = fromLong(0x696969FF)
  val DodgerBlue: Color = fromLong(0x1E90FFFF)
  val FireBrick: Color = fromLong(0xB22222FF)
  val FloralWhite: Color = fromLong(0xFFFAF0FF)
  val ForestGreen: Color = fromLong(0x228B22FF)
  val Fuchsia: Color = fromLong(0xFF00FFFF)
  val Gainsboro: Color = fromLong(0xDCDCDCFF)
  val GhostWhite: Color = fromLong(0xF8F8FFFF)
  val Gold: Color = fromLong(0xFFD700FF)
  val GoldenRod: Color = fromLong(0xDAA520FF)
  val Gray: Color = fromLong(0x808080FF)
  val Green: Color = fromLong(0x008000FF)
  val GreenYellow: Color = fromLong(0xADFF2FFF)
  val HoneyDew: Color = fromLong(0xF0FFF0FF)
  val HotPink: Color = fromLong(0xFF69B4FF)
  val IndianRed: Color = fromLong(0xCD5C5CFF)
  val Indigo: Color = fromLong(0x4B0082FF)
  val Ivory: Color = fromLong(0xFFFFF0FF)
  val Khaki: Color = fromLong(0xF0E68CFF)
  val Lavender: Color = fromLong(0xE6E6FAFF)
  val LavenderBlush: Color = fromLong(0xFFF0F5FF)
  val LawnGreen: Color = fromLong(0x7CFC00FF)
  val LemonChiffon: Color = fromLong(0xFFFACDFF)
  val LightBlue: Color = fromLong(0xADD8E6FF)
  val LightCoral: Color = fromLong(0xF08080FF)
  val LightCyan: Color = fromLong(0xE0FFFFFF)
  val LightGoldenRodYellow: Color = fromLong(0xFAFAD2FF)
  val LightGray: Color = fromLong(0xD3D3D3FF)
  val LightGreen: Color = fromLong(0x90EE90FF)
  val LightPink: Color = fromLong(0xFFB6C1FF)
  val LightSalmon: Color = fromLong(0xFFA07AFF)
  val LightSeaGreen: Color = fromLong(0x20B2AAFF)
  val LightSkyBlue: Color = fromLong(0x87CEFAFF)
  val LightSlateGray: Color = fromLong(0x778899FF)
  val LightSteelBlue: Color = fromLong(0xB0C4DEFF)
  val LightYellow: Color = fromLong(0xFFFFE0FF)
  val Lime: Color = fromLong(0x00FF00FF)
  val LimeGreen: Color = fromLong(0x32CD32FF)
  val Linen: Color = fromLong(0xFAF0E6FF)
  val Magenta: Color = fromLong(0xFF00FFFF)
  val Maroon: Color = fromLong(0x800000FF)
  val MediumAquaMarine: Color = fromLong(0x66CDAAFF)
  val MediumBlue: Color = fromLong(0x0000CDFF)
  val MediumOrchid: Color = fromLong(0xBA55D3FF)
  val MediumPurple: Color = fromLong(0x9370DBFF)
  val MediumSeaGreen: Color = fromLong(0x3CB371FF)
  val MediumSlateBlue: Color = fromLong(0x7B68EEFF)
  val MediumSpringGreen: Color = fromLong(0x00FA9AFF)
  val MediumTurquoise: Color = fromLong(0x48D1CCFF)
  val MediumVioletRed: Color = fromLong(0xC71585FF)
  val MidnightBlue: Color = fromLong(0x191970FF)
  val MintCream: Color = fromLong(0xF5FFFAFF)
  val MistyRose: Color = fromLong(0xFFE4E1FF)
  val Moccasin: Color = fromLong(0xFFE4B5FF)
  val NavajoWhite: Color = fromLong(0xFFDEADFF)
  val Navy: Color = fromLong(0x000080FF)
  val OldLace: Color = fromLong(0xFDF5E6FF)
  val Olive: Color = fromLong(0x808000FF)
  val OliveDrab: Color = fromLong(0x6B8E23FF)
  val Orange: Color = fromLong(0xFFA500FF)
  val OrangeRed: Color = fromLong(0xFF4500FF)
  val Orchid: Color = fromLong(0xDA70D6FF)
  val PaleGoldenRod: Color = fromLong(0xEEE8AAFF)
  val PaleGreen: Color = fromLong(0x98FB98FF)
  val PaleTurquoise: Color = fromLong(0xAFEEEEFF)
  val PaleVioletRed: Color = fromLong(0xDB7093FF)
  val PapayaWhip: Color = fromLong(0xFFEFD5FF)
  val PeachPuff: Color = fromLong(0xFFDAB9FF)
  val Peru: Color = fromLong(0xCD853FFF)
  val Pink: Color = fromLong(0xFFC0CBFF)
  val Plum: Color = fromLong(0xDDA0DDFF)
  val PowderBlue: Color = fromLong(0xB0E0E6FF)
  val Purple: Color = fromLong(0x800080FF)
  val RebeccaPurple: Color = fromLong(0x663399FF)
  val Red: Color = fromLong(0xFF0000FF)
  val RosyBrown: Color = fromLong(0xBC8F8FFF)
  val RoyalBlue: Color = fromLong(0x4169E1FF)
  val SaddleBrown: Color = fromLong(0x8B4513FF)
  val Salmon: Color = fromLong(0xFA8072FF)
  val SandyBrown: Color = fromLong(0xF4A460FF)
  val SeaGreen: Color = fromLong(0x2E8B57FF)
  val SeaShell: Color = fromLong(0xFFF5EEFF)
  val Sienna: Color = fromLong(0xA0522DFF)
  val Silver: Color = fromLong(0xC0C0C0FF)
  val SkyBlue: Color = fromLong(0x87CEEBFF)
  val SlateBlue: Color = fromLong(0x6A5ACDFF)
  val SlateGray: Color = fromLong(0x708090FF)
  val Snow: Color = fromLong(0xFFFAFAFF)
  val SpringGreen: Color = fromLong(0x00FF7FFF)
  val SteelBlue: Color = fromLong(0x4682B4FF)
  val Tan: Color = fromLong(0xD2B48CFF)
  val Teal: Color = fromLong(0x008080FF)
  val Thistle: Color = fromLong(0xD8BFD8FF)
  val Tomato: Color = fromLong(0xFF6347FF)
  val Turquoise: Color = fromLong(0x40E0D0FF)
  val Violet: Color = fromLong(0xEE82EEFF)
  val Wheat: Color = fromLong(0xF5DEB3FF)
  val White: Color = fromLong(0xFFFFFFFF)
  val WhiteSmoke: Color = fromLong(0xF5F5F5FF)
  val Yellow: Color = fromLong(0xFFFF00FF)
  val YellowGreen: Color = fromLong(0x9ACD32FF)

  private def hexify(value: Double): String = hexify(math.floor(value * 255.0).toInt)
  private def hexify(value: Int): String = f"$value%02x"

  /**
    * Creates a new Color instance from a Long value 0xRRGGBBAA
    */
  def fromLong(value: Long): Color = new Color(value)

  /**
    * Creates a new Color instance from a hex value. It is flexible for:
    *
    * - 1-digit (represents the value for red, green, and blue)
    * - 2-digit (represents the value for red, green, and blue)
    * - 3-digit (RGB)
    * - 4-digit (RGBA)
    * - 6-digit (RRGGBB)
    * - 8-digit (RRGGBBAA)
    *
    * A preceding hash is optional.
    *
    * @param value String representation of a hex String
    */
  def fromHex(value: String): Color = value.length match {
    case _ if value.startsWith("#") => fromHex(value.substring(1))
    case 1 => fromHex((0 until 8).map(_ => value).mkString)
    case 2 => fromHex((0 until 4).map(_ => value).mkString)
    case 3 => {
      val r = value.charAt(0)
      val g = value.charAt(1)
      val b = value.charAt(2)
      fromHex(s"$r$r$g$g$b${b}ff")
    }
    case 4 => {
      val r = value.charAt(0)
      val g = value.charAt(1)
      val b = value.charAt(2)
      val a = value.charAt(3)
      fromHex(s"$r$r$g$g$b$b$a$a")
    }
    case 6 => {
      fromHex(s"${value}ff")
    }
    case 8 => {
      fromLong(java.lang.Long.parseLong(value, 16))
    }
  }

  def fromRGBA(red: Double, green: Double, blue: Double, alpha: Double): Color = {
    fromHex(s"${hexify(red)}${hexify(green)}${hexify(blue)}${hexify(alpha)}")
  }

  def fromRGBA(red: Int, green: Int, blue: Int, alpha: Double): Color = {
    fromHex(s"${hexify(red)}${hexify(green)}${hexify(blue)}${hexify(alpha)}")
  }

  def fromHSB(hue: Double, saturation: Double, brightness: Double): Color = if (saturation == 0.0) {
    val value = (brightness * 255.0 + 0.5).toInt
    fromRGBA(value, value, value, 1.0)
  } else {
    val h = (hue - math.floor(hue)) * 6.0
    val f = h - math.floor(h)
    val p = brightness * (1.0 - saturation)
    val q = brightness * (1.0 - saturation * f)
    val t = brightness * (1.0 - (saturation * (1.0 - f)))
    h.toInt match {
      case 0 => {
        val red = (brightness * 255.0 + 0.5).toInt
        val green = (t * 255.0 + 0.5).toInt
        val blue = (p * 255.0 + 0.5).toInt
        Color.fromRGBA(red, green, blue, 1.0)
      }
      case 1 => {
        val red = (q * 255.0 + 0.5).toInt
        val green = (brightness * 255.0 + 0.5).toInt
        val blue = (p * 255.0 + 0.5).toInt
        Color.fromRGBA(red, green, blue, 1.0)
      }
      case 2 => {
        val red = (p * 255.0 + 0.5).toInt
        val green = (brightness * 255.0 + 0.5).toInt
        val blue = (t * 255.0 + 0.5).toInt
        Color.fromRGBA(red, green, blue, 1.0)
      }
      case 3 => {
        val red = (p * 255.0 + 0.5).toInt
        val green = (q * 255.0 + 0.5).toInt
        val blue = (brightness * 255.0 + 0.5).toInt
        Color.fromRGBA(red, green, blue, 1.0)
      }
      case 4 => {
        val red = (t * 255.0 + 0.5).toInt
        val green = (p * 255.0 + 0.5).toInt
        val blue = (brightness * 255.0 + 0.5).toInt
        Color.fromRGBA(red, green, blue, 1.0)
      }
      case 5 => {
        val red = (brightness * 255.0 + 0.5).toInt
        val green = (p * 255.0 + 0.5).toInt
        val blue = (q * 255.0 + 0.5).toInt
        Color.fromRGBA(red, green, blue, 1.0)
      }
    }
  }
}