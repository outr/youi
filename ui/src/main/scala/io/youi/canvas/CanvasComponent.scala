package io.youi.canvas

import com.outr.pixijs.PIXI.DisplayObject
import io.youi.AbstractComponent

trait CanvasComponent extends AbstractComponent {
  protected val displayObject: DisplayObject
}