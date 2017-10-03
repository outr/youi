package io.youi.layout

import io.youi.{Widget, WidgetContainer}

/**
  * Layouts can be defined and connected to an AbstractContainer to manage the layout of the children. By default
  * Layout.None is defined and each child is responsible for its own positioning and sizing.
  */
trait Layout {
  def connect(container: WidgetContainer): Unit

  def disconnect(container: WidgetContainer): Unit

  def resized(container: WidgetContainer, width: Double, height: Double): Unit = {}

  def childrenChanged(container: WidgetContainer, removed: Vector[Widget], added: Vector[Widget]): Unit = {}
}

object Layout {
  object None extends Layout {
    override def connect(container: WidgetContainer): Unit = {}

    override def disconnect(container: WidgetContainer): Unit = {}
  }
}