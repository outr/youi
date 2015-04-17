package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.{ClassProperty, ClassName}
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TableRow extends tag.Tr {
  def this(rowStyle: RowStyle) = {
    this()
    this.rowStyle := rowStyle
  }

  val rowStyle = new ClassProperty[RowStyle](this, RowStyle.Default)
}

sealed abstract class RowStyle(val className: Option[String]) extends EnumEntry with ClassName

object RowStyle extends Enumerated[RowStyle] {
  case object Default extends RowStyle(Option("default"))
  case object Active extends RowStyle(Option("active"))
  case object Success extends RowStyle(Option("success"))
  case object Info extends RowStyle(Option("info"))
  case object Warning extends RowStyle(Option("warning"))
  case object Danger extends RowStyle(Option("danger"))

  val values = findValues.toVector
}