package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.powerscala.Color
import org.hyperscala.realtime.Realtime
import org.hyperscala.selector._
import org.hyperscala.selector.Selector._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StyleSheetExample extends Webpage {
  Webpage().require(Realtime)
  Realtime.connectStandard()

  val allStyle = head.selector(all)
  allStyle.fontFamily := "sans-serif"

  val buttonStyle = head.selector(element[tag.Button])
  buttonStyle.color := Color.Blue
  buttonStyle.fontSize := 18.pt

  val h1HoverStyle = head.selector(pseudo(element[tag.H1], PseudoClass.hover))
  h1HoverStyle.fontSize := 28.pt

  val h1 = new tag.H1(content = "Colored Red") {
    style.color := Color.Red
    style.textDecoration := "underline"
    style.textDecoration.important := false
  }
  body.contents += h1

  val b = new tag.Button(content = "Change Color") {
    clickEvent.on {
      case evt => {
        h1.style.color := Color.random
        buttonStyle.color := Color.random
      }
    }
  }
  body.contents += b

  val idStyle = head.selector(b)
  idStyle.backgroundColor := Color.Green
}
