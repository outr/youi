package org.hyperscala.bootstrap

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Bootstrap extends Module {
  override def name = "bootstrap"

  override def version = Version(3, 2, 0)

  override def dependencies = List(jQuery)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Meta(httpEquiv = "X-UA-Compatible", content = "IE=edge")
    webpage.head.meta("viewport", "width=device-width, initial-scale=1")

    val v = s"${version.major}.${version.minor}.${version.maintenance}"
    webpage.head.contents += new tag.Link(href = s"//netdna.bootstrapcdn.com/bootstrap/$v/css/bootstrap.min.css")
    webpage.head.contents += new tag.Link(href = s"//netdna.bootstrapcdn.com/bootstrap/$v/css/bootstrap-theme.min.css")

    webpage.head.contents += new tag.ConditionalComment("lt IE 9") {
      contents += new tag.Script(src = "https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js")
      contents += new tag.Script(src = "https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js")
    }

    webpage.head.contents += new tag.Script(src = s"//netdna.bootstrapcdn.com/bootstrap/$v/js/bootstrap.min.js")
    webpage.head.contents += new tag.Script(src = "//cdnjs.cloudflare.com/ajax/libs/holder/2.4.1/holder.js")
  }
}