package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Column(widths: ColumnWidth*) extends tag.Div(clazz = widths.map(cw => cw.classNames).flatten)

class ColumnType private(val classPre: String) extends EnumEntry {
  def apply(columns: Int, offset: Int = 0) = ColumnWidth(columns, this, offset)
}

object ColumnType extends Enumerated[ColumnType] {
  val Large = new ColumnType("col-lg-")
  val Medium = new ColumnType("col-md-")
  val Small = new ColumnType("col-sm-")
  val ExtraSmall = new ColumnType("col-xs-")
}

case class ColumnWidth(columns: Int, columnType: ColumnType, offset: Int) {
  if (columns < 1) throw new RuntimeException(s"Columns value must be 1 or greater. Value supplied is $columns")
  if (columns > 12) throw new RuntimeException(s"Columns value must be 12 or less. Value supplied is $columns")
  if (offset < 0) throw new RuntimeException(s"Offset value must be 0 or greater. Value supplied is $offset")
  if (offset > 11) throw new RuntimeException(s"Offset value must be 11 or less. Value supplied is $offset")

  def classNames = {
    val columnClass = s"${columnType.classPre}$columns"
    val columnOffset = s"${columnType.classPre}offset-$offset"
    List(columnClass, columnOffset)
  }
}