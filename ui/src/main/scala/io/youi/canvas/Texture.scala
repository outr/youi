package io.youi.canvas

import com.outr.pixijs._
import io.youi.net.URL

class Texture(private[canvas] val instance: PIXI.Texture)

object Texture {
  lazy val Empty: Texture = new Texture(PIXI.Texture.EMPTY)
  lazy val White: Texture = new Texture(PIXI.Texture.WHITE)

  def apply(url: URL): Texture = new Texture(PIXI.Texture.fromImage(url.toString))
}