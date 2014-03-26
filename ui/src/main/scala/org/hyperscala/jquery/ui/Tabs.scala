package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.web._
import org.powerscala.Unique
import org.hyperscala.realtime.Realtime
import org.hyperscala.selector.Selector
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Tabs extends tag.Div {
  this.require(Realtime)
  this.require(jQueryUI.LatestWithDefault)

  identity

  val navigation = new tag.Ul(id = Unique())
  contents += navigation

  connected[Webpage[_ <: Session]] {
    case webpage => Realtime.sendJavaScript(webpage, "$('#%s').tabs();".format(id()), selector = Some(Selector.id(identity)), onlyRealtime = false)
  }

  def addTab[T <: tag.Div](label: String)(f: => T) = {
    val content: T = f
    navigation.contents += new tag.Li {
      contents += new tag.A(href = "#%s".format(content.identity), content = label)
    }
    contents += content
    content
  }
}

object Tabs {
  def apply(t: HTMLTag) = jQueryUI.tabs(t)
}