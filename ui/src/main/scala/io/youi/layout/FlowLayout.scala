package io.youi.layout
import io.youi.{AbstractComponent, AbstractContainer}

class FlowLayout(xOffset: Double = 0.0,
                 yOffset: Double = 0.0,
                 horizontalPadding: Double = 0.0,
                 verticalPadding: Double = 0.0) extends BasicLayout {
  override protected def layout(parent: AbstractContainer[AbstractComponent],
                                children: Vector[AbstractComponent]): Unit = {
    val maxWidth = children.map(_.size.width()).max
    val maxHeight = children.map(_.size.height()).max
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