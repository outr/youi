package io.youi.component

import com.outr.pixijs._
import io.youi.History
import io.youi.net.URL
import reactify.{Trigger, Val, Var}

class Texture(private[component] val instance: PIXI.Texture) {
  val update: Trigger = Trigger()
  val width: Val[Double] = Var(instance.width)
  val height: Val[Double] = Var(instance.height)

  instance.on("update", () => {
    width.asInstanceOf[Var[Double]] := instance.width
    height.asInstanceOf[Var[Double]] := instance.height

    update.fire()
  })
}

object Texture {
  lazy val Empty: Texture = new Texture(PIXI.Texture.EMPTY)
  lazy val White: Texture = new Texture(PIXI.Texture.WHITE)

  def apply(url: URL): Texture = new Texture(PIXI.Texture.fromImage(url.toString))
  def apply(path: String): Texture = apply(History.url().withPath(path))
}