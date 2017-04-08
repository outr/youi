package io.youi.component
import com.outr.pixijs.PIXI
import reactify.Var

class Image extends Component {
  def this(texture: Texture) = {
    this()
    this.texture := texture
  }

  override protected[component] lazy val instance: PIXI.Sprite = new PIXI.Sprite(Texture.Empty.instance)

  lazy val texture: Var[Texture] = prop(new Texture(instance.texture), (t: Texture) => instance.texture = t.instance)

  object anchor {
    lazy val x: Var[Double] = prop(instance.anchor.x, instance.anchor.x = _)
    lazy val y: Var[Double] = prop(instance.anchor.y, instance.anchor.y = _)
  }
}
