package org.hyperscala.examples.bootstrap

import org.hyperscala.bootstrap.Bootstrap
import org.hyperscala.bootstrap.component._
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.html.attributes.InputType
import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BootstrapSignin extends Webpage with Example {
  require(Bootstrap)

  head.contents += new tag.Link(href = "/form.css")

  body.contents += new Container {
    contents += new Form {
      contents += new tag.H2(clazz = List("form-heading"), content = "Please sign in")
      contents += new tag.Input(inputType = InputType.Email, clazz = List("form-control"), placeHolder = "Email address", required = true, autoFocus = true)
      contents += new tag.Input(inputType = InputType.Password, clazz = List("form-control"), placeHolder = "Password", required = true)
      contents += new tag.Label(clazz = List("checkbox")) {
        contents += new tag.Input(inputType = InputType.CheckBox)
        contents += "Remember me"
      }
      contents += new Button("Sign in", ButtonStyle.Primary, ButtonSize.Large, block = true)
    }
  }
}
