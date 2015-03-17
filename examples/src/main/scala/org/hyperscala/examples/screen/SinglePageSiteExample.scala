package org.hyperscala.examples.screen

import com.outr.net.URL
import org.hyperscala.bootstrap.Bootstrap
import org.hyperscala.bootstrap.component._
import org.hyperscala.css.attributes.Display
import org.hyperscala.examples.Example
import org.hyperscala.html.attributes.InputType
import org.hyperscala.html._
import org.hyperscala.screen._
import org.hyperscala.realtime._
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SinglePageSiteExample extends Example {
  require(Bootstrap)

  connected[Webpage] {
    case webpage => {
      webpage.head.contents += new tag.Link(href = "/css/single_page_site.css")
      webpage.connectForm()
    }
  }

  val loginLink = "/example/advanced/login.html"
  val welcomeLink = "/example/advanced/single_page_site.html"
  val authenticatedLink = "/example/advanced/authenticated.html"

  val screens = new Screens(this)

  val userVerify = (url: URL) => if (this.website.session.contains("username")) {
    true
  } else {
    screens.activate(loginLink, replace = true)
    false
  }

  val loginScreen = screens.screen(loginLink, new LoginScreen(this))
  val welcomeScreen = screens.screen(welcomeLink, new WelcomeScreen(this))
  val authenticatedScreen = screens.screen(authenticatedLink, new AuthenticatedScreen(this), verify = userVerify)

  val main = new tag.Div
  contents += main

  contents += new ButtonGroup {
    contents += new Button("Welcome") {
      clickEvent.onRealtime {
        case evt => screens.activate(welcomeLink, replace = false)
      }
    }
    contents += new Button("Login") {
      clickEvent.onRealtime {
        case evt => screens.activate(loginLink, replace = false)
      }
    }
    contents += new Button("Authenticated") {
      clickEvent.onRealtime {
        case evt => screens.activate(authenticatedLink, replace = false)
      }
    }
  }
}

abstract class SinglePageScreen(val example: SinglePageSiteExample) extends Container with Screen {
  style.display := Display.None
  example.main.contents += this

  override def activate() = style.display := Display.Block

  override def deactivate() = style.display := Display.None

  override def dispose() = removeFromParent()
}

class LoginScreen(example: SinglePageSiteExample) extends SinglePageScreen(example) {
  val usernameInput = new tag.Input(inputType = InputType.Text, clazz = List("form-control"), placeHolder = "Username", required = true, autoFocus = true)
  val passwordInput = new tag.Input(inputType = InputType.Password, clazz = List("form-control"), placeHolder = "Password", required = true)

  contents += new Form {
    contents += new tag.H2(clazz = List("form-heading"), content = "Please sign in")
    contents += usernameInput
    contents += passwordInput
    contents += new Button("Sign in", ButtonStyle.Primary, ButtonSize.Large, block = true)

    submitEvent.on {
      case evt => logIn()
    }
  }

  private def logIn() = {
    example.website.session.update("username", usernameInput.value())
    example.authenticatedScreen.dispose()         // Remove any existing instances of authenticated screen
    example.screens.activate(example.authenticatedLink, replace = true)
  }
}

class WelcomeScreen(example: SinglePageSiteExample) extends SinglePageScreen(example) {
  contents += new Form {
    contents += new tag.H2(clazz = List("form-heading"), content = "Welcome!")
    contents += new tag.P(content = "This is a multi-page site represented as a series of screens dynamically loaded instead of unique web pages.")
  }
}

class AuthenticatedScreen(example: SinglePageSiteExample) extends SinglePageScreen(example) {
  contents += new Form {
    contents += new tag.H2(clazz = List("form-heading"), content = "Authenticated Page")
    contents += new tag.P(content = s"This is an authenticated screen. Welcome ${example.website.session[String]("username")}.")
  }
}