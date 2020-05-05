package io.youi.component.feature

import io.youi.Color
import io.youi.component.Component
import io.youi.component.types._
import io.youi.paint.Paint
import org.scalajs.dom.raw.CSSStyleDeclaration

import scala.reflect.ClassTag
import scala.util.Try

trait FeatureParent {
  private var features = Map.empty[String, Feature]
  def css: CSSStyleDeclaration

  lazy val color: Prop[Color] = Prop.stringify(css.color, css.color_=, Color, Color.Clear)
  lazy val background: Prop[Paint] = Prop.stringify(css.background, css.background_=, Paint, Paint.none)
  lazy val backgroundColor: Prop[Color] = Prop.stringify(css.backgroundColor, css.backgroundColor_=, Color, Color.Clear)
  lazy val clear: Prop[Clear] = Prop.stringify(css.clear, css.clear_=, Clear, Clear.None)
  lazy val cursor: Prop[Cursor] = Prop.stringify(css.cursor, css.cursor_=, Cursor, Cursor.Default)
  lazy val display: Prop[Display] = Prop.stringify(css.display, css.display_=, Display, Display.Inherit, measureComponent)
  lazy val floatStyle: Prop[FloatStyle] = Prop.stringify(css.cssFloat, css.cssFloat_=, FloatStyle, FloatStyle.None)
  lazy val opacity: Prop[Double] = new Prop[Double](Try(css.opacity.toDouble).getOrElse(1.0), d => css.opacity = d.toString)
  lazy val pointerEvents: Prop[PointerEvents] = Prop.stringify(css.pointerEvents, css.pointerEvents_=, PointerEvents, PointerEvents.Unset)
  lazy val rotation: Prop[Double] = new Prop[Double](0.0, d => {
    val s = if (d != 0.0) {
      s"rotate(${d * 360.0}deg)"
    } else {
      ""
    }
    css.transform = s
  })
  lazy val userSelect: Prop[UserSelect] = Prop.stringify(css.getPropertyValue("user-select"), css.setProperty("user-select", _), UserSelect, UserSelect.Initial)
  lazy val verticalAlign: Prop[VerticalAlign] = Prop.stringify(css.verticalAlign, css.verticalAlign_=, VerticalAlign, VerticalAlign.Unset)
  lazy val visibility: Prop[Visibility] = Prop.stringify(css.visibility, css.visibility_=, Visibility, Visibility.Unset)
  lazy val whiteSpace: Prop[WhiteSpace] = Prop.stringify(css.whiteSpace, css.whiteSpace_=, WhiteSpace, WhiteSpace.Normal, measureComponent)

  def measureComponent(): Unit = this match {
    case c: Component => c.measure.trigger()
    case _ => // Theme
  }
}

object FeatureParent {
  def has[F <: Feature](parent: FeatureParent)(implicit tag: ClassTag[F]): Boolean = parent.features.contains(Feature.nameFor[F](tag))
  def add[F <: Feature](parent: FeatureParent, feature: F): F = {
    add[F](Feature.nameFor[F](feature), parent, feature)
  }
  def add[F <: Feature](name: String, parent: FeatureParent, feature: F): F = {
    parent.features += name -> feature
    feature
  }
  def get[F <: Feature](parent: FeatureParent)(implicit tag: ClassTag[F]): Option[F] = {
    get[F](Feature.nameFor[F](tag), parent)
  }
  def get[F <: Feature](name: String, parent: FeatureParent): Option[F] = {
    parent.features.get(name).asInstanceOf[Option[F]]
  }
  def apply[F <: Feature](name: String, parent: FeatureParent, create: => F): F = get[F](name, parent) match {
    case Some(f) => f
    case None => add[F](name, parent, create)
  }
  def apply[F <: Feature](parent: FeatureParent, create: => F)(implicit tag: ClassTag[F]): F = get[F](parent) match {
    case Some(f) => f
    case None => add[F](parent, create)
  }
}