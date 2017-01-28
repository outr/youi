package io.youi.example

import com.outr.reactify.Var
import io.youi.comm.Communication

import scala.concurrent.Future

trait ExampleCommunication extends Communication {
//  val randomId: Long                            // Server val
//  val name: Var[Option[String]]                 // Shared Var
  def url: Future[String]                                       // Client call
  def reverse(value: String): Future[String]                    // Server method
  def time: Future[Long]                                        // Server call
  def navigateTo(url: String, force: Boolean): Future[Unit]     // Client method
}