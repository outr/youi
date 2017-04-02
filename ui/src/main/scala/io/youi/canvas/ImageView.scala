package io.youi.canvas
import com.outr.pixijs._
import reactify.Var

class ImageView extends Component {
  def this(texture: Texture) = {
    this()
    this.texture := texture
  }

  override protected[canvas] lazy val displayObject: PIXI.Sprite = new PIXI.Sprite(Texture.Empty.instance)

  lazy val texture: Var[Texture] = prop(new Texture(displayObject.texture), (t: Texture) => displayObject.texture = t.instance)

  object anchor {
    lazy val x: Var[Double] = prop(displayObject.anchor.x, displayObject.anchor.x = _)
    lazy val y: Var[Double] = prop(displayObject.anchor.y, displayObject.anchor.y = _)
  }
}