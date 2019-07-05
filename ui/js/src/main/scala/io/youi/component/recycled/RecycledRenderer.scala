package io.youi.component.recycled

import io.youi.component.Component
import io.youi.style.Display

trait RecycledRenderer[T, C <: Component] {
  def createComponent: C
  def setData(data: T, component: C): Unit
  def getData(component: C): T
  def loading(component: C): Unit
  def show(component: C): Unit = component.display := Display.Block
  def hide(component: C): Unit = component.display := Display.None
}
