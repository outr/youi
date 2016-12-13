package io.youi

import com.outr.props._

trait Component {
  object position {
    lazy val x: Var[Double] = Var(0.0)
    lazy val y: Var[Double] = Var(0.0)

    lazy val left: Var[Double] = x
    lazy val center: Dep = Dep(left, size.width / 2.0)
    lazy val right: Dep = Dep(left, size.width)

    lazy val top: Var[Double] = y
    lazy val middle: Dep = Dep(top, size.height / 2.0)
    lazy val bottom: Dep = Dep(top, size.height)
  }

  object size {
    lazy val width: Var[Double] = Var(0.0)
    lazy val height: Var[Double] = Var(0.0)
  }

  object scale extends Channel[Double] {
    lazy val x: Var[Double] = Var(1.0)
    lazy val y: Var[Double] = Var(1.0)

    override def set(value: => Double): Unit = {
      super.set(value)

      x := value
      y := value
    }
  }

  lazy val rotation: Var[Double] = Var(0.0)
  lazy val opacity: Var[Double] = Var(1.0)
  lazy val visible: Var[Boolean] = Var(true)
}