package org.hyperscala.fabricjs

import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.hyperscala.module.Module
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Website, Webpage}
import com.outr.net.http.session.Session
import org.powerscala.event.Listenable
import org.powerscala.property.Property
import org.powerscala.{Color, Unique, Version}
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object FabricJS extends Module {
  val name = "fabric.js"
  val version = Version(1, 4, 13)

  override def dependencies = List(Realtime)

  override def init(website: Website) = {
    website.register("/js/fabric.js", "fabric.js")
    website.register("/js/fabric.min.js", "fabric.min.js")
    website.register("/js/hyperscala-fabric.js", "hyperscala-fabric.js")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(src = "/js/fabric.min.js")
    webpage.head.contents += new tag.Script(src = "/js/hyperscala-fabric.js")
  }
}