package org.hyperscala.ui.form.error

import org.hyperscala.html.HTMLTag
import org.hyperscala.Container
import org.hyperscala.html.constraints.BodyChild

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class HTMLErrorSupport[E <: HTMLTag, C <: HTMLTag with Container[BodyChild]](val container: C) extends ErrorSupport {
  override protected def clearDisplay() = {
    super.clearDisplay()

    container.contents.clear()
  }
}
