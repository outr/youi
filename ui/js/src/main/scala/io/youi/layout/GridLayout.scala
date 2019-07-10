package io.youi.layout

import io.youi.component.Component
import io.youi.{Horizontal, Vertical}
import reactify._

class GridLayout extends Layout {
  val columns: Var[Int] = Var(1)

  columns.on(updateAll())

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

  private var containers: Set[Component] = Set.empty

  override def connect(container: Component): Unit = {
    containers += container
    update(container)
  }

  override def disconnect(container: Component): Unit = {
    containers -= container
  }

  override def childrenChanged(container: Component, removed: Vector[Component], added: Vector[Component]): Unit = {
    super.childrenChanged(container, removed, added)
    update(container)
  }

  override def resized(container: Component, width: Double, height: Double): Unit = {
    super.resized(container, width, height)
    update(container)
  }

  def updateAll(): Unit = containers.foreach(update)

  def update(container: Component): Unit = {
    val columnCount = this.columns()
    var rowIndex = 0
    var columnIndex = 0
    var rowSpans = Map.empty[Int, Int]
    var configs = Map.empty[Component, CellConfig]
    var maxWidths = Map.empty[Int, Double]
    var maxHeights = Map.empty[Int, Double]
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

        val width = child.size.width + cfg.margin.left.getOrElse(0.0) + cfg.margin.right.getOrElse(0.0)
        val height = child.size.height + cfg.margin.top.getOrElse(0.0) + cfg.margin.bottom.getOrElse(0.0)

        // Spans
        val colSpan = cfg.span.columns().getOrElse(1)
        val rowSpan = cfg.span.rows().getOrElse(1)

        if (colSpan == 1) {
          maxWidths += columnIndex -> math.max(maxWidths.getOrElse(columnIndex, 0.0), width)
        }
        if (rowSpan == 1) {
          maxHeights += rowIndex -> math.max(maxHeights.getOrElse(rowIndex, 0.0), height)
        }

        configs += child -> cfg

        columnIndex += colSpan
        if (rowSpan > 1) {
          rowSpans += columnIndex -> (rowSpans.getOrElse(columnIndex, 0) + (rowSpan - 1))
        }
      }
    }
    Component.childrenFor(container).filter(c => c.visible() && c.includeInLayout()).foreach(layout)
    configs.foreach {
      case (child, cfg) => {
        // Alignment
        val horizontal = cfg.alignment.horizontal().getOrElse(Horizontal.Left)
        val vertical = cfg.alignment.vertical().getOrElse(Vertical.Top)

        val row = cfg.rowIndex
        val column = cfg.columnIndex
        val rowSpan = cfg.span.rows.getOrElse(1)
        val colSpan = cfg.span.columns.getOrElse(1)

        val offsetX: Double = (0 until column).foldLeft(0.0)((total, index) => maxWidths.getOrElse(index, 0.0) + total) + cfg.margin.left.getOrElse(0.0)
        val offsetY: Double = (0 until row).foldLeft(0.0)((total, index) => maxHeights.getOrElse(index, 0.0) + total) + cfg.margin.top.getOrElse(0.0)
        val maxWidth: Double = (column until column + colSpan).foldLeft(0.0)((total, columnIndex) => maxWidths.getOrElse(columnIndex, 0.0) + total)
        val maxHeight: Double = (row until row + rowSpan).foldLeft(0.0)((total, rowIndex) => maxHeights.getOrElse(rowIndex, 0.0) + total)
        vertical match {
          case Vertical.Top => child.position.top := offsetY
          case Vertical.Middle => child.position.middle := offsetY + (maxHeight / 2.0)
          case Vertical.Bottom => child.position.bottom := offsetY + maxHeight
        }
        horizontal match {
          case Horizontal.Left => child.position.left := offsetX
          case Horizontal.Center => child.position.center := offsetX + (maxWidth / 2.0)
          case Horizontal.Right => child.position.right := offsetX + maxWidth
        }
      }
    }
  }

  trait Config {
    def parents: List[Config]

    object margin {
      val left: Var[Option[Double]] = prop[Double](_.margin.left)
      val right: Var[Option[Double]] = prop[Double](_.margin.right)
      val top: Var[Option[Double]] = prop[Double](_.margin.top)
      val bottom: Var[Option[Double]] = prop[Double](_.margin.bottom)
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
      val v = Var[Option[T]](parents.map(lookup).collectFirst {
        case v if v().nonEmpty => v().get
      })
      v.on(updateAll())
      v
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