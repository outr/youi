package org.hyperscala.event

import org.powerscala.event.Event
import org.hyperscala.Tag

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class TagCreated(tag: Tag) extends Event