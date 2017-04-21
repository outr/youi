package io.youi.hypertext.layout

import io.youi.hypertext.{AbstractComponent, AbstractContainer}

class GridLayout(columns: Int,
                 xOffset: Double = 0.0,
                 yOffset: Double = 0.0,
                 verticalPadding: Double = 0.0,
                 horizontalPadding: Double = 0.0) extends BasicLayout {
  override protected def layout(parent: AbstractContainer[AbstractComponent], children: Vector[AbstractComponent]): Unit = {
    val grid = children.grouped(columns).toVector
    grid.zipWithIndex.foreach {
      case (row, rowIndex) => row.zipWithIndex.foreach {
        case (component, columnIndex) => {
          component.position.left := (if (columnIndex == 0) {
            xOffset
          } else {
            grid.map(r => if (r.length >= columnIndex) r(columnIndex - 1).position.right() else 0.0).max + horizontalPadding
          })
          component.position.top := (if (rowIndex == 0) {
            yOffset
          } else {
            grid(rowIndex - 1).map(_.position.bottom()).max + verticalPadding
          })
        }
      }
    }
  }
}
