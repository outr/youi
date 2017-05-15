package io.youi.style

import io.youi.Color
import reactify.Var

class Theme private() {
  def this(parent: Theme) = {
    this()
    this.parent := Some(parent)
  }

  val parent: Var[Option[Theme]] = Var(None)

  val breakWords: Var[Boolean] = Var(parent.map(_.breakWords()).getOrElse(false))
  object dropShadow {
    val enabled: Var[Boolean] = Var(parent.map(_.dropShadow.enabled()).getOrElse(false))
    val angle: Var[Double] = Var(parent.map(_.dropShadow.angle()).getOrElse(0.0))
    val blur: Var[Double] = Var(parent.map(_.dropShadow.blur()).getOrElse(0.0))
    val color: Var[Color] = Var(parent.map(_.dropShadow.color()).getOrElse(Color.Black))
    val distance: Var[Double] = Var(parent.map(_.dropShadow.distance()).getOrElse(0.0))
  }
  val fill: Var[Paint] = Var(parent.map(_.fill()).getOrElse(Color.Black))
  object font {
    val family: Var[String] = Var(parent.map(_.font.family()).getOrElse("Arial"))
    val size: Var[Double] = Var(parent.map(_.font.size()).getOrElse(26.0))
    val style: Var[String] = Var(parent.map(_.font.style()).getOrElse("normal"))
    val variant: Var[String] = Var(parent.map(_.font.variant()).getOrElse("normal"))
    val weight: Var[String] = Var(parent.map(_.font.weight()).getOrElse("normal"))
  }
  val letterSpacing: Var[Double] = Var(parent.map(_.letterSpacing()).getOrElse(0.0))
  val lineHeight: Var[Double] = Var(parent.map(_.lineHeight()).getOrElse(0.0))
  val lineJoin: Var[String] = Var(parent.map(_.lineJoin()).getOrElse("miter"))
  val miterLimit: Var[Double] = Var(parent.map(_.miterLimit()).getOrElse(10.0))
  val padding: Var[Double] = Var(parent.map(_.padding()).getOrElse(0.0))
  val stroke: Var[Color] = Var(parent.map(_.stroke()).getOrElse(Color.Clear))
  val strokeThickness: Var[Double] = Var(parent.map(_.strokeThickness()).getOrElse(0.0))
  val textBaseline: Var[String] = Var(parent.map(_.textBaseline()).getOrElse("alphabetic"))
  val wordWrap: Var[Boolean] = Var(parent.map(_.wordWrap()).getOrElse(false))
  val interactive: Var[Boolean] = Var(parent.map(_.interactive()).getOrElse(true))
  val visible: Var[Boolean] = Var(parent.map(_.visible()).getOrElse(true))
}

object Theme extends Theme