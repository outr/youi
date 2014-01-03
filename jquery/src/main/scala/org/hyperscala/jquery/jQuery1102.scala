package org.hyperscala.jquery

import org.powerscala.Version
import org.hyperscala.web.module.{WebJarType, WebJarModule}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuery1102 extends WebJarModule {
  addWebJar("jquery", "1.10.2", WebJarType.Script, "jquery.min.js")
  addWebJar("jquery", "1.10.2", WebJarType.Resource, "jquery.js", "jquery-1.10.2.min.map")

  def name = "jquery"
  def version = Version(1, 10, 2)

  override def implements = List(jQuery)
}
