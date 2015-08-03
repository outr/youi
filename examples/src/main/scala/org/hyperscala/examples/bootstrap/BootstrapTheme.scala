package org.hyperscala.examples.bootstrap

import com.outr.net.http.session.{MapSession, Session}
import org.hyperscala.bootstrap.Bootstrap
import org.hyperscala.bootstrap.component._
import org.hyperscala.bootstrap.component.extra._
import org.hyperscala.css.attributes.{Display, Position}
import org.hyperscala.html._
import org.hyperscala.selector.Selector
import org.hyperscala.web.{Website, Webpage}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BootstrapTheme extends Webpage {
  require(Bootstrap)

  body.role := "document"

  val bodyStyle = head.selector(Selector.element[tag.Body])
  bodyStyle.paddingTop := 70.px
  bodyStyle.paddingBottom := 30.px

  val dropdownMenuStyle = head.selector(Selector(".theme-dropdown .dropdown-menu"))
  dropdownMenuStyle.position := Position.Static
  dropdownMenuStyle.display := Display.Block
  dropdownMenuStyle.marginBottom := 20.px

  val showcaseStyle = head.selector(Selector(".theme-showcase > p > .btn"))
  showcaseStyle.margin := "5px 0"

  body.contents += NavBarFactory(
    brand = "Bootstrap theme",

    theme = NavBarTheme.Inverse,

    links = Seq(
      NavBarFactory.Link("#", "Home", active = true),
      NavBarFactory.Link("#", "About"),
      NavBarFactory.Link("#", "Contact")
    ),

    dropdown = Some(new NavBarDropdown(
      NavBarFactory.Link("#", "Action", active = true).li,
      NavBarFactory.Link("#", "Another action").li,
      NavBarFactory.Link("#", "Something else here").li,
      new Divider,
      new DropdownHeader {
        contents += "Nav header"
      },
      NavBarFactory.Link("#", "Sepated link").li,
      NavBarFactory.Link("#", "One more separated link").li
    ))
  )

  body.contents += new Container {
    clazz += "theme-showcase"
    role := "main"

    contents += new Jumbotron {
      contents += new tag.H1 {
        contents += "Hello, world!"
      }
      contents += new tag.P {
        contents += "This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique."
      }
      contents += new tag.P {
        contents += new Button("Learn more »", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.Large)
      }
    }

    contents += new PageTitle("Buttons")
    contents += new tag.P {
      contents += new Button("Default", buttonStyle = ButtonStyle.Default, buttonSize = ButtonSize.Large)
      contents += new Button("Primary", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.Large)
      contents += new Button("Success", buttonStyle = ButtonStyle.Success, buttonSize = ButtonSize.Large)
      contents += new Button("Info", buttonStyle = ButtonStyle.Info, buttonSize = ButtonSize.Large)
      contents += new Button("Warning", buttonStyle = ButtonStyle.Warning, buttonSize = ButtonSize.Large)
      contents += new Button("Danger", buttonStyle = ButtonStyle.Danger, buttonSize = ButtonSize.Large)
      contents += new Button("Link", buttonStyle = ButtonStyle.Link, buttonSize = ButtonSize.Large)
    }
    contents += new tag.P {
      contents += new Button("Default", buttonStyle = ButtonStyle.Default, buttonSize = ButtonSize.Default)
      contents += new Button("Primary", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.Default)
      contents += new Button("Success", buttonStyle = ButtonStyle.Success, buttonSize = ButtonSize.Default)
      contents += new Button("Info", buttonStyle = ButtonStyle.Info, buttonSize = ButtonSize.Default)
      contents += new Button("Warning", buttonStyle = ButtonStyle.Warning, buttonSize = ButtonSize.Default)
      contents += new Button("Danger", buttonStyle = ButtonStyle.Danger, buttonSize = ButtonSize.Default)
      contents += new Button("Link", buttonStyle = ButtonStyle.Link, buttonSize = ButtonSize.Default)
    }
    contents += new tag.P {
      contents += new Button("Default", buttonStyle = ButtonStyle.Default, buttonSize = ButtonSize.Small)
      contents += new Button("Primary", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.Small)
      contents += new Button("Success", buttonStyle = ButtonStyle.Success, buttonSize = ButtonSize.Small)
      contents += new Button("Info", buttonStyle = ButtonStyle.Info, buttonSize = ButtonSize.Small)
      contents += new Button("Warning", buttonStyle = ButtonStyle.Warning, buttonSize = ButtonSize.Small)
      contents += new Button("Danger", buttonStyle = ButtonStyle.Danger, buttonSize = ButtonSize.Small)
      contents += new Button("Link", buttonStyle = ButtonStyle.Link, buttonSize = ButtonSize.Small)
    }
    contents += new tag.P {
      contents += new Button("Default", buttonStyle = ButtonStyle.Default, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Primary", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Success", buttonStyle = ButtonStyle.Success, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Info", buttonStyle = ButtonStyle.Info, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Warning", buttonStyle = ButtonStyle.Warning, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Danger", buttonStyle = ButtonStyle.Danger, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Link", buttonStyle = ButtonStyle.Link, buttonSize = ButtonSize.ExtraSmall)
    }

    contents += new PageTitle("Tables")
    contents += new Row {
      contents += new Column {
        medium := Some(6)

        contents += new Table {
          contents += new TableRow {
            contents += new tag.Th(content = "#")
            contents += new tag.Th(content = "First Name")
            contents += new tag.Th(content = "Last Name")
            contents += new tag.Th(content = "Username")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "1")
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "2")
            contents += new tag.Td(content = "Jacob")
            contents += new tag.Td(content = "Thornton")
            contents += new tag.Td(content = "@fat")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "3")
            contents += new tag.Td(content = "Larry")
            contents += new tag.Td(content = "the Bird")
            contents += new tag.Td(content = "@twitter")
          }
        }
      }
      contents += new Column {
        medium := Some(6)

        contents += new Table {
          striped := true

          contents += new TableRow {
            contents += new tag.Th(content = "#")
            contents += new tag.Th(content = "First Name")
            contents += new tag.Th(content = "Last Name")
            contents += new tag.Th(content = "Username")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "1")
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "2")
            contents += new tag.Td(content = "Jacob")
            contents += new tag.Td(content = "Thornton")
            contents += new tag.Td(content = "@fat")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "3")
            contents += new tag.Td(content = "Larry")
            contents += new tag.Td(content = "the Bird")
            contents += new tag.Td(content = "@twitter")
          }
        }
      }
    }
    contents += new Row {
      contents += new Column {
        medium := Some(6)

        contents += new Table {
          bordered := true

          contents += new TableRow {
            contents += new tag.Th(content = "#")
            contents += new tag.Th(content = "First Name")
            contents += new tag.Th(content = "Last Name")
            contents += new tag.Th(content = "Username")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "1", rowSpan = 2)
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "2")
            contents += new tag.Td(content = "Jacob")
            contents += new tag.Td(content = "Thornton")
            contents += new tag.Td(content = "@fat")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "3")
            contents += new tag.Td(content = "Larry the Bird", colSpan = 2)
            contents += new tag.Td(content = "@twitter")
          }
        }
      }
      contents += new Column {
        medium := Some(6)

        contents += new Table {
          condensed := true

          contents += new TableRow {
            contents += new tag.Th(content = "#")
            contents += new tag.Th(content = "First Name")
            contents += new tag.Th(content = "Last Name")
            contents += new tag.Th(content = "Username")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "1")
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "2")
            contents += new tag.Td(content = "Jacob")
            contents += new tag.Td(content = "Thornton")
            contents += new tag.Td(content = "@fat")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "3")
            contents += new tag.Td(content = "Larry the Bird", colSpan = 2)
            contents += new tag.Td(content = "@twitter")
          }
        }
      }
    }

    contents += new PageTitle("Thumbnails")
    contents += new HolderImage(200, 200, "A generic square placeholder image with a white border around it, making it resemble a photograph taken with an old instant camera")

    contents += new PageTitle("Labels")
    contents += new tag.H1 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H2 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H3 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H4 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H5 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H6 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.P {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }

    contents += new PageTitle("Badges")
    contents += new tag.P {
      contents += new tag.A(href = "#") {
        contents += "Inbox"
        contents += new Badge("42")
      }
    }
    contents += new Tabs {
      tabs := false
      pills := true

      contents += new TabEntry {
        active := true

        contents += new tag.A(href = "#") {
          contents += "Home"
          contents += new Badge("42")
        }
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#") {
          contents += "Profile"
        }
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#") {
          contents += "Messages"
          contents += new Badge("3")
        }
      }
    }

    contents += new PageTitle("Dropdown menus")
    contents += new tag.Div(clazz = List("dropdown", "theme-dropdown", "clearfix")) {
      contents += new tag.A(id = "dropdownMenu1", href = "#", role = "button", clazz = List("sr-only", "dropdown-toggle")) {
        data("toggle", "dropdown")
        contents += "Dropdown"

        contents += new tag.B(clazz = List("caret"))
      }
      contents += new tag.Ul(clazz = List("dropdown-menu"), role = "menu") {
        ariaLabelledBy := "dropdownMenu1"
        contents += new tag.Li(clazz = List("active"), role = "presentation") {
          contents += new tag.A(role = "menuitem", href = "#") {
            contents += "Action"
          }
        }
        contents += new tag.Li(role = "presentation") {
          contents += new tag.A(role = "menuitem", href = "#") {
            contents += "Another action"
          }
        }
        contents += new tag.Li(role = "presentation") {
          contents += new tag.A(role = "menuitem", href = "#") {
            contents += "Something else here"
          }
        }
        contents += new tag.Li(role = "presentation", clazz = List("divider"))
        contents += new tag.Li(role = "presentation") {
          contents += new tag.A(role = "menuitem", href = "#") {
            contents += "Separated link"
          }
        }
      }
    }

    contents += new PageTitle("Navs")
    contents += new Tabs {
      role := "tablist"

      contents += new TabEntry {
        active := true

        contents += new tag.A(href = "#", content = "Home")
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#", content = "Profile")
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#", content = "Messages")
      }
    }
    contents += new Tabs {
      tabs := false
      pills := true

      contents += new TabEntry {
        active := true

        contents += new tag.A(href = "#", content = "Home")
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#", content = "Profile")
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#", content = "Messages")
      }
    }

    contents += new PageTitle("Navbars")

    body.contents += NavBarFactory(
      brand = "Project name",

      theme = NavBarTheme.Default,

      links = Seq(
        NavBarFactory.Link("#", "Home", active = true),
        NavBarFactory.Link("#", "About"),
        NavBarFactory.Link("#", "Contact")
      )
    )

    body.contents += NavBarFactory(
      brand = "Project name",

      theme = NavBarTheme.Inverse,

      links = Seq(
        NavBarFactory.Link("#", "Home", active = true),
        NavBarFactory.Link("#", "About"),
        NavBarFactory.Link("#", "Contact")
      )
    )

    contents += new PageTitle("Alerts")
    contents += Alert.success("Well done!", "You successfully read this important alert message.")
    contents += Alert.info("Heads up!", "This alert needs your attention, but it's not super important.")
    contents += Alert.warning("Warning!", "Best check yo self, you're not looking too good.")
    contents += Alert.danger("Oh snap!", "Change a few things up and try submitting again.")

    contents += new PageTitle("Progress bars")
    contents += ProgressBar(0.6, ProgressType.Default)
    contents += ProgressBar(0.4, ProgressType.Success)
    contents += ProgressBar(0.2, ProgressType.Info)
    contents += ProgressBar(0.6, ProgressType.Warning)
    contents += ProgressBar(0.8, ProgressType.Danger)
    contents += ProgressBar(0.6, ProgressType.Default, striped = true)
    contents += new ProgressBar {
      addProgress(0.35, ProgressType.Success)
      addProgress(0.2, ProgressType.Warning)
      addProgress(0.1, ProgressType.Danger)
    }

    contents += new PageTitle("List groups")
    contents += new Row {
      contents += new Column(small = Some(4)) {
        contents += new ListGroup {
          contents += new tag.Span("Cras justo odio") with ListGroupItem
          contents += new tag.Span("Dapibus ac facilisis in") with ListGroupItem
          contents += new tag.Span("Morbi leo risus") with ListGroupItem
          contents += new tag.Span("Porta ac consectetur ac") with ListGroupItem
          contents += new tag.Span("Vestibulum at eros") with ListGroupItem
        }
      }
      contents += new Column(small = Some(4)) {
        contents += new ListGroup {
          contents += new tag.A(href = "#", content = "Cras justo odio") with ListGroupItem {
            active := true
          }
          contents += new tag.A(href = "#", content = "Dapibus ac facilisis in") with ListGroupItem
          contents += new tag.A(href = "#", content = "Morbi leo risus") with ListGroupItem
          contents += new tag.A(href = "#", content = "Porta ac consectetur ac") with ListGroupItem
          contents += new tag.A(href = "#", content = "Vestibulum at eros") with ListGroupItem
        }
      }
      contents += new Column(small = Some(4)) {
        contents += new ListGroup {
          contents += new tag.A(href = "#") with ListGroupItem {
            active := true
            contents += new ListGroupItemHeading("List group item heading")
            contents += new ListGroupItemText("Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.")
          }

          contents += new tag.A(href = "#") with ListGroupItem {
            contents += new ListGroupItemHeading("List group item heading")
            contents += new ListGroupItemText("Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.")
          }

          contents += new tag.A(href = "#") with ListGroupItem {
            contents += new ListGroupItemHeading("List group item heading")
            contents += new ListGroupItemText("Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.")
          }

          contents += new tag.A(href = "#") with ListGroupItem {
            contents += new ListGroupItemHeading("List group item heading")
            contents += new ListGroupItemText("Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.")
          }
        }
      }
    }

    contents += new PageTitle("Panels")
    contents += new Row {
      contents += new Column(small = Some(4)) {
        contents += new PanelFactory("Panel title", Some("Panel content"), PanelType.Default)
        contents += new PanelFactory("Panel title", Some("Panel content"), PanelType.Primary)
      }
      contents += new Column(small = Some(4)) {
        contents += new PanelFactory("Panel title", Some("Panel content"), PanelType.Success)
        contents += new PanelFactory("Panel title", Some("Panel content"), PanelType.Info)
      }
      contents += new Column(small = Some(4)) {
        contents += new PanelFactory("Panel title", Some("Panel content"), PanelType.Warning)
        contents += new PanelFactory("Panel title", Some("Panel content"), PanelType.Danger)
      }
    }

    contents += new PageTitle("Wells")
    contents += new Well {
      contents += new tag.P {
        contents += "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sed diam eget risus varius blandit sit amet non magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Aenean lacinia bibendum nulla sed consectetur."
      }
    }
  }
}