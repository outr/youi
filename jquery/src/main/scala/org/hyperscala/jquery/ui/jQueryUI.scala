package org.hyperscala.jquery.ui

import org.hyperscala.module._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI extends Interface {
  def Latest = jQueryUI1101
  lazy val LatestWithDefault = InterfaceWithDefault(jQueryUI, Latest)

  def name = "jquery-ui"
}
