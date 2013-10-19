package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.Webpage
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
object PageTimer extends Module with Logging {
  val name = "pageTimer"
  val version = Version(1)

  def init() = {
  }

  def load() = {
    val page = Webpage()
    page.store("pageLoadingStart") = System.currentTimeMillis()
    page.pageLoadedEvent.on {
      case webpage => {
        val start = webpage.store[Long]("pageLoadingStart")
        info(s"${webpage.getClass} loaded in ${System.currentTimeMillis() - start} milliseconds.")
      }
    }
  }
}