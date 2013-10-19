package org.hyperscala.hello

import org.hyperscala.web.{StaticWebsite, BasicWebsite}

/**
 * HelloSite is the starting point of our application. It is responsible for all resource management and web pages.
 *
 * We are extending both BasicWebsite and DynamicWebsite to simplify the process of getting started.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object HelloSite extends BasicWebsite with StaticWebsite {
  def index = new HelloPage
}