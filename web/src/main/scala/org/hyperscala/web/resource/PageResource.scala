package org.hyperscala.web.resource

import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait PageResource extends WebResource with Listenable with InterceptableWebResource with DisposableWebResource {
//  override val bus = new Bus(Priority.Normal)
//  Bus.current = bus
//
//  override protected def createInterceptors() = new BusInterceptor(bus) :: super.createInterceptors()
//
//  override def dispose() {
//    super.dispose()
//    bus.clear()
//  }
}
