package org.hyperscala.jquery

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import org.powerscala.property.Property
import org.hyperscala.jquery.dsl.jQuerySelector
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.powerscala.concurrent.Time
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Zoomooz(selector: jQuerySelector) {
  def zoomTo(targetSize: Double = 1.0,
             scaleMode: ScaleMode = ScaleMode.Both,
             duration: Double = 1.0,
             nativeAnimation: Boolean = true,
             root: jQuerySelector = null,
             debug: Boolean = false,
             closeClick: Boolean = false,
             preserveScroll: Boolean = false) = {
    var values = Map(
      "targetsize" -> targetSize,
      "scalemode" -> scaleMode.name.toLowerCase,
      "duration" -> Time.millis(duration),
      "nativeanimation" -> nativeAnimation,
      "debug" -> debug,
      "closeclick" -> closeClick,
      "preservescroll" -> preserveScroll
    )
    if (root != null) {
      values += "root" -> root.content
    }
    selector.call("zoomTo", values)
  }
}

object Zoomooz extends Module {
  val name = "zoomooz"
  val version = Version(1, 1, 6)
  val debug = Property[Boolean](default = Some(false))

  override def dependencies = List(jQuery.LatestWithDefault)

  override def init[S <: Session](website: Website[S]) = {
    website.addClassPath("/jquery-zoomooz/", "jquery-zoomooz/")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    val name = if (debug()) "jquery.zoomooz.min.js" else "jquery.zoomooz.js"
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = s"/jquery-zoomooz/$name")
  }
}

class ScaleMode private() extends EnumEntry

object ScaleMode extends Enumerated[ScaleMode] {
  val Width = new ScaleMode
  val Height = new ScaleMode
  val Both = new ScaleMode
}