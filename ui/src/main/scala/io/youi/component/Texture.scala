package io.youi.component

import com.outr.pixijs._
import io.youi.History
import io.youi.net.URL
import reactify.{Trigger, Val, Var}

class Texture(private[component] val instance: PIXI.Texture,
              val dependsOn: Option[Texture] = None,
              val clip: Option[Clip] = None) {
  val update: Val[Long] = Var(0L)
  val width: Val[Double] = Var(instance.width)
  val height: Val[Double] = Var(instance.height)

  def clipped(left: Double, top: Double, right: Double, bottom: Double): Texture = {
    val rectangle = new PIXI.Rectangle(left, top, width - (left + right), height - (top + bottom))
    new Texture(
      instance = new PIXI.Texture(instance.baseTexture, rectangle),
      dependsOn = Some(this),
      clip = Some(Clip(left, top, right, bottom))
    )
  }

  instance.on("update", () => {
    width.asInstanceOf[Var[Double]] := instance.width
    height.asInstanceOf[Var[Double]] := instance.height

    update.asInstanceOf[Var[Long]] := System.currentTimeMillis()
  })
}

object Texture {
  lazy val Empty: Texture = new Texture(PIXI.Texture.EMPTY)
  lazy val White: Texture = new Texture(PIXI.Texture.WHITE)

  def apply(url: URL): Texture = new Texture(PIXI.Texture.fromImage(url.toString))
  def apply(path: String): Texture = apply(History.url().withPath(path))
}

case class Clip(left: Double, top: Double, right: Double, bottom: Double)