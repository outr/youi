package io.youi.ui.types

import reactify.Var
import reactify.standard.StandardVar

import scala.util.matching.Regex

class SizeProperty(get: => String, set: String => Unit, callbacks: (() => Unit)*) extends StandardVar[Double](0.0, Var.Mode.Normal, None) {
  val `type`: Var[SizeType] = Var(SizeType.Auto)

  refresh()

  attach { d =>
    if (d != 0.0 && `type`() == SizeType.Auto) {
      `type` @= SizeType.Pixel
    }
    set()
    callbacks.foreach(_())
  }
  `type`.on(set())

  private def set(): Unit = {
    val t = `type`().name
    val value = if (`type`.includeNumeric) {
      val d = apply()
      s"$d$t"
    } else {
      t
    }
    set(value)
  }

  def refresh(): Unit = get match {
    case null | "" | "auto" => {
      this @= 0.0
      `type` @= SizeType.Auto
    }
    case "initial" => {
      this @= 0.0
      `type` @= SizeType.Initial
    }
    case "inherit" => {
      this @= 0.0
      `type` @= SizeType.Inherit
    }
    case SizeProperty.ValueRegex(number, unit) => {
      this @= number.toDouble
      `type` @= SizeType(unit)
    }
  }
}

object SizeProperty {
  val ValueRegex: Regex = """([0-9.]+)(ch|em|ex|rem|vh|vw|vmin|vmax|px|cm|mm|in|pc|pt)""".r
}