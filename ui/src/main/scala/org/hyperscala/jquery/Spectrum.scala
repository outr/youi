package org.hyperscala.jquery

import org.powerscala.{StorageComponent, Color, Version}
import org.hyperscala.module.{Module, InterfaceWithDefault}
import org.hyperscala.web.site.{Webpage, Website}
import com.outr.webcommunicator.netty.handler.PathHandler
import org.hyperscala.html.tag

import scala.language.implicitConversions
import org.hyperscala.html.tag.Input

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Spectrum extends Module with JavaScriptCaller with StorageComponent[Spectrum, Input] {
  implicit def tag2Spectrum(tag: Input) = apply(tag)

  def name = "spectrum"

  def version = Version(1, 1, 1)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  def init() = {
    Website().register(PathHandler("/spectrum/", "spectrum/"))
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Link(href = "/spectrum/spectrum.css", rel = "stylesheet")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/spectrum/spectrum.js")
  }

  override def apply(tag: Input) = {
    Webpage().require(this)
    super.apply(tag)
  }

  protected def create(t: Input) = new Spectrum(t)
}

class Spectrum private(val tag: Input) extends jQueryComponent {
  def functionName = "spectrum"

  val color = property("color", Color.DarkKhaki)

//  color.bindTo(tag.value)()
}