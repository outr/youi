package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.log.Logging
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object PageTimer extends Module with Logging {
  val name = "pageTimer"
  val version = Version(1)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](page: Webpage[S]) = {
    page.store("pageLoadingStart") = System.currentTimeMillis()
    page.pageLoadedEvent.on {
      case webpage => {
        val start = webpage.store[Long]("pageLoadingStart")
        info(s"${webpage.getClass} loaded in ${System.currentTimeMillis() - start} milliseconds.")
      }
    }
  }
}