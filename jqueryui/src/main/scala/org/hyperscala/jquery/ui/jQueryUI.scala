package org.hyperscala.jquery.ui

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery
import org.hyperscala.jquery.dsl._
import org.hyperscala.module.Module
import org.hyperscala.web._
import org.powerscala.Version
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI extends Module {
  val name = "jquery-ui"
  val version = Version(1, 11, 2)
  val theme = Property[Theme](default = Option(Theme.Redmond))

  override def dependencies = List(jQuery)

  override def init[S <: Session](website: Website[S]) = {}

  private def v = version.general

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Link(href = themeCSS, rel = "stylesheet")
    //    webpage.head.contents += new tag.Link(href = "/jquery-ui-1.10.4/css/jquery-ui-fixes.css", rel = "stylesheet")
    webpage.head.contents += new tag.Script(src = s"//code.jquery.com/ui/$v/jquery-ui.min.js")
  }

  def themeCSS = {
    val t = theme()
    if (t.cssPath != null) {
      t.cssPath
    } else {
      s"//code.jquery.com/ui/$v/themes/${t.directory}/jquery-ui.min.css"
    }
  }

  def tabs(t: HTMLTag) = {
    t.require(this)
    t.connected[Webpage[_ <: Session]] {
      case webpage => webpage.eval($(t).call("tabs()"))
    }
  }

  def menu(t: HTMLTag) = {
    t.require(this)
    t.connected[Webpage[_ <: Session]] {
      case webpage => webpage.eval($(t).call("menu()"))
    }
  }

  def datepicker(t: HTMLTag) = {
    t.require(this)
    t.connected[Webpage[_ <: Session]] {
      case webpage => webpage.eval($(t).call("datepicker()"))
    }
  }
}