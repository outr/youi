package org.hyperscala.bootstrap.component.extra

import org.hyperscala.html._
import org.hyperscala.bootstrap.component._
import org.hyperscala.html.constraints.BodyChild

object NavBarFactory {
  case class Link(url: String, text: String, active: Boolean = false) {
    private val that = this
    def li = new ListItem {
      active := that.active
      contents += new tag.A(href = url, content = text)
    }
  }

  def apply(brand: BodyChild,
            theme: NavBarTheme,
            links: Seq[NavBarFactory.Link],
            dropdown: Option[NavBarDropdown] = None,
            brandUrl: String = "#") = {
    val thatTheme = theme

    new NavBar {
      theme := thatTheme
      top := true

      val navigation = new NavBarNav {
        pullRight := true

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
        }

        contents += new NavbarBrand {
          href := brandUrl
          contents += brand
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
