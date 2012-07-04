package org.hyperscala.style

import org.powerscala.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
sealed case class Display(value: String) extends EnumEntry[Display] with StyleValue

object Display extends Enumerated[Display] {
  val None = Display("none")
  val Block = Display("block")
  val Inline = Display("inline")
  val InlineBlock = Display("inline-block")
  val InlineTable = Display("inline-table")
  val ListItem = Display("list-item")
  val Table = Display("table")
  val TableCaption = Display("table-caption")
  val TableCell = Display("table-cell")
  val TableColumn = Display("table-column")
  val TableColumnGroup = Display("table-column-group")
  val TableFooterGroup = Display("table-footer-group")
  val TableHeaderGroup = Display("table-header-group")
  val TableRow = Display("table-row")
  val TableRowGroup = Display("table-row-group")
  val Inherit = Display("inherit")
}