package io.youi.component.bootstrap

import io.youi.component.{Component, Container}
import io.youi.dom
import io.youi.theme.Theme
import org.scalajs.dom.html

class AccordionEntry(accordion: Accordion,
                     header: Component,
                     body: Component,
                     element: html.Element = dom.create[html.Div]("div")) extends Card(element) {
  override protected def defaultParentTheme: Theme = Container
  override def componentType: String = "bootstrap.AccordionEntry"

  override protected def init(): Unit = {
    super.init()

    val collapsible = new Container {
      classList += "collapse"
      data("parent") := s"#${accordion.id()}"
      children += new CardBody {
        children += body
      }
    }

    val cardHeader = new CardHeader {
      children += new Button {
        `type` @= ButtonType.Link
        data("toggle") @= "collapse"
        data("target") @= s"#${collapsible.id()}"
        aria("expanded") @= "false"
        aria("controls") @= collapsible.id()
        children += header
      }
    }

    collapsible.aria("labelledby") @= cardHeader.id()

    children += cardHeader
    children += collapsible
  }
}