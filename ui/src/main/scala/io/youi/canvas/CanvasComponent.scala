package io.youi.canvas

import com.outr.pixijs.PIXI.DisplayObject
import io.youi.AbstractComponent

import scala.concurrent.Future

trait CanvasComponent extends AbstractComponent {
  protected val displayObject: Future[DisplayObject]
}