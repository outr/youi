package youi.component.types

import reactify.Var

import scala.util.matching.Regex

class SizeProperty(get: => String, set: String => Unit, callbacks: (() => Unit)*) extends Var[Double](-1.0) {
  private val setStyle = set
  val `type`: Var[SizeType] = Var(SizeType.Auto)

  refresh()

  private var changed = false

  attach { d =>
    if (d != -1.0 && `type`() == SizeType.Auto && !changed) {
      `type` @= SizeType.Pixel
      changed = true
    }
    syncToStyle()
    callbacks.foreach(_())
  }
  `type`.on(syncToStyle())

  def set(value: Double, sizeType: => SizeType): Unit = {
    this.`type` := sizeType
    this @= value
    syncToStyle()
  }

  private def syncToStyle(): Unit = {
    val t = `type`() match {
      case SizeType.Auto => ""
      case st => st.name
    }
    val value = if (`type`().includeNumeric) {
      val d = apply()
      s"$d$t"
    } else {
      t
    }
    setStyle(value)
  }

  def refresh(): Unit = {
    val (v, t) = SizeProperty(get)
    this @= v
    `type`.static(t)
  }
}

object SizeProperty {
  val ValueRegex: Regex = """([0-9.]+)(ch|em|ex|rem|vh|vw|vmin|vmax|px|cm|mm|in|pc|pt)""".r

  def apply(value: String): (Double, SizeType) = value.trim match {
    case null | "" | "auto" => -1.0 -> SizeType.Auto
    case "initial" => -1.0 -> SizeType.Initial
    case "inherit" => -1.0 -> SizeType.Inherit
    case SizeProperty.ValueRegex(number, unit) => number.toDouble -> SizeType(unit)
  }
}