package io.youi

import com.outr.reactify._
import io.youi.hypertext.style.{ColorProperties, Position}

trait AbstractComponent {
  object position {
    lazy val `type`: Var[Position] = Var(Position.Absolute)
    lazy val x: Var[Double] = Var(0.0)
    lazy val y: Var[Double] = Var(0.0)

    lazy val left: Var[Double] = x
    lazy val center: Dep[Double, Double] = Dep(left, size.actual.width / 2.0)
    lazy val right: Dep[Double, Double] = Dep(left, size.actual.width)

    lazy val top: Var[Double] = y
    lazy val middle: Dep[Double, Double] = Dep(top, size.actual.height / 2.0)
    lazy val bottom: Dep[Double, Double] = Dep(top, size.actual.height)
  }

  protected val actualWidth: Var[Double] = Var(0.0)
  protected val actualHeight: Var[Double] = Var(0.0)

  object size {
    lazy val width: Var[Size] = Var(Size.Auto)
    lazy val height: Var[Size] = Var(Size.Auto)

    object actual {
      val width: Val[Double] = Val(actualWidth)
      val height: Val[Double] = Val(actualHeight)
    }
  }

  object scale extends Channel[Double] {
    lazy val x: Var[Double] = Var(1.0)
    lazy val y: Var[Double] = Var(1.0)
  }

  lazy val color: ColorProperties = new ColorProperties
  lazy val backgroundColor: ColorProperties = new ColorProperties
  lazy val rotation: Var[Double] = Var(0.0)
  lazy val opacity: Var[Double] = Var(1.0)
  lazy val visible: Var[Boolean] = Var(true)

  protected def init(): Unit = {}
}