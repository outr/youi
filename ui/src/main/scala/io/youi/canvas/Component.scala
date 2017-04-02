package io.youi.canvas

import com.outr.pixijs.PIXI.DisplayObject
import io.youi.AbstractComponent
import reactify.Var

trait Component extends AbstractComponent {
  protected[canvas] val displayObject: DisplayObject

  connect(position.x, displayObject.position.x, (v: Double) => displayObject.position.x = v)
  connect(position.y, displayObject.position.y, (v: Double) => displayObject.position.y = v)

  protected[youi] def prop[T](get: => T, set: T => Unit): Var[T] = {
    val v = Var[T](get)
    v.attach(set)
    v
  }

  protected[youi] def connect[T](v: Var[T], get: => T, set: T => Unit): Unit = {
    v := get
    v.attach(set)
  }
}