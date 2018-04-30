package io.youi.component.bootstrap

import io.youi.component.extras.HTMLComponent
import io.youi.dom
import org.scalajs.dom._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait BootstrapComponent[E <: html.Element] extends HTMLComponent[E] {
  override protected def init(): Unit = {
    BootstrapComponent.loader     // Make sure bootstrap is loaded
    super.init()
  }
}

object BootstrapComponent {
  object versions {
    val bootstrap: String = "4.0.0"
    val jquery: String = "3.2.1"
    val popper: String = "1.12.9"
  }

  private lazy val loader: Future[Unit] = {
    // Add the CSS
    val link = dom.create[html.Link]("link");
    link.rel = "stylesheet"
    link.`type` = "text/css"
    link.href = s"https://maxcdn.bootstrapcdn.com/bootstrap/${versions.bootstrap}/css/bootstrap.min.css"
    document.getElementsByTagName("head")(0).appendChild(link)
    dom.addScript(s"https://code.jquery.com/jquery-${versions.jquery}.slim.min.js").flatMap { _ =>
      dom.addScript(s"https://cdnjs.cloudflare.com/ajax/libs/popper.js/${versions.popper}/umd/popper.min.js").flatMap { _ =>
        dom.addScript(s"https://maxcdn.bootstrapcdn.com/bootstrap/${versions.bootstrap}/js/bootstrap.min.js")
      }
    }
  }
}