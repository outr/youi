package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag
import org.hyperscala.html.attributes.Target
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.selector.Selector

import scala.collection.mutable.ListBuffer

/**
 * @author Matt Hicks <matt@outr.com>
 */
object window extends DelayedStatement[HTMLTag] with Selector {
  def thisValue = "window"
  def parentSelector = None

  def thisMatches(t: HTMLTag) = false

  override def quoted = false

  def parent = ExistingStatement[DelayedStatement[HTMLTag]]("window.parent")

  def toStatement = ExistingStatement[HTMLTag]("window")

  def innerWidth = ExistingStatement[Double]("window.innerWidth")
  def innerHeight = ExistingStatement[Double]("window.innerHeight")

  def duplicate(parent: Option[Selector]) = throw new RuntimeException("window cannot be duplicated")

  def open(url: String, target: Target = Target.Self) = {
    JavaScriptString(s"window.open(${JavaScriptContext.toJS(url)}, ${JavaScriptContext.toJS(target)});")
  }

  def alert(statements: Statement[String]*) = {
    val b = ListBuffer.empty[Any]
    b.append("alert(")
    b.append(statements.head)
    b.appendAll(statements.tail.map(s => List(" + ", s)).flatten)
    b.append(")")
    MultiStatement[Unit](sideEffects = true, b.toList: _*)
  }
}
