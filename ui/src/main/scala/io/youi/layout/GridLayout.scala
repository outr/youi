package io.youi.layout
import io.youi.{AbstractComponent, AbstractContainer}

class GridLayout(columns: Int,
                 xOffset: Double = 0.0,
                 yOffset: Double = 0.0,
                 verticalPadding: Double = 0.0,
                 horizontalPadding: Double = 0.0) extends Layout with (Vector[AbstractComponent] => Unit) {
  override protected def connect[C <: AbstractComponent](container: AbstractContainer[C]): Unit = {
    container.children.attachAndFire(this)
  }

  override protected def disconnect[C <: AbstractComponent](container: AbstractContainer[C]): Unit = {
    container.children.detach(this)
  }

  override def apply(children: Vector[AbstractComponent]): Unit = if (children.nonEmpty) {
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
