package org.hyperscala.bootstrap.component.extra

import org.hyperscala.html._
import org.hyperscala.bootstrap.component._
import org.hyperscala.html.constraints.BodyChild

object NavBarFactory {
  case class Link(url: String, text: String, active: Boolean = true) {
    private val that = this
    def li = new ListItem {
      contents += new tag.A(href = url, content = text)
      active := that.active
    }
  }

  def apply(brand: BodyChild,
            theme: NavBarTheme,
            links: Seq[NavBarFactory.Link],
            dropdown: Option[NavBarDropdown] = None) = {
    val thatTheme = theme

    new NavBar {
      theme := thatTheme

      val navigation = new NavBarNav {
        links.foreach { link =>
          contents += link.li
        }

        dropdown.foreach { dd =>
          contents += dd
        }
      }

      val header = new NavBarHeader {
        contents += new NavBarToggle {
          contents += new tag.Span(clazz = List("sr-only")) {
            contents += "Toggle navigation"
          }
          contents += new tag.Span(clazz = List("icon-bar"))
          contents += new tag.Span(clazz = List("icon-bar"))
          contents += new tag.Span(clazz = List("icon-bar"))
          contents += new NavbarBrand {
            href := "#"
            contents += brand
          }
        }
      }

      val bar = new NavBarCollapse {
        contents += navigation
      }

      contents += new Container {
        contents += header
        contents += bar
      }
    }
  }
}
