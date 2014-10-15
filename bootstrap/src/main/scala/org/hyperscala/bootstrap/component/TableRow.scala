package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.tag.Text

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TableRow(heading: Boolean = false, rowStyle: RowStyle = RowStyle.Default) extends tag.Tr(clazz = List(rowStyle.className)) {
  def add[T <: BodyChild](t: T, colSpan: Int = 1) = {
    val cs = if (colSpan != 1) colSpan.toString else null
    val cell = if (heading) new tag.Th(colSpan = cs) else new tag.Td(colSpan = cs)
    cell.contents += t
    contents += cell
    cell
  }

  def addText(text: String, colSpan: Int = 1) = add(new Text(text), colSpan)
}

class RowStyle(val className: String) extends EnumEntry

object RowStyle extends Enumerated[RowStyle] {
  val Default = new RowStyle("default")
  val Active = new RowStyle("active")
  val Success = new RowStyle("success")
  val Info = new RowStyle("info")
  val Warning = new RowStyle("warning")
  val Danger = new RowStyle("danger")
}