package io.youi.app

import com.outr.reactify._
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

  private[app] val activeConnections = Var[Set[Connection]](Set.empty)

  /**
    * Listing of all active connections. On the client this will only every have one entry.
    */
  val connections: Val[Set[Connection]] = Val(activeConnections)

  /**
    * The absolute path to establish / listen for web socket communication.
    *
    * Defaults to "/communication".
    */
  def connectionPath: String = "/communication"

  /**
    * Creates a new CommunicationManager for the Communication trait defined. This should be used to define a val in the
    * shared application trait that will be utilized in both client and server.
    *
    * @tparam C the Communication trait
    * @return CommunicationManager[C]
    */
  def communication[C <: Communication]: CommunicationManager[C] = macro Macros.communication[C]
}

object YouIApplication {
  private var instance: Option[YouIApplication] = None

  def get(): Option[YouIApplication] = instance

  def apply(): YouIApplication = instance.getOrElse(throw new RuntimeException("No YouIApplication is initialized!"))
}