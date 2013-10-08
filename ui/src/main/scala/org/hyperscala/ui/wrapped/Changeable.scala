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
 * Changeable wraps existing elements to allow them to change an element relative to an
 * element, alignment, or more advanced change scenario.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Changeable extends Module with StorageComponent[Changeable, HTMLTag] {
  val name = "changeable"
  val version = Version(1)

  override def dependencies = List(jQuery.LatestWithDefault)

  def init() = {
    Website().register("/js/changeable.js", "changeable.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/changeable.js")
  }

  override def apply(t: HTMLTag) = {
    Webpage().require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new Changeable(t)

  private val f2js = (frequency: Double) => JavaScriptString(s"${(frequency * 1000.0).toInt}")
  private val c2js = (list: List[Changing]) => {
    JavaScriptString(list.map(f => f.content).mkString("[", ", ", "]"))
  }
}

class Changeable private(val wrapped: HTMLTag) extends jQueryComponent {
  protected def functionName = "changeable"

  val frequency = property[Double]("frequency", 0.1, toJS = Changeable.f2js)
  val changing = property[List[Changing]]("changing", Nil, toJS = Changeable.c2js)
  val sendChanges = property[Boolean]("sendChanges", false)
}

trait Changing extends JSFunction1[Changes, Unit]

object Changing {
  def apply[T](style: Style[T], statement: Statement) = new JavaScriptString(
    s"""
      |function(changes) {
      | changes.style.${style.cssName} = ${statement.content}
      |}
    """.stripMargin) with Changing
}

case class Changes(attributes: Map[String, Any], style: Map[String, Any])