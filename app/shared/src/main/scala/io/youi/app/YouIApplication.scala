package io.youi.app

import reactify._
import io.youi.ErrorSupport
import io.youi.communication.Communication
import io.youi.http.Connection

import scala.language.experimental.macros

/**
  * Base trait to define shared client and server information. This trait must be extended in the shared code as a trait
  * and implemented in both client and server implementations.
  */
trait YouIApplication extends ErrorSupport {
  YouIApplication.instance = Some(this)

  val connectivityEntries: State[Set[ApplicationConnectivity]] = Var(Set.empty[ApplicationConnectivity])

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