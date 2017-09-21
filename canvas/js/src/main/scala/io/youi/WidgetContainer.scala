package io.youi

import reactify._

trait WidgetContainer extends Widget {
  type Child <: Widget

  protected def childEntries: Val[Vector[Child]]
}

object WidgetContainer {
  def children(container: WidgetContainer): Val[Vector[Widget]] = container.childEntries.asInstanceOf[Val[Vector[Widget]]]
}