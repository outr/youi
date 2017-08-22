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
  def Clear: Color = fromLong(0x00000000)
  def AliceBlue: Color = fromLong(0xF0F8FFFF)
  def AntiqueWhite: Color = fromLong(0xFAEBD7FF)
  def Aqua: Color = fromLong(0x00FFFFFF)
  def Aquamarine: Color = fromLong(0x7FFFD4FF)
  def Azure: Color = fromLong(0xF0FFFFFF)
  def Beige: Color = fromLong(0xF5F5DCFF)
  def Bisque: Color = fromLong(0xFFE4C4FF)
  def Black: Color = fromLong(0x000000FF)
  def BlanchedAlmond: Color = fromLong(0xFFEBCDFF)
  def Blue: Color = fromLong(0x0000FFFF)
  def BlueViolet: Color = fromLong(0x8A2BE2FF)
  def Brown: Color = fromLong(0xA52A2AFF)
  def BurlyWood: Color = fromLong(0xDEB887FF)
  def CadetBlue: Color = fromLong(0x5F9EA0FF)
  def Chartreuse: Color = fromLong(0x7FFF00FF)
  def Chocolate: Color = fromLong(0xD2691EFF)
  def Coral: Color = fromLong(0xFF7F50FF)
  def CornflowerBlue: Color = fromLong(0x6495EDFF)
  def Cornsilk: Color = fromLong(0xFFF8DCFF)
  def Crimson: Color = fromLong(0xDC143CFF)
  def Cyan: Color = fromLong(0x00FFFFFF)
  def DarkBlue: Color = fromLong(0x00008BFF)
  def DarkCyan: Color = fromLong(0x008B8BFF)
  def DarkGoldenRod: Color = fromLong(0xB8860BFF)
  def DarkGray: Color = fromLong(0xA9A9A9FF)
  def DarkGreen: Color = fromLong(0x006400FF)
  def DarkKhaki: Color = fromLong(0xBDB76BFF)
  def DarkMagenta: Color = fromLong(0x8B008BFF)
  def DarkOliveGreen: Color = fromLong(0x556B2FFF)
  def DarkOrange: Color = fromLong(0xFF8C00FF)
  def DarkOrchid: Color = fromLong(0x9932CCFF)
  def DarkRed: Color = fromLong(0x8B0000FF)
  def DarkSalmon: Color = fromLong(0xE9967AFF)
  def DarkSeaGreen: Color = fromLong(0x8FBC8FFF)
  def DarkSlateBlue: Color = fromLong(0x483D8BFF)
  def DarkSlateGray: Color = fromLong(0x2F4F4FFF)
  def DarkTurquoise: Color = fromLong(0x00CED1FF)
  def DarkViolet: Color = fromLong(0x9400D3FF)
  def DeepPink: Color = fromLong(0xFF1493FF)
  def DeepSkyBlue: Color = fromLong(0x00BFFFFF)
  def DimGray: Color = fromLong(0x696969FF)
  def DodgerBlue: Color = fromLong(0x1E90FFFF)
  def FireBrick: Color = fromLong(0xB22222FF)
  def FloralWhite: Color = fromLong(0xFFFAF0FF)
  def ForestGreen: Color = fromLong(0x228B22FF)
  def Fuchsia: Color = fromLong(0xFF00FFFF)
  def Gainsboro: Color = fromLong(0xDCDCDCFF)
  def GhostWhite: Color = fromLong(0xF8F8FFFF)
  def Gold: Color = fromLong(0xFFD700FF)
  def GoldenRod: Color = fromLong(0xDAA520FF)
  def Gray: Color = fromLong(0x808080FF)
  def Green: Color = fromLong(0x008000FF)
  def GreenYellow: Color = fromLong(0xADFF2FFF)
  def HoneyDew: Color = fromLong(0xF0FFF0FF)
  def HotPink: Color = fromLong(0xFF69B4FF)
  def IndianRed: Color = fromLong(0xCD5C5CFF)
  def Indigo: Color = fromLong(0x4B0082FF)
  def Ivory: Color = fromLong(0xFFFFF0FF)
  def Khaki: Color = fromLong(0xF0E68CFF)
  def Lavender: Color = fromLong(0xE6E6FAFF)
  def LavenderBlush: Color = fromLong(0xFFF0F5FF)
  def LawnGreen: Color = fromLong(0x7CFC00FF)
  def LemonChiffon: Color = fromLong(0xFFFACDFF)
  def LightBlue: Color = fromLong(0xADD8E6FF)
  def LightCoral: Color = fromLong(0xF08080FF)
  def LightCyan: Color = fromLong(0xE0FFFFFF)
  def LightGoldenRodYellow: Color = fromLong(0xFAFAD2FF)
  def LightGray: Color = fromLong(0xD3D3D3FF)
  def LightGreen: Color = fromLong(0x90EE90FF)
  def LightPink: Color = fromLong(0xFFB6C1FF)
  def LightSalmon: Color = fromLong(0xFFA07AFF)
  def LightSeaGreen: Color = fromLong(0x20B2AAFF)
  def LightSkyBlue: Color = fromLong(0x87CEFAFF)
  def LightSlateGray: Color = fromLong(0x778899FF)
  def LightSteelBlue: Color = fromLong(0xB0C4DEFF)
  def LightYellow: Color = fromLong(0xFFFFE0FF)
  def Lime: Color = fromLong(0x00FF00FF)
  def LimeGreen: Color = fromLong(0x32CD32FF)
  def Linen: Color = fromLong(0xFAF0E6FF)
  def Magenta: Color = fromLong(0xFF00FFFF)
  def Maroon: Color = fromLong(0x800000FF)
  def MediumAquaMarine: Color = fromLong(0x66CDAAFF)
  def MediumBlue: Color = fromLong(0x0000CDFF)
  def MediumOrchid: Color = fromLong(0xBA55D3FF)
  def MediumPurple: Color = fromLong(0x9370DBFF)
  def MediumSeaGreen: Color = fromLong(0x3CB371FF)
  def MediumSlateBlue: Color = fromLong(0x7B68EEFF)
  def MediumSpringGreen: Color = fromLong(0x00FA9AFF)
  def MediumTurquoise: Color = fromLong(0x48D1CCFF)
  def MediumVioletRed: Color = fromLong(0xC71585FF)
  def MidnightBlue: Color = fromLong(0x191970FF)
  def MintCream: Color = fromLong(0xF5FFFAFF)
  def MistyRose: Color = fromLong(0xFFE4E1FF)
  def Moccasin: Color = fromLong(0xFFE4B5FF)
  def NavajoWhite: Color = fromLong(0xFFDEADFF)
  def Navy: Color = fromLong(0x000080FF)
  def OldLace: Color = fromLong(0xFDF5E6FF)
  def Olive: Color = fromLong(0x808000FF)
  def OliveDrab: Color = fromLong(0x6B8E23FF)
  def Orange: Color = fromLong(0xFFA500FF)
  def OrangeRed: Color = fromLong(0xFF4500FF)
  def Orchid: Color = fromLong(0xDA70D6FF)
  def PaleGoldenRod: Color = fromLong(0xEEE8AAFF)
  def PaleGreen: Color = fromLong(0x98FB98FF)
  def PaleTurquoise: Color = fromLong(0xAFEEEEFF)
  def PaleVioletRed: Color = fromLong(0xDB7093FF)
  def PapayaWhip: Color = fromLong(0xFFEFD5FF)
  def PeachPuff: Color = fromLong(0xFFDAB9FF)
  def Peru: Color = fromLong(0xCD853FFF)
  def Pink: Color = fromLong(0xFFC0CBFF)
  def Plum: Color = fromLong(0xDDA0DDFF)
  def PowderBlue: Color = fromLong(0xB0E0E6FF)
  def Purple: Color = fromLong(0x800080FF)
  def RebeccaPurple: Color = fromLong(0x663399FF)
  def Red: Color = fromLong(0xFF0000FF)
  def RosyBrown: Color = fromLong(0xBC8F8FFF)
  def RoyalBlue: Color = fromLong(0x4169E1FF)
  def SaddleBrown: Color = fromLong(0x8B4513FF)
  def Salmon: Color = fromLong(0xFA8072FF)
  def SandyBrown: Color = fromLong(0xF4A460FF)
  def SeaGreen: Color = fromLong(0x2E8B57FF)
  def SeaShell: Color = fromLong(0xFFF5EEFF)
  def Sienna: Color = fromLong(0xA0522DFF)
  def Silver: Color = fromLong(0xC0C0C0FF)
  def SkyBlue: Color = fromLong(0x87CEEBFF)
  def SlateBlue: Color = fromLong(0x6A5ACDFF)
  def SlateGray: Color = fromLong(0x708090FF)
  def Snow: Color = fromLong(0xFFFAFAFF)
  def SpringGreen: Color = fromLong(0x00FF7FFF)
  def SteelBlue: Color = fromLong(0x4682B4FF)
  def Tan: Color = fromLong(0xD2B48CFF)
  def Teal: Color = fromLong(0x008080FF)
  def Thistle: Color = fromLong(0xD8BFD8FF)
  def Tomato: Color = fromLong(0xFF6347FF)
  def Turquoise: Color = fromLong(0x40E0D0FF)
  def Violet: Color = fromLong(0xEE82EEFF)
  def Wheat: Color = fromLong(0xF5DEB3FF)
  def White: Color = fromLong(0xFFFFFFFF)
  def WhiteSmoke: Color = fromLong(0xF5F5F5FF)
  def Yellow: Color = fromLong(0xFFFF00FF)
  def YellowGreen: Color = fromLong(0x9ACD32FF)

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