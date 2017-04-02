package io.youi.canvas

import com.outr.pixijs._
import io.youi.AbstractContainer

class Container extends Component with AbstractContainer[Component] {
  override protected[canvas] lazy val displayObject: PIXI.Container = new PIXI.Container()

//  connect(size.width, displayObject.width, (d: Double) => displayObject.width = d)
//  connect(size.height, displayObject.height, (d: Double) => displayObject.height = d)

  override protected def addAfter(c: Component, previous: Option[Component]): Unit = previous match {
    case Some(p) => {
      val index = displayObject.getChildIndex(p.displayObject)
      displayObject.addChildAt(c.displayObject, index + 1)
    }
    case None => displayObject.addChild(c.displayObject)
  }

  override protected def remove(c: Component): Unit = displayObject.removeChild(c.displayObject)
}