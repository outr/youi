package org.hyperscala.css.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Display(val value: String) extends EnumEntry[Display] with AttributeValue

object Display extends Enumerated[Display] with EnumEntryPersistence[Display] {
  val ListItem = new Display("list-item")
  val Inline = new Display("inline")
  val TableRowGroup = new Display("table-row-group")
  val TableCell = new Display("table-cell")
  val InlineBlock = new Display("inline-block")
  val TableCaption = new Display("table-caption")
  val TableColumn = new Display("table-column")
  val TableRow = new Display("table-row")
  val Inherit = new Display("inherit")
  val Table = new Display("table")
  val TableFooterGroup = new Display("table-footer-group")
  val InlineTable = new Display("inline-table")
  val None = new Display("none")
  val TableColumnGroup = new Display("table-column-group")
  val Block = new Display("block")
  val TableHeaderGroup = new Display("table-header-group")
}