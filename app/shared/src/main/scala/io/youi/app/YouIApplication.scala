package io.youi.app

import reactify._
import io.youi.{Cache, CacheImplementation, ErrorSupport}
import profig.Profig

import scala.language.experimental.macros

/**
  * Base trait to define shared client and server information. This trait must be extended in the shared code as a trait
  * and implemented in both client and server implementations.
  */
trait YouIApplication extends ErrorSupport with CacheImplementation {
  Cache.implementation = this
  YouIApplication.instance = Some(this)

  def isClient: Boolean
  def isServer: Boolean

  /**
    * Returns true if errors that happen in the browser should be logged on the server.
    *
    * Defaults to true.
    */
  protected def logJavaScriptErrors: Boolean = Profig("logJavaScriptErrors").opt[Boolean].getOrElse(true)

  protected val logPath: String = "/client/log"

  val connectivityEntries: Var[Set[ApplicationConnectivity]] = Var(Set.empty[ApplicationConnectivity])

  protected def autoConnectCommunication: Boolean = true

  /**
    * Default connectivity using "/communication" as the path. Can be overridden if this path is not desirable.
    */
  val connectivity: ApplicationConnectivity = createConnectivity()

  def createConnectivity(path: String = "/communication"): ApplicationConnectivity = {
    new ApplicationConnectivity(this, path, autoConnectCommunication)
  }
}

object YouIApplication {
  private var instance: Option[YouIApplication] = None

  def get(): Option[YouIApplication] = instance

  def apply(): YouIApplication = instance.getOrElse(throw new RuntimeException("No YouIApplication is initialized!"))
}