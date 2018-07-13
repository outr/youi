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

  /**
    * Returns true if errors that happen in the browser should be logged on the server.
    *
    * Defaults to true.
    */
  protected def logJavaScriptErrors: Boolean = Profig("logJavaScriptErrors").as[Option[Boolean]].getOrElse(true)

  protected val logPath: String = "/client/log"

  val connectivityEntries: Var[Set[ApplicationConnectivity]] = Var(Set.empty[ApplicationConnectivity])

  /**
    * Default connectivity using "/communication" as the path. Can be overridden if this path is not desirable.
    */
  val connectivity: ApplicationConnectivity = createConnectivity()

  def createConnectivity(path: String = "/communication",
                         autoConnect: Boolean = true): ApplicationConnectivity = {
    new ApplicationConnectivity(this, path, autoConnect)
  }
}

object YouIApplication {
  private var instance: Option[YouIApplication] = None

  def get(): Option[YouIApplication] = instance

  def apply(): YouIApplication = instance.getOrElse(throw new RuntimeException("No YouIApplication is initialized!"))
}