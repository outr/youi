package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.powerscala.Color
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StyleSheetExample extends Webpage {
  Webpage().require(Realtime)
  Realtime.connectStandard()

  val h1 = new tag.H1(content = "Colored Red") {
    style.color := Color.Red
    style.textDecoration := "underline"
    style.textDecoration.important := false
  }
  body.contents += h1

  body.contents += new tag.Button(content = "Change Color") {
    clickEvent.on {
      case evt => h1.style.color := Color.random
    }
  }
}
