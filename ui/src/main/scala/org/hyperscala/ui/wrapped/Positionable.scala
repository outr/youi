package org.hyperscala.ui.wrapped

import org.hyperscala.module.Module
import org.powerscala.{StorageComponent, Version}
import org.hyperscala.html.{tag, HTMLTag}
import org.hyperscala.jquery.{jQueryComponent, jQuery}
import org.hyperscala.web.site.{Webpage, Website}
import org.hyperscala.javascript.{JSFunction1, JavaScriptString}
import org.hyperscala.css.Style
import org.hyperscala.javascript.dsl.Statement

/**
 * Positionable wraps existing elements to allow them to position an element relative to an
 * element, alignment, or more advanced positioning scenario.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Positionable extends Module with StorageComponent[Positionable, HTMLTag] {
  val name = "positionable"
  val version = Version(1)

  override def dependencies = List(jQuery.LatestWithDefault)

  def init() = {
    Website().register("/js/positionable.js", "positionable.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/positionable.js")
  }

  override def apply(t: HTMLTag) = {
    Webpage().require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new Positionable(t)

  private val f2js = (frequency: Double) => JavaScriptString(s"${(frequency * 1000.0).toInt}")
  private val p2js = (list: List[Positioning]) => {
    JavaScriptString(list.map(f => f.content).mkString("[", ", ", "]"))
  }
}

class Positionable private(val wrapped: HTMLTag) extends jQueryComponent {
  protected def functionName = "positionable"

  val frequency = property[Double]("frequency", 0.1, toJS = Positionable.f2js)
  val positioning = property[List[Positioning]]("positioning", Nil, toJS = Positionable.p2js)
  val sendChanges = property[Boolean]("sendChanges", false)
}

trait Positioning extends JSFunction1[Changes, Unit]

object Positioning {
  def apply[T](style: Style[T], statement: Statement) = new JavaScriptString(
    s"""
      |function(changes) {
      | changes.style.${style.cssName} = ${statement.content}
      |}
    """.stripMargin) with Positioning
}

case class Changes(attributes: Map[String, Any], style: Map[String, Any])