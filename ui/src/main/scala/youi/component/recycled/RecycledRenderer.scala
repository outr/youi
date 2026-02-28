package youi.component.recycled

import youi.component.Component
import youi.component.types.Display

trait RecycledRenderer[T, C <: Component] {
  def createComponent: C
  def setData(data: T, component: C): Unit
  def getData(component: C): T
  def loading(component: C): Unit
  def show(component: C): Unit = component.display @= Display.Block
  def hide(component: C): Unit = component.display @= Display.None
}
