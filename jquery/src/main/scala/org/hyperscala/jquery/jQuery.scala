package org.hyperscala.jquery

import org.hyperscala.module._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuery extends Interface {
  def Latest = jQuery1102
  val LatestWithDefault = InterfaceWithDefault(jQuery, Latest)

  def name = "jquery"
}