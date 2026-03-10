package youi.component.types

import reactify.{Val, Var}

import scala.util.matching.Regex

class SizeProperty(cssGet: => String, cssSet: String => Unit, measured: Val[Double] = Val(0.0), callbacks: (() => Unit)*) extends Var[Double](measured()) {
  private val setStyle = cssSet
  val `type`: Var[SizeType] = Var(SizeType.Auto)
  private var _explicitlySet = false

  // If CSS already has an explicit value, use it instead of measured
  locally {
    val (v, t) = SizeProperty(cssGet)
    if (v >= 0.0) {
      _explicitlySet = true
      `type`.static(t)
      static(v)
    }
  }

  attach { d =>
    if (_explicitlySet) {
      if (`type`() == SizeType.Auto) `type` @= SizeType.Pixel
      syncToStyle()
    }
    callbacks.foreach(_())
  }
  `type`.on(if (_explicitlySet) syncToStyle())

  override def @=(value: Double): Unit = {
    val wasExplicit = _explicitlySet
    _explicitlySet = true
    if (!wasExplicit || get != value) {
      // Force reactions on first explicit set (even if same value as measured)
      // or when value actually changes
      val prev = option
      static(value)
      reactify.Reactive.fire(this, value, prev, reactions())
    }
  }

  override def :=(value: => Double): Unit = {
    val wasExplicit = _explicitlySet
    _explicitlySet = true
    val evaluated = value
    if (!wasExplicit || get != evaluated) {
      val prev = option
      static(evaluated)
      reactify.Reactive.fire(this, evaluated, prev, reactions())
    }
    // Also store as reactive function for future re-evaluation
    super.:=(value)
  }

  def set(value: Double, sizeType: => SizeType): Unit = {
    this.`type` := sizeType
    this @= value
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
    val (v, t) = SizeProperty(cssGet)
    if (v >= 0.0) {
      `type`.static(t)
      this @= v
    }
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
