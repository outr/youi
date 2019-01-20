package io.youi.example.ui

import io.youi.component.HTMLTextInput
import io.youi.{Color, Template, dom}
import io.youi.component.bootstrap.Button
import io.youi.component.extras.HTMLComponent
import io.youi.example.screen.UIExampleScreen
import io.youi.net._
import org.scalajs.dom._

import scala.concurrent.Future

class BootstrapLoginExample extends UIExampleScreen {
  override def title: String = "Bootstrap Login"
  override def path: Path = path"/examples/bootstrap/login.html"

  private lazy val form: html.Form = Template.byId[html.Form]("login-example.html", "login-form", "youi")

  override def createUI(): Future[Unit] = {
    Button.color := Color.White
    container.background := Color.fromLong(0xf5f5f5ff)
    dom.addCSS(
      """
        |.form-signin {
        |  width: 100%;
        |  max-width: 330px;
        |  padding: 15px;
        |  margin: 0 auto;
        |}
        |.form-signin .checkbox {
        |  font-weight: 400;
        |}
        |.form-signin .form-control {
        |  position: relative;
        |  box-sizing: border-box;
        |  height: auto;
        |  padding: 10px;
        |  font-size: 16px;
        |}
        |.form-signin .form-control:focus {
        |  z-index: 2;
        |}
        |.form-signin input[type="email"] {
        |  margin-bottom: -1px;
        |  border-bottom-right-radius: 0;
        |  border-bottom-left-radius: 0;
        |}
        |.form-signin input[type="password"] {
        |  margin-bottom: 10px;
        |  border-top-left-radius: 0;
        |  border-top-right-radius: 0;
        |}
      """.stripMargin)

    val parent = HTMLComponent.element(container)
    parent.appendChild(form)

    val email = HTMLTextInput.existing("inputEmail", parent)
    val password = HTMLTextInput.existing("inputPassword", parent)

    email.value.attach { e =>
      scribe.info(s"Value: $e")
    }

    val button = Button.existing("sign-in", parent)
    scribe.info(s"Button: ${button.block()}")

    Future.successful(())
  }
}
