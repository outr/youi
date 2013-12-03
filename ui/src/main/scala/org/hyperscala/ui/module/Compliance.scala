package org.hyperscala.ui.module

import org.hyperscala.css.Style
import org.hyperscala.web.useragent.{BrowserFamily, UserAgent}
import org.hyperscala.html.HTMLTag
import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Compliance(t: HTMLTag) {
  implicit def transformPersistence = Transform

  val IEZoom = new Style[Double]("zoom")
  val FirefoxTransform = new Style[Transform]("-moz-transform")
  val FirefoxTransformOrigin = new Style[Double]("-moz-transform-origin")
  val OperaTransform = new Style[Transform]("-o-transform")
  val OperaTransformOrigin = new Style[Double]("-o-transform-origin")
  val WebkitTransform = new Style[Transform]("-webkit-transform")
  val WebkitTransformOrigin = new Style[Double]("-webkit-transform-origin")

  def scalePercent(v: Int) = scale(v / 100.0)
  def scalePercent = math.round(scale * 100.0).toInt

  def scale(v: Double) = if (UserAgent().browser.family == BrowserFamily.IE) {
    t.style(IEZoom, None) := v
  } else if (UserAgent().browser.family == BrowserFamily.Firefox) {
    t.style(FirefoxTransform, None) := Scale(v)
    t.style(FirefoxTransformOrigin, None) := 0.0
  } else if (UserAgent().browser.family == BrowserFamily.Opera) {
    t.style(OperaTransform, None) := Scale(v)
    t.style(OperaTransformOrigin, None) := 0.0
  } else {
    t.style(WebkitTransform, None) := Scale(v)
    t.style(WebkitTransformOrigin, None) := 0.0
  }

  def scale = if (UserAgent().browser.family == BrowserFamily.IE) {
    t.style(IEZoom, Some(1.0)).value
  } else if (UserAgent().browser.family == BrowserFamily.Firefox) {
    t.style(FirefoxTransform, Some(Scale(1.0))).value match {
      case s: Scale => s.value
    }
  } else if (UserAgent().browser.family == BrowserFamily.Opera) {
    t.style(OperaTransform, Some(Scale(1.0))).value match {
      case s: Scale => s.value
    }
  } else {
    t.style(WebkitTransform, Some(Scale(1.0))).value match {
      case s: Scale => s.value
    }
  }
}

// TODO: migrate functionality into style
sealed trait Transform {
  def toCSS: String
}

object Transform extends ValuePersistence[Transform] {
  def fromString(s: String, name: String, clazz: Class[_]) = throw new UnsupportedOperationException("Transform")

  def toString(t: Transform, name: String, clazz: Class[_]) = t.toCSS
}

case class Scale(value: Double) extends Transform {
  def toCSS = s"scale($value)"
}