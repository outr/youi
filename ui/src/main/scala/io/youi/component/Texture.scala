package io.youi.component

import com.outr.pixijs._
import io.youi.History
import io.youi.net.URL
import org.scalajs.dom.html
import reactify.{Val, Var}

import scala.scalajs.js.|

class Texture(val instance: PIXI.Texture,
              val dependsOn: Option[Texture] = None,
              val clip: Option[Clip] = None,
              val rotation: Double = 0.0) {
  val update: Val[Long] = Var(0L)
  val width: Val[Double] = Var(instance.width)
  val height: Val[Double] = Var(instance.height)
  def source: html.Image | html.Canvas = instance.baseTexture.source
  def loaded: Boolean = width() > 0.0 && height() > 0.0

  object clipped {
    def fromSides(left: Double = 0.0, top: Double = 0.0, right: Double = 0.0, bottom: Double = 0.0): Texture = {
      val rectangle = new PIXI.Rectangle(left, top, width - (left + right), height - (top + bottom))
      new Texture(
        instance = new PIXI.Texture(instance.baseTexture, rectangle),
        dependsOn = Some(Texture.this),
        clip = Some(Clip(left, top, right, bottom))
      )
    }
    def fromLeft(width: Double): Texture = {
      val right = math.max(Texture.this.width - width, 0.0)
      fromSides(right = right)
    }
    def fromRight(width: Double): Texture = {
      val left = math.max(Texture.this.width - width, 0.0)
      fromSides(left = left)
    }
    def fromTop(height: Double): Texture = {
      val bottom = math.max(Texture.this.height - height, 0.0)
      fromSides(bottom = bottom)
    }
    def fromBottom(height: Double): Texture = {
      val top = math.max(Texture.this.height - height, 0.0)
      fromSides(top = top)
    }
    def fromPoints(x1: Double, y1: Double, x2: Double, y2: Double): Texture = {
      val right = width - x2
      val bottom = height - y2
      fromSides(x1, y1, right, bottom)
    }
  }

  def rotated(value: Double): Texture = new Texture(
    instance = new PIXI.Texture(instance.baseTexture, rotate = value),
    dependsOn = Some(Texture.this),
    rotation = value
  )

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
  def apply(path: String): Texture = apply(History.url().withPart(path))
  def apply(video: html.Video): Texture = new Texture(PIXI.Texture.from(video))
  def apply(image: html.Image): Texture = new Texture(PIXI.Texture.from(image))
}

case class Clip(left: Double, top: Double, right: Double, bottom: Double)

sealed trait TextureType