package io.youi.component

import io.youi.component.feature.{FeatureParent, HeightFeature, WidthFeature}
import io.youi.component.types.Prop
import io.youi.path.Rectangle
import org.scalajs.dom.{CSSStyleDeclaration, html}
import reactify.{Trigger, Val}

class Component(val element: html.Element) extends FeatureParent {
  lazy val id: Prop[String] = new Prop[String](element.id, s => element.setAttribute("id", s))
  object classes extends Prop[List[String]](
    getter = (0 until element.classList.length).toList.map(element.classList.item),
    setter = values => element.setAttribute("class", values.mkString(" "))
  ) {
    def +=(className: String): Unit = this @= (this() :+ className)
    def -=(className: String): Unit = this @= this().filterNot(_ == className)
    def ++=(classNames: Seq[String]): Unit = this @= (this() ++ classNames)
    def --=(classNames: Seq[String]): Unit = this @= (this().filterNot(cn => classNames.contains(cn)))
    def toggle(className: String): Prop[Boolean] = new Prop[Boolean](
      getter = get.contains(className),
      setter = b => if (b) this @= get :+ className else this @= get.filterNot(_ == className)
    )
  }

  override def css: CSSStyleDeclaration = element.style

  lazy val title: Prop[String] = new Prop[String](element.title, element.title_=)

  lazy val measure: Trigger = Trigger()

  /**
    * Returns the absolute bounding rectangle for this element
    */
  def absoluteBounding: Rectangle = {
    val r = element.getBoundingClientRect()
    Rectangle(x = r.left, y = r.top, width = r.width, height = r.height)
  }
}

object Component {
  def width(component: Component): Option[Val[Double]] = WidthFeature(component)
  def height(component: Component): Option[Val[Double]] = HeightFeature(component)
}