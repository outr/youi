package org.hyperscala.web.useragent

import org.hyperscala.web.module.useragent.BrowserFamily
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class Browser(family: BrowserFamily, version: Version) {
   override def toString = s"name: $family, version: $version"
 }
