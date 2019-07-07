package io.youi.font

import io.youi.component.extras.HTMLComponent
import io.youi.dom._
import io.youi.theme.{MaterialIconViewTheme, Theme}
import org.scalajs.dom._

import scribe.Execution.global

class MaterialIconView(protected val element: html.Element,
                       val existing: Boolean = false) extends HTMLComponent[html.Element] with MaterialIconViewTheme {
  def this() = {
    this(create[html.Element]("i"))
  }

  element.classList.add("material-icons")

  Material.load().map(fnt => font := fnt)

  override protected def defaultParentTheme: Theme = MaterialIconView

  override def componentType: String = "MaterialIconView"
}

object MaterialIconView extends MaterialIconViewTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): MaterialIconViewTheme = {
    new MaterialIconView(in.byId[html.Element](id), existing = true)
  }
}