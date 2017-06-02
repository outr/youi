package io.youi.component
import com.outr.pixijs.PIXI
import io.youi.theme.ImageTheme
import reactify.Var

class Image extends Component {
  override lazy val theme: Var[_ <: ImageTheme] = Var(Image)

  def this(texture: Texture) = {
    this()
    this.texture := texture
  }

  override protected[component] lazy val instance: PIXI.Sprite = new PIXI.Sprite(Texture.Empty.instance)

  lazy val texture: Var[Texture] = prop(new Texture(instance.texture), (t: Texture) => instance.texture = t.instance, updatesRendering = true)

  // TODO: fire invalidate when texture.update is triggered

  size.measured.width := texture().width()
  size.measured.height := texture().height()

  // Configure auto-scaling to size
  scale.x := size.width() / size.measured.width()
  scale.y := size.height() / size.measured.height()
}

object Image extends ImageTheme