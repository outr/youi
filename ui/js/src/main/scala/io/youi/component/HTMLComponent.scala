package io.youi.component

import com.outr.pixijs.PIXI
import io.youi._
import io.youi.theme.HTMLComponentTheme
import reactify.Var

class HTMLComponent[C <: hypertext.Component](val component: C) extends Component {
  override lazy val theme: Var[HTMLComponentTheme] = Var(HTMLComponent)
  override protected[component] lazy val instance: PIXI.Container = new PIXI.Sprite(PIXI.Texture.EMPTY)

  size.measured.width := component.size.actual.width
  size.measured.height := component.size.actual.height
  component.visible := globalVisibility

  override def update(delta: Double): Unit = {
    super.update(delta)

    if (globalVisibility()) {
      val a = instance.worldTransform.a
      val b = instance.worldTransform.b
      val c = instance.worldTransform.c
      val d = instance.worldTransform.d
      val tx = instance.worldTransform.tx
      val ty = instance.worldTransform.ty
      component.element.style.transform = s"matrix($a, $b, $c, $d, $tx, $ty)"
    }
  }
}

object HTMLComponent extends HTMLComponentTheme