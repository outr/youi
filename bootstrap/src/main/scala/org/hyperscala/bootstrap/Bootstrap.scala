package org.hyperscala.bootstrap

import org.hyperscala.module.Module
import org.powerscala.Version
import com.outr.net.http.session.Session
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Bootstrap extends Module {
  override def name = "bootstrap"

  override def version = Version(3, 1, 1)

  override def dependencies = List(jQuery.LatestWithDefault)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Meta(httpEquiv = "X-UA-Compatible", content = "IE=edge")
    webpage.head.meta("viewport", "width=device-width, initial-scale=1")

    webpage.head.contents += new tag.Link(href = "//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css")
    webpage.head.contents += new tag.Link(href = "//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css")

    webpage.head.contents += new tag.ConditionalComment("lt IE 9") {
      contents += new tag.Script(src = "https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js")
      contents += new tag.Script(src = "https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js")
    }

    webpage.head.contents += new tag.Script(src = "//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js")
    webpage.head.contents += new tag.Script(src = "//cdnjs.cloudflare.com/ajax/libs/holder/2.3.1/holder.min.js")
  }
}