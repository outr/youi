package io.youi.component
import com.outr.pixijs.PIXI
import io.youi.style.Theme
import reactify.Var

class Image extends Component {
  def this(texture: Texture) = {
    this()
    this.texture := texture
  }

  override protected[component] lazy val instance: PIXI.Sprite = new PIXI.Sprite(Texture.Empty.instance)
  override protected def defaultTheme: Theme = Image

  lazy val texture: Var[Texture] = prop(new Texture(instance.texture), (t: Texture) => instance.texture = t.instance, updatesRendering = true)

  size.measured.width := texture().width()
  size.measured.height := texture().height()

  // Configure auto-scaling to size
  scale.x := size.width() / size.measured.width()
  scale.y := size.height() / size.measured.height()
}

object Image extends Theme(Theme)