package org.hyperscala.examples.basic

import org.hyperscala.web.Webpage
import org.hyperscala.ui.module.PageTimer
import org.powerscala.{Language, Country}

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class LargePageExample extends Webpage {
  require(PageTimer)

  val table = new tag.Table

  Country.values.foreach {
    case c => table.contents += new tag.Tr {
      contents += new tag.Td(content = c.ordinal.toString)
      contents += new tag.Td(content = c.name)
      contents += new tag.Td(content = c.fullName)
    }
  }
  Language.values.foreach {
    case l => table.contents += new tag.Tr {
      contents += new tag.Td(content = l.ordinal.toString)
      contents += new tag.Td(content = l.name)
    }
  }

  body.contents += table
}
