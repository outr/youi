package org.hyperscala

import web.VisualException

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object web {
  implicit def throwable2Div(t: Throwable) = new VisualExceptionCreator(t)
}

class VisualExceptionCreator(t: Throwable) {
  def toHTML = new VisualException(t)
}