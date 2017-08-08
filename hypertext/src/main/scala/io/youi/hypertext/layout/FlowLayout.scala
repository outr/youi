package io.youi.hypertext.layout

import io.youi.hypertext.{AbstractComponent, AbstractContainer}

class FlowLayout(xOffset: Double = 0.0,
                 yOffset: Double = 0.0,
                 horizontalPadding: Double = 0.0,
                 verticalPadding: Double = 0.0) extends BasicLayout(updateOnParentResize = true, updateOnChildResize = true) {
  override protected def layout(parent: AbstractContainer[AbstractComponent],
                                children: Vector[AbstractComponent]): Unit = {
    val maxWidth = if (children.nonEmpty) children.map(_.size.actual.width()).max else 0.0
    val maxHeight = if (children.nonEmpty) children.map(_.size.actual.height()).max else 0.0
    val columns = math.floor((parent.size.width - xOffset) / (horizontalPadding + maxWidth)).toInt
    var y = yOffset
    children.grouped(columns).foreach { row =>
      var x = xOffset
      row.foreach { child =>
        child.position.left := x
        child.position.top := y

        x += maxWidth + horizontalPadding
      }
      y += maxHeight + verticalPadding
    }
  }
}