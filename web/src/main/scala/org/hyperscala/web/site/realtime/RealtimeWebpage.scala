package org.hyperscala.web.site.realtime

import org.hyperscala.web.site.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait RealtimeWebpage extends Webpage {
  require(Realtime)
}
