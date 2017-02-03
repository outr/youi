package io.youi.communication

import java.util.concurrent.atomic.AtomicInteger

import com.outr.reactify.{Channel, Var}
import com.outr.scribe._
import io.youi.ErrorSupport
import io.youi.http.Connection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.language.experimental.macros

/**
  * Communication provides convenience functionality to communicate between a client and server (two-way) via WebSockets.
  * The supported mechanisms are methods and shared variables.
  *
  * Method Example:
  *
  * <pre>@server def time: Future[Long]</pre>
  *
  * Notice the <pre>@server</pre> annotation. This represents that this method will be defined in the server
  * implementation allowing the client to call the method and it is invoked on the server and the result is received on
  * the client. To create a method in the client that is called from the server simply use the <pre>@client</pre>
  * annotation instead. The return type of the method must be of <pre>Future</pre> or it will be ignored.
  *
  * Shared Var Example:
  *
  * <pre>val name: com.outr.reactify.Var[String] = shared[String]("Default")</pre>
  *
  * This represents a variable that is shared between the client and server. Either can change the value and it will be
  * reflected in the other asynchronously. We use Reactify's <pre>Var</pre> because it allows functional reactive
  * observing of changes.
  *
  * Note that three traits must be defined for a <pre>Communication</pre>:
  * 1. Shared Interface (defined as the definition of each method and shared variable):
  *   <pre>
  *     trait ExampleCommunication extends Communication {
  *       @server def time: Future[Long]
  *       @client def url: Future[String]
  *       val name: Var[String] = shared[String]("Default")
  *     }
  *   <pre>
  * 2. Client Interface (defined in Scala.js code):
  *   <pre>
  *     trait ClientExampleCommunication extends ExampleCommunication {
  *       override def url: Future[String] = Future.successful(document.url.href)
  *     }
  *   </pre>
  * 3. Server Interface (defined in JVM code):
  *   <pre>
  *     trait ServerExampleCommunication extends ExampleCommunication {
  *       override def time: Future[Long] = Future.successful(System.currentTimeMillis())
  *     }
  *   </pre>
  *
  * Notice that only the methods that need to be implemented on the client or server are defined. The rest are generated
  * at compile-time.
  *
  * @see io.youi.app.YouIApplication for usage examples
  */
trait Communication extends ErrorSupport {
  val comm: CommunicationInternal = new CommunicationInternal(this)
  comm.init()

  def connection: Connection

  def shared[T](default: T): Var[T] = macro Macros.shared[T]
}

class CommunicationInternal private[communication](communication: Communication) {
  private val increment = new AtomicInteger(0)
  private var queue = Map.empty[Int, CommunicationMessage => Unit]

  val send: Channel[CommunicationMessage] = Channel[CommunicationMessage]
  val receive: Channel[CommunicationMessage] = Channel[CommunicationMessage]

  private[communication] def init(): Unit = {
    receive.attach { message =>
      if (message.messageType == CommunicationMessage.MethodResponse) {
        synchronized {
          val f = queue.get(message.invocationId)
          queue -= message.invocationId
          f
        } match {
          case Some(f) => f(message)
          case None => logger.warn(s"No entry found for endPointId: ${message.endPointId}, invocationId: ${message.invocationId}, content: ${message.content}.")
        }
      }
    }

    communication.connection.receive.text.attach {
      case CommunicationMessage(message) => {
        communication.connection.manager.withConnection(communication.connection) {
          receive := message
        }
      }
    }
    send.attach { message =>
      communication.connection.send.text := message.parsableString
    }
  }

  def nextId(): Int = increment.getAndIncrement()

  // Received remote request for invocation of method
  def onEndPoint[T](endPointId: Int)(f: CommunicationMessage => Future[String]): Unit = {
    receive.attach { message =>
      if (message.endPointId == endPointId && message.messageType == CommunicationMessage.MethodRequest) {
        f(message).map { content =>
          send := CommunicationMessage(CommunicationMessage.MethodResponse, endPointId, message.invocationId, List(content), None)
        }.failed.foreach { t =>
          send := CommunicationMessage(CommunicationMessage.MethodResponse, endPointId, message.invocationId, Nil, Some(t.getMessage))
          communication.error(t)
        }
      }
    }
  }

  // Wait for invocation response
  def onInvocation[T](invocationId: Int)(f: CommunicationMessage => T): Future[T] = synchronized {
    val promise = Promise[T]
    val handler: CommunicationMessage => Unit = (m: CommunicationMessage) => m.error match {
      case Some(error) => {
        promise.failure(new CommunicationException(error))
      }
      case None => {
        val t = f(m)
        promise.success(f(m))
      }
    }
    queue += invocationId -> handler
    promise.future
  }
}

object Communication {
  private val increment = new AtomicInteger(0)

  def nextEndPointId: Int = increment.getAndIncrement()

  def create[C <: Communication](connection: Connection): C = macro Macros.create[C]
}

class CommunicationException(message: String) extends RuntimeException(message)