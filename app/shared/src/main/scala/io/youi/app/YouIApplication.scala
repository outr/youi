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

  val communicationEntries: State[Set[ApplicationConnectivity]] = Var(Set.empty[ApplicationConnectivity])

  /**
    * Default connectivity using "/communication" as the path. Can be overridden if this path is not desirable.
    */
  val connectivity: ApplicationConnectivity = createConnectivity()

  def createConnectivity(path: String = "/communication"): ApplicationConnectivity = new ApplicationConnectivity(this, path)
}

/**
  * ApplicationCommunication defines a communication end-point for Communication instances. More than one can be defined
  * in an application.
  *
  * @param application the application this is associated with
  * @param path the absolute path to establish or listen for web socket communication. Defaults to "/communication"
  */
class ApplicationConnectivity private[app](val application: YouIApplication, val path: String = "/communication") {
  private[app] val activeConnections = Var[Set[Connection]](Set.empty)

  /**
    * Listing of all active connections. On the client this will only every have one entry.
    */
  val connections: Val[Set[Connection]] = Val(activeConnections)

  // Make sure the application knows about it
  application.synchronized {
    val entries = application.communicationEntries()
    application.communicationEntries.asInstanceOf[Var[Set[ApplicationConnectivity]]] := entries + this
  }

  /**
    * Creates a new CommunicationManager for the Communication trait defined. This should be used to define a val in the
    * shared application trait that will be utilized in both client and server.
    *
    * @return CommunicationManager[C]
    */
  def communication[C <: Communication]: CommunicationManager[C] = macro Macros.communication[C]
}

object YouIApplication {
  private var instance: Option[YouIApplication] = None

  def get(): Option[YouIApplication] = instance

  def apply(): YouIApplication = instance.getOrElse(throw new RuntimeException("No YouIApplication is initialized!"))
}