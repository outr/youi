package io.youi.template

import io.youi.activate.ActivationSupport
import io.youi.app.ClientApplication
import io.youi.dom
import org.scalajs.dom._

import scala.scalajs.js.annotation.JSExportTopLevel

object ClientTemplateApplication extends TemplateApplication with ClientApplication {
  private lazy val pages = dom.byId[html.Input]("template_pages").value.split(';').toSet

  @JSExportTopLevel("application")
  def main(): Unit = {
    ActivationSupport.debug = true
    val paths = pages.map { page =>
      s"/${page.substring(0, page.indexOf('.'))}"
    }
    paths.map { p =>
      new TemplateScreen(p)
    }

    println("Template initialized!")
  }
}