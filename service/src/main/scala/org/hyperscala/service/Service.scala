package org.hyperscala.service

import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.HttpResponse
import com.outr.net.http.session.Session
import org.hyperscala.web.Website
import org.powerscala.event.Listenable
import org.powerscala.event.processor.ModifiableProcessor
import org.powerscala.reflect.EnhancedClass

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class Service extends Listenable {
  /**
   * The base path used to build all end-points
   */
  def path: String

  /**
   * True if the returned JSON should be formatted. False will render condensed.
   *
   * Defaults to false.
   */
  def formatted = false

  val beforeInvoke = new ModifiableProcessor[ServiceInvocation]("beforeInvoke")

  lazy val endpoints = findEndpoints()

  def endpoint(name: String) = endpoints.find(se => se.name == name)

  /**
   * Registers the service end-points (methods) in this service to the supplied Website.
   *
   * @param site the site to register with
   * @tparam S the session type
   */
  def register[S <: Session](site: Website[S]) = {
    endpoints.foreach {
      case endpoint => site.addHandler(endpoint, endpoint.uri)
    }
  }

  private def findEndpoints() = {
    val clazz: EnhancedClass = getClass
    clazz.methods.collect {
      case m if m.isPublic && m.javaMethod.getAnnotation(classOf[endpoint]) != null => {
        ServiceEndpoint(this, m.name, m)
      }
    }
  }
}