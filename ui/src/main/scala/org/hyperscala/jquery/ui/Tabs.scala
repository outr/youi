package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.web.Webpage
import org.hyperscala.Unique
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Tabs extends tag.Div {
  Webpage().require(Realtime)
  Webpage().require(jQueryUI.LatestWithDefault)

  identity

  val navigation = new tag.Ul(id = Unique())
  contents += navigation

  onInit {
    Realtime.sendJavaScript("$('#%s').tabs();".format(id()), forId = identity, onlyRealtime = false)
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