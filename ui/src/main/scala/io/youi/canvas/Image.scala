package io.youi.canvas
import com.outr.pixijs._

class Image extends Component {
  override protected[canvas] val displayObject: PIXI.Sprite = new PIXI.Sprite(Texture.Empty.instance)
}