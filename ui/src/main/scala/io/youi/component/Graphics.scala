package io.youi.component
import com.outr.pixijs.PIXI
import io.youi.style.Theme

class Graphics extends Component {
  override protected[component] lazy val instance: PIXI.Graphics = new PIXI.Graphics
  override protected def defaultTheme: Theme = Graphics

  def drawable: PIXI.Graphics = instance
}

object Graphics extends Theme(Theme)