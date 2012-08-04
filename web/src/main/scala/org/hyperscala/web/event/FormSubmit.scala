package org.hyperscala.web.event

import org.powerscala.event.Event
import org.hyperscala.html.attributes.Method

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class FormSubmit(method: Method) extends Event