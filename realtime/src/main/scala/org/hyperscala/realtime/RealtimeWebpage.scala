package org.hyperscala.realtime

import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeWebpage extends Webpage {
  require(Realtime)
  this.connectForm()
}
