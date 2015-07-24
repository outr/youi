package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.ui.module.PageTimer
import org.hyperscala.web.Webpage
import org.powerscala.{Country, Language}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class LargePageExample extends Webpage with Example {
  require(PageTimer)

  val table = new tag.Table

  Country.values.foreach {
    case c => table.contents += new tag.Tr {
      contents += new tag.Td(content = Country.values.indexOf(c).toString)
      contents += new tag.Td(content = c.name)
      contents += new tag.Td(content = c.fullName)
    }
  }
  Language.values.foreach {
    case l => table.contents += new tag.Tr {
      contents += new tag.Td(content = Language.values.indexOf(l).toString)
      contents += new tag.Td(content = l.name)
    }
  }

  body.contents += table
}
