package org.hyperscala.event.processor

import org.powerscala.event.processor.InterceptProcessor
import org.powerscala.event.Listenable
import org.hyperscala.event.BrowserEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class EventReceivedProcessor(implicit listenable: Listenable) extends InterceptProcessor[BrowserEvent]("eventReceived")