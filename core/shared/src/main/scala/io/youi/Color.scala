package io.youi

import scala.util.Random

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
      val t: Double = if (redInt == colorMax) {
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

  override def toString: String = s"Color(red: $red, green: $green, blue: $blue, alpha: $alpha)"
}

// TODO: Regenerate using https://en.wikipedia.org/wiki/List_of_colors:_A–F
object Color extends Stringify[Color] {
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
  lazy val all: Vector[Color] = Vector(
    Clear,
    AliceBlue,
    AntiqueWhite,
    Aqua,
    Aquamarine,
    Azure,
    Beige,
    Bisque,
    Black,
    BlanchedAlmond,
    Blue,
    BlueViolet,
    Brown,
    BurlyWood,
    CadetBlue,
    Chartreuse,
    Chocolate,
    Coral,
    CornflowerBlue,
    Cornsilk,
    Crimson,
    Cyan,
    DarkBlue,
    DarkCyan,
    DarkGoldenRod,
    DarkGray,
    DarkGreen,
    DarkKhaki,
    DarkMagenta,
    DarkOliveGreen,
    DarkOrange,
    DarkOrchid,
    DarkRed,
    DarkSalmon,
    DarkSeaGreen,
    DarkSlateBlue,
    DarkSlateGray,
    DarkTurquoise,
    DarkViolet,
    DeepPink,
    DeepSkyBlue,
    DimGray,
    DodgerBlue,
    FireBrick,
    FloralWhite,
    ForestGreen,
    Fuchsia,
    Gainsboro,
    GhostWhite,
    Gold,
    GoldenRod,
    Gray,
    Green,
    GreenYellow,
    HoneyDew,
    HotPink,
    IndianRed,
    Indigo,
    Ivory,
    Khaki,
    Lavender,
    LavenderBlush,
    LawnGreen,
    LemonChiffon,
    LightBlue,
    LightCoral,
    LightCyan,
    LightGoldenRodYellow,
    LightGray,
    LightGreen,
    LightPink,
    LightSalmon,
    LightSeaGreen,
    LightSkyBlue,
    LightSlateGray,
    LightSteelBlue,
    LightYellow,
    Lime,
    LimeGreen,
    Linen,
    Magenta,
    Maroon,
    MediumAquaMarine,
    MediumBlue,
    MediumOrchid,
    MediumPurple,
    MediumSeaGreen,
    MediumSlateBlue,
    MediumSpringGreen,
    MediumTurquoise,
    MediumVioletRed,
    MidnightBlue,
    MintCream,
    MistyRose,
    Moccasin,
    NavajoWhite,
    Navy,
    OldLace,
    Olive,
    OliveDrab,
    Orange,
    OrangeRed,
    Orchid,
    PaleGoldenRod,
    PaleGreen,
    PaleTurquoise,
    PaleVioletRed,
    PapayaWhip,
    PeachPuff,
    Peru,
    Pink,
    Plum,
    PowderBlue,
    Purple,
    RebeccaPurple,
    Red,
    RosyBrown,
    RoyalBlue,
    SaddleBrown,
    Salmon,
    SandyBrown,
    SeaGreen,
    SeaShell,
    Sienna,
    Silver,
    SkyBlue,
    SlateBlue,
    SlateGray,
    Snow,
    SpringGreen,
    SteelBlue,
    Tan,
    Teal,
    Thistle,
    Tomato,
    Turquoise,
    Violet,
    Wheat,
    White,
    WhiteSmoke,
    Yellow,
    YellowGreen
  )

  def random: Color = all(Random.nextInt(all.length))

  private def hexify(value: Double): String = hexify(math.floor(value * 255.0).toInt)
  private def hexify(value: Int): String = f"$value%02x"

  private val Hex3Regex = """#?([\w\d])([\w\d])([\w\d])""".r
  private val Hex6Regex = """#?([\w\d]{2})([\w\d]{2})([\w\d]{2})""".r
  private val RGBARegex = """rgba\((\d+), (\d+), (\d+), ([\d.]+)\)""".r

  def unapply(value: String): Option[Color] = value.trim match {
    case null | "" => None
    case Hex3Regex(r, g, b) => Some(fromLong(java.lang.Long.parseLong(s"$r$r$g$g$b${b}ff", 16)))
    case Hex6Regex(r, g, b) => Some(fromLong(java.lang.Long.parseLong(s"$r$g${b}ff", 16)))
    case RGBARegex(r, g, b, a) => {
      val red = hexify(r.toInt)
      val green = hexify(g.toInt)
      val blue = hexify(b.toInt)
      val alpha = hexify(a.toDouble)
      Some(fromLong(java.lang.Long.parseLong(s"$red$green$blue$alpha", 16)))
    }
    case _ => {
      scribe.warn(s"Unknown conversion for color value from String: [$value]")
      None
    }
  }

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
  @scala.annotation.tailrec
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
    case length => throw new RuntimeException(s"Unsupported hex length ($length) for $value.")
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

  override def fromString(value: String): Option[Color] = unapply(value)

  override def toString(value: Color): Option[String] = Some(if (value.alpha != 1.0) value.toRGBA else value.toHex)
}