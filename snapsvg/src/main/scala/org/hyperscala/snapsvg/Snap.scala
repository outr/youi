package org.hyperscala.snapsvg

import org.hyperscala.module.Module
import org.powerscala.{Color, Version}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.tag
import org.hyperscala.selector.Selector
import org.hyperscala.javascript.dsl.{MultiStatement, Statement}
import org.hyperscala.svg.Svg
import scala.collection.mutable.ListBuffer

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Snap private(selector: Selector) extends Statement[Snap] {
  def content = s"Snap(${selector.content})"

  def circle(x: Statement[Double], y: Statement[Double], r: Statement[Double]) = {
    new Circle(this, x, y, r)
  }

  def group(elements: SnapElement*) = new Group(this, elements: _*)

  def sideEffects = true
}

trait SnapElement {
  def snap: Snap

  def attr(fill: Statement[Color] = null,
           stroke: Statement[Color] = null,
           strokeWidth: Statement[Double] = null,
           mask: Statement[SnapElement] = null) = {
    val args = ListBuffer.empty[Any]
    args += this
    args += ".attr({"
    var firstAttribute = true
    List("fill" -> fill, "stroke" -> stroke, "strokeWidth" -> strokeWidth, "mask" -> mask).foreach {
      case (key, value) => if (value != null) {
        if (!firstAttribute) {
          args += ", "
        }
        firstAttribute = false
        args += key
        args += ": "
        args += value
      }
    }
    args += "})"
    MultiStatement[Circle](sideEffects = true, args: _*)
  }
}

class Group private[snapsvg](val snap: Snap, elements: SnapElement*) extends MultiStatement[Group](sideEffects = true, Group.combine(snap, elements: _*): _*) with SnapElement

object Group {
  def combine(snap: Snap, elements: SnapElement*) = {
    val args = ListBuffer.empty[Any]
    args += snap
    args += ".group("
    var first = true
    elements.foreach {
      case e => {
        if (!first) {
          args += ", "
        }
        first = false
        args += e
      }
    }
    args += ")"
    args
  }
}

class Circle private[snapsvg](val snap: Snap,
                              x: Statement[Double],
                              y: Statement[Double],
                              r: Statement[Double]) extends MultiStatement[Circle](sideEffects = true, snap, ".circle(", x, ", ", y, ", ", r, ")") with SnapElement

object Snap extends Module {
  val name = "Snap.svg"
  val version = Version(0, 1, 0)
  var debug = false

  def apply(selector: Selector) = new Snap(selector)
  def apply(svg: Svg) = new Snap(Selector.id(svg))

  def init() = {
    Website().register("/js/snap.svg.js", "snapsvg/snap.svg.js")
    Website().register("/js/snap.svg-min.js", "snapsvg/snap.svg-min.js")
  }

  def load() = if (debug) {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/snap.svg.js")
  } else {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/snap.svg-min.js")
  }
}