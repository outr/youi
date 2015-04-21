package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Display(val value: String) extends EnumEntryAttributeValue

object Display extends Enumerated[Display] with EnumEntryPersistence[Display] {
  case object ListItem extends Display("list-item")
  case object Inline extends Display("inline")
  case object TableRowGroup extends Display("table-row-group")
  case object TableCell extends Display("table-cell")
  case object InlineBlock extends Display("inline-block")
  case object TableCaption extends Display("table-caption")
  case object TableColumn extends Display("table-column")
  case object TableRow extends Display("table-row")
  case object Inherit extends Display("inherit")
  case object Table extends Display("table")
  case object TableFooterGroup extends Display("table-footer-group")
  case object InlineTable extends Display("inline-table")
  case object None extends Display("none")
  case object TableColumnGroup extends Display("table-column-group")
  case object Block extends Display("block")
  case object TableHeaderGroup extends Display("table-header-group")

  val values = findValues.toVector
}