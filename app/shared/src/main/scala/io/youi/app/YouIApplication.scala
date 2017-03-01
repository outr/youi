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

  private[app] val communicationEntries: Var[Set[ApplicationCommunication]] = Var(Set.empty[ApplicationCommunication])
  val communication: Val[Set[ApplicationCommunication]] = Val(communicationEntries)
}

/**
  * ApplicationCommunication defines a communication end-point for Communication instances. More than one can be defined
  * in an application.
  *
  * @param application the application this is associated with
  * @param path the absolute path to establish or listen for web socket communication. Defaults to "/communication"
  */
class ApplicationCommunication(val application: YouIApplication, val path: String = "/communication") {
  private[app] val activeConnections = Var[Set[Connection]](Set.empty)

  /**
    * Listing of all active connections. On the client this will only every have one entry.
    */
  val connections: Val[Set[Connection]] = Val(activeConnections)

  // Make sure the application knows about it
  application.synchronized {
    val entries = application.communicationEntries()
    application.communicationEntries := entries + this
  }

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