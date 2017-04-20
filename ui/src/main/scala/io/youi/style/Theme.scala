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
  val interactive: Var[Boolean] = Var(parent.map(_.interactive()).getOrElse(true))
  val visible: Var[Boolean] = Var(parent.map(_.visible()).getOrElse(true))
}

object Theme extends Theme