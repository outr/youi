package org.hyperscala.examples.basic

import org.hyperscala.css.SelectorStyleSheet
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.web._
import org.powerscala.Color
import org.hyperscala.realtime._
import org.hyperscala.selector._
import org.hyperscala.css.attributes.Decoration
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StyleSheetExample extends Example {
  require(Realtime)
  connected[Webpage] {
    case webpage => webpage.connectStandard()
  }

  implicit def htmlTag: HTMLTag = this

  new SelectorStyleSheet(Selector.all) {
    fontFamily := "sans-serif"
  }

  new SelectorStyleSheet(Selector.element[tag.Button]) {
    color := Color.Blue
    fontSize := 18.pt
  }

  val colorButtonStyle = new SelectorStyleSheet(Selector.id("colorButton")) {
    color := Color.Red
  }

  val h1HoverStyle = new SelectorStyleSheet(Selector.pseudo(PseudoClass.Hover, Some(Selector.element[tag.H1]))) {
    fontSize := 28.pt
  }

  val h1 = new tag.H1(content = "Colored Red") {
    style.color := Color.Red
    style.textDecoration := Decoration.Underline
    style.textDecoration.important := false
  }
  contents += h1

  val b = new tag.Button(id = "colorButton", content = "Change Color") {
    clickEvent.on {
      case evt => {
        h1.style.color := Color.random
        if (colorButtonStyle.color() != null) {
          colorButtonStyle.color := null
        } else {
          colorButtonStyle.color := Color.random
        }
      }
    }
  }
  contents += b
  contents += new tag.Button(id = "removeButton", content = "Remove Hover Style") {
    clickEvent.on {
      case evt => this.webpage.head.deleteSelector(h1HoverStyle.selector)
    }
  }

  val idStyle = new SelectorStyleSheet(Selector.id(b)) {
    backgroundColor := Color.Green
  }
}
