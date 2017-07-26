package io.youi.component

import com.outr.pixijs.PIXI
import io.youi.theme.GraphicsTheme
import reactify.Var

class Graphics extends Component {
  override lazy val theme: Var[GraphicsTheme] = Var(Graphics)
  override protected[component] lazy val instance: PIXI.Graphics = new PIXI.Graphics

  def drawable: PIXI.Graphics = instance
}

object Graphics extends GraphicsTheme