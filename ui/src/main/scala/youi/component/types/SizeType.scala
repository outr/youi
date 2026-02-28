package youi.component.types

sealed abstract class SizeType(val name: String, val includeNumeric: Boolean = true)

object SizeType {
  case object Auto extends SizeType("auto", includeNumeric = false)
  case object Centimeter extends SizeType("cm")
  case object Ch extends SizeType("ch")
  case object Em extends SizeType("em")
  case object Ex extends SizeType("ex")
  case object Inch extends SizeType("in")
  case object Inherit extends SizeType("inherit", includeNumeric = false)
  case object Initial extends SizeType("initial", includeNumeric = false)
  case object Millimeter extends SizeType("mm")
  case object Percent extends SizeType("%")
  case object Pica extends SizeType("pc")
  case object Pixel extends SizeType("px")
  case object Point extends SizeType("pt")
  case object Rem extends SizeType("rem")
  case object ViewportHeight extends SizeType("vh")
  case object ViewportWidth extends SizeType("vw")
  case object ViewportMinimum extends SizeType("vmin")
  case object ViewportMaximum extends SizeType("vmax")

  private val map = List(
    Auto, Centimeter, Ch, Em, Ex, Inch, Inherit, Initial, Millimeter, Percent, Pica, Pixel, Point, Rem, ViewportHeight,
    ViewportWidth, ViewportMinimum, ViewportMaximum
  ).map(t => t.name -> t).toMap

  def apply(name: String): SizeType = map.getOrElse(name.toLowerCase, Pixel)
}