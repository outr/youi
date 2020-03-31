package io.youi.component

import io.youi.Color
import io.youi.component.feature.{Feature, MeasuredFeature, PreferredSizeFeature, SizeFeature}
import io.youi.component.types.{Clear, Cursor, Display, FloatStyle, Prop, UserSelect, VerticalAlign, WhiteSpace}
import io.youi.paint.Paint
import io.youi.path.Rectangle
import org.scalajs.dom.html
import reactify.{Trigger, Val}

import scala.reflect.ClassTag
import scala.util.Try

class Component(val element: html.Element) {
  private var features = Map.empty[String, Feature]

  lazy val id: Prop[String] = new Prop[String](element.id, s => element.setAttribute("id", s))
  object classes extends Prop[Set[String]](
    getter = Option(element.getAttribute("class")).getOrElse("").split(' ').toSet,
    setter = values => element.setAttribute("class", values.mkString(" "))
  ) {
    def +=(className: String): Unit = this @= (this() + className)
    def -=(className: String): Unit = this @= (this() - className)
    def toggle(className: String): Prop[Boolean] = new Prop[Boolean](
      getter = get.contains(className),
      setter = b => if (b) this @= get + className else this @= get - className
    )
  }
  lazy val color: Prop[Color] = Prop.stringify(element.style.color, element.style.color_=, Color, Color.Clear)
  lazy val background: Prop[Paint] = Prop.stringify(element.style.background, element.style.background_=, Paint, Paint.none)
  lazy val backgroundColor: Prop[Color] = Prop.stringify(element.style.backgroundColor, element.style.backgroundColor_=, Color, Color.Clear)
  lazy val clear: Prop[Clear] = Prop.stringify(element.style.clear, element.style.clear_=, Clear, Clear.None)
  lazy val cursor: Prop[Cursor] = Prop.stringify(element.style.cursor, element.style.cursor_=, Cursor, Cursor.Default)
  lazy val display: Prop[Display] = Prop.stringify(element.style.display, element.style.display_=, Display, Display.Inherit, measure.trigger)
  lazy val floatStyle: Prop[FloatStyle] = Prop.stringify(element.style.cssFloat, element.style.cssFloat_=, FloatStyle, FloatStyle.None)
  lazy val opacity: Prop[Double] = new Prop[Double](Try(element.style.opacity.toDouble).getOrElse(1.0), d => element.style.opacity = d.toString)
  lazy val rotation: Prop[Double] = new Prop[Double](0.0, d => {
    val s = if (d != 0.0) {
      s"rotate(${d * 360.0}deg)"
    } else {
      ""
    }
    element.style.transform = s
  })
  lazy val title: Prop[String] = new Prop[String](element.title, element.title_=)
  lazy val userSelect: Prop[UserSelect] = Prop.stringify(element.style.getPropertyValue("user-select"), element.style.setProperty("user-select", _), UserSelect, UserSelect.Initial)
  lazy val verticalAlign: Prop[VerticalAlign] = Prop.stringify(element.style.verticalAlign, element.style.verticalAlign_=, VerticalAlign, VerticalAlign.Unset)
  lazy val whiteSpace: Prop[WhiteSpace] = Prop.stringify(element.style.whiteSpace, element.style.whiteSpace_=, WhiteSpace, WhiteSpace.Normal, measure.trigger)

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
  def hasFeature[F <: Feature](component: Component)(implicit tag: ClassTag[F]): Boolean = component.features.contains(Feature.nameFor[F](tag))
  def addFeature[F <: Feature](component: Component, feature: F): Unit = {
    component.features += Feature.nameFor[F](feature) -> feature
  }
  def getFeature[F <: Feature](component: Component)(implicit tag: ClassTag[F]): Option[F] = {
    component.features.get(Feature.nameFor[F](tag)).asInstanceOf[Option[F]]
  }
  def width(component: Component): Option[Val[Double]] = getFeature[PreferredSizeFeature](component).map(_.width)
      .orElse(getFeature[MeasuredFeature](component).map(_.width))
      .orElse(getFeature[SizeFeature](component).map(_.width))
  def height(component: Component): Option[Val[Double]] = getFeature[PreferredSizeFeature](component).map(_.height)
    .orElse(getFeature[MeasuredFeature](component).map(_.height))
    .orElse(getFeature[SizeFeature](component).map(_.height))
}