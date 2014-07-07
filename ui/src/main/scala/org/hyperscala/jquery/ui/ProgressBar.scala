package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.web._
import org.hyperscala.jquery.{jQueryComponent, JavaScriptCaller}
import org.powerscala.StorageComponent
import org.powerscala.event.Listenable
import scala.language.implicitConversions

/**
 * ProgressBar is a light-weight wrapper around jQuery UI's Progressbar widget:
 *
 * http://api.jqueryui.com/progressbar/
 *
 * @author Matt Hicks <matt@outr.com>
 */
object ProgressBar extends JavaScriptCaller with StorageComponent[ProgressBar, HTMLTag] {
  implicit def tag2ProgressBar(t: HTMLTag) = apply(t)

  override def apply(t: HTMLTag) = {
    t.require(jQueryUI.LatestWithDefault)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new ProgressBar(t)

  private val ValueConverter = (o: Option[Int]) => o match {
    case Some(v) => JavaScriptString(v.toString)
    case None => JavaScriptString("false")
  }
}

class ProgressBar private(val wrapped: HTMLTag) extends jQueryComponent {
  def functionName = "progressbar"
  def autoInit = true

  implicit def listenable: Listenable = wrapped

  val disabled = property("disabled", false)
  val max = property("max", 100)
  val value: JavaScriptProperty[Option[Int]] = property("value", Some(0), toJS = ProgressBar.ValueConverter)
}