package io.youi.canvas

import com.outr.pixijs.PIXI.DisplayObject
import io.youi.AbstractComponent

trait Component extends AbstractComponent {
  protected[canvas] val displayObject: DisplayObject
}