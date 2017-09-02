package io.youi.layout

import io.youi.component.{AbstractContainer, Component}
import io.youi.{Horizontal, Vertical}
import reactify.Var

import scala.collection.mutable.ListBuffer

class GridLayout extends Layout {
  val columns: Var[Int] = Var(1)

  object config {
    val default: Config = new Config {
      override def parents: List[Config] = Nil
    }
    val rows: Var[Map[Int, RowConfig]] = Var(Map.empty)
    val columns: Var[Map[Int, ColumnConfig]] = Var(Map.empty)

    def row(rowIndex: Int): RowConfig = rows().get(rowIndex) match {
      case Some(c) => c
      case None => {
        val c = new RowConfig(rowIndex)
        rows.static(rows() + (rowIndex -> c))
        c
      }
    }

    def column(columnIndex: Int): ColumnConfig = columns().get(columnIndex) match {
      case Some(c) => c
      case None => {
        val c = new ColumnConfig(columnIndex)
        columns.static(columns() + (columnIndex -> c))
        c
      }
    }

    def cell(row: Int, column: Int): CellConfig = this.row(row).cell(column)

    def hasRow(rowIndex: Int): Boolean = rows().contains(rowIndex)
    def hasColumn(columnIndex: Int): Boolean = columns().contains(columnIndex)
  }

  override def connect(container: AbstractContainer): Unit = update(container)

  override def disconnect(container: AbstractContainer): Unit = {}

  override def childrenChanged(container: AbstractContainer, removed: Vector[Component], added: Vector[Component]): Unit = {
    super.childrenChanged(container, removed, added)
    update(container)
  }

  def update(container: AbstractContainer): Unit = {
    val columnCount = this.columns()
    var rowIndex = 0
    var columnIndex = 0
    var rowSpans = Map.empty[Int, Int]
    var configs = Map.empty[Component, CellConfig]
    var columnsMap = Map.empty[Int, ListBuffer[Component]]
    var rowsMap = Map.empty[Int, ListBuffer[Component]]
    def layout(child: Component): Unit = {
      if (rowSpans.contains(columnIndex)) {
        val newValue = rowSpans(columnIndex) - 1
        if (newValue == 0) {
          rowSpans -= columnIndex
        } else {
          rowSpans += columnIndex -> newValue
        }
        columnIndex += 1
        layout(child)
      } else if (columnIndex >= columnCount) {
        columnIndex = 0
        rowIndex += 1
        layout(child)
      } else {
        val cfg = config.cell(rowIndex, columnIndex)

        // Padding
        cfg.padding.left.foreach(p => child.padding.left := p)
        cfg.padding.right.foreach(p => child.padding.right := p)
        cfg.padding.top.foreach(p => child.padding.top := p)
        cfg.padding.bottom.foreach(p => child.padding.bottom := p)

        // Spans
        val colSpan = cfg.span.columns().getOrElse(1)
        val rowSpan = cfg.span.rows().getOrElse(1)

        if (colSpan == 1) {
          val columns = columnsMap.getOrElse(columnIndex, ListBuffer.empty[Component])
          columns += child
          columnsMap += columnIndex -> columns
        }
        if (rowSpan == 1) {
          val rows = rowsMap.getOrElse(rowIndex, ListBuffer.empty[Component])
          rows += child
          rowsMap += rowIndex -> rows
        }

        configs += child -> cfg

        columnIndex += colSpan
        if (rowSpan > 1) {
          rowSpans += columnIndex -> (rowSpans.getOrElse(columnIndex, 0) + (rowSpan - 1))
        }
      }
    }
    AbstractContainer.children(container).foreach(layout)
    configs.foreach {
      case (child, cfg) => {
        // Alignment
        val horizontal = cfg.alignment.horizontal().getOrElse(Horizontal.Left)
        val vertical = cfg.alignment.vertical().getOrElse(Vertical.Top)

        val row = cfg.rowIndex
        val column = cfg.columnIndex

        def maxHeight: Double = rowsMap.getOrElse(row, ListBuffer.empty).foldLeft(0.0)((max, c) => math.max(max, c.size.height))
        def maxBottom: Double = rowsMap.getOrElse(row - 1, ListBuffer.empty).foldLeft(0.0)((max, c) => math.max(max, c.position.bottom))
        def maxWidth: Double = columnsMap.getOrElse(column, ListBuffer.empty).foldLeft(0.0)((max, c) => math.max(max, c.size.width))
        def maxRight: Double = columnsMap.getOrElse(column - 1, ListBuffer.empty).foldLeft(0.0)((max, c) => math.max(max, c.position.right))
        vertical match {
          case Vertical.Top => child.position.top := maxBottom
          case Vertical.Middle => child.position.middle := maxBottom + (maxHeight / 2.0)
          case Vertical.Bottom => child.position.bottom := maxBottom + maxHeight
        }
        horizontal match {
          case Horizontal.Left => child.position.left := maxRight
          case Horizontal.Center => child.position.center := maxRight + (maxWidth / 2.0)
          case Horizontal.Right => child.position.right := maxRight + maxWidth
        }
      }
    }
  }

  trait Config {
    def parents: List[Config]

    object padding {
      val left: Var[Option[Double]] = prop[Double](_.padding.left)
      val right: Var[Option[Double]] = prop[Double](_.padding.right)
      val top: Var[Option[Double]] = prop[Double](_.padding.top)
      val bottom: Var[Option[Double]] = prop[Double](_.padding.bottom)
    }

    object span {
      val columns: Var[Option[Int]] = prop[Int](_.span.columns)
      val rows: Var[Option[Int]] = prop[Int](_.span.rows)
    }

    object alignment {
      val horizontal: Var[Option[Horizontal]] = prop[Horizontal](_.alignment.horizontal)
      val vertical: Var[Option[Vertical]] = prop[Vertical](_.alignment.vertical)
    }

    protected def prop[T](lookup: Config => Var[Option[T]]): Var[Option[T]] = {
      Var[Option[T]](parents.map(lookup).collectFirst {
        case v if v().nonEmpty => v().get
      })
    }
  }

  class RowConfig(val rowIndex: Int) extends Config {
    val cells: Var[Map[Int, CellConfig]] = Var(Map.empty)

    val parents: List[Config] = List(config.default)

    def cell(columnIndex: Int): CellConfig = cells().get(columnIndex) match {
      case Some(c) => c
      case None => {
        val c = new CellConfig(this, config.column(columnIndex))
        cells.static(cells() + (columnIndex -> c))
        c
      }
    }
  }

  class ColumnConfig(val columnIndex: Int) extends Config {
    val parents: List[Config] = List(config.default)
  }

  class CellConfig(val row: RowConfig, val column: ColumnConfig) extends Config {
    def rowIndex: Int = row.rowIndex
    def columnIndex: Int = column.columnIndex
    val parents: List[Config] = List(column, row, config.default)
  }
}