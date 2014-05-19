package org.hyperscala.ui.wrapped

import org.hyperscala.module.Module
import org.powerscala.{StorageComponent, Version}
import org.hyperscala.html.{tag, HTMLTag}
import org.hyperscala.jquery.{jQueryComponent, jQuery}
import org.hyperscala.web._
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.css.Style
import org.hyperscala.javascript.dsl.{JSFunction1, Statement}
import com.outr.net.http.session.Session

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

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/changeable.js", "changeable.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/changeable.js")
  }

  override def apply(t: HTMLTag) = {
    t.require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new Changeable(t)

  private val f2js = (frequency: Double) => JavaScriptString(s"${(frequency * 1000.0).toInt}")
  private val c2js = (list: List[JSFunction1[Changes, Unit]]) => {
    JavaScriptString(list.map(f => f.content).mkString("[", ", ", "]"))
  }
}

class Changeable private(val wrapped: HTMLTag, val autoInit: Boolean = true) extends jQueryComponent {
  protected def functionName = "changeable"

  val frequency = property[Double]("frequency", 0.1, toJS = Changeable.f2js)
  val changing = property[List[JSFunction1[Changes, Unit]]]("changing", Nil, toJS = Changeable.c2js)
  val sendChanges = property[Boolean]("sendChanges", false)
}

object Changing {
  def apply[T](style: Style[T], statement: Statement[_]) = JSFunction1[Changes, Unit](s"p1.style.${style.cssName} = ${statement.content}")
}

case class Changes(attributes: Map[String, Any], style: Map[String, Any])