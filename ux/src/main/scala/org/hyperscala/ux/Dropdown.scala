package org.hyperscala.ux

import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.dsl.jQuerySelector
import org.hyperscala.web._
import org.hyperscala.jquery.{jQueryComponent, JavaScriptCaller}
import org.powerscala.StorageComponent
import org.powerscala.event.Listenable
import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Dropdown extends JavaScriptCaller with StorageComponent[Dropdown, HTMLTag] {
  implicit def tag2Dropdown(tag: HTMLTag) = apply(tag)

  override def apply(t: HTMLTag) = {
    t.require(UX)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new Dropdown(t)

  private val Selector2JS = (s: jQuerySelector) => JavaScriptString(s.toJS)
}

class Dropdown private(val wrapped: HTMLTag) extends jQueryComponent {
  def autoInit = true
  def functionName = "dropdown"

  implicit def listenable: Listenable = wrapped

  val selector = property[jQuerySelector]("dropdownSelector", null, toJS = Dropdown.Selector2JS)
  val modifyDropdownCSS = property("modifyDropdownCSS", true)
  val showOn = property("showOn", "click")
  val hideOnClick = property("hideOnClick", true)

  def open() = call("open")
  def close() = call("close")
  def toggle() = call("toggle")
}