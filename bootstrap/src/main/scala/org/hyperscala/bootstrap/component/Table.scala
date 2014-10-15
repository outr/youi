package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Table(options: TableOption*) extends tag.Table(clazz = List("table")) {
  options.foreach(o => clazz += o.className)

  val head = new tag.THead
  val body = new tag.TBody

  contents.addAll(head, body)

  def heading(rowStyle: RowStyle = RowStyle.Default) = {
    val r = new TableRow(heading = true, rowStyle = rowStyle)
    head.contents += r
    r
  }

  def row(rowStyle: RowStyle = RowStyle.Default) = {
    val r = new TableRow(heading = false, rowStyle = rowStyle)
    body.contents += r
    r
  }

  def currentRow = if (body.contents.isEmpty) {
    head.contents.last.asInstanceOf[TableRow]
  } else {
    body.contents.last.asInstanceOf[TableRow]
  }
}

class TableOption(val className: String) extends EnumEntry

object TableOption extends Enumerated[TableOption] {
  val Striped = new TableOption("table-striped")
  val Bordered = new TableOption("table-bordered")
  val Hover = new TableOption("table-hover")
  val Condensed = new TableOption("table-condensed")
}