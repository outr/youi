package io.youi.communication

import java.util.concurrent.atomic.AtomicInteger

import reactify.{Channel, Var}
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
  * <pre>val name: reactify.Var[String] = shared[String]("Default")</pre>
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

  def connection: Connection = throw new NotImplementedError()

  def shared[T](default: T): Var[T] = macro Macros.shared[T]
}

class CommunicationInternal private[communication](communication: Communication) {
  private val increment = new AtomicInteger(0)
  private var queue = Map.empty[Int, CommunicationMessage => Unit]
  private var endPoints = Map.empty[String, CommunicationMessage => Future[String]]

  def endPointNames: Set[String] = endPoints.keySet

  val send: Channel[CommunicationMessage] = Channel[CommunicationMessage]
  val receive: Channel[CommunicationMessage] = Channel[CommunicationMessage]

  private[communication] def init(): Unit = {
    receive.attach { message =>
      scribe.debug(s"Received: ${message.messageType}...")
      if (message.messageType == CommunicationMessage.MethodResponse) {
        synchronized {
          val f = queue.get(message.invocationId)
          queue -= message.invocationId
          f
        } match {
          case Some(f) => f(message)
          case None => {
            // TODO: detect if this is the right Communication instance
            scribe.debug(s"${getClass.getName}: No entry found for endPoint: ${message.endPoint}, invocationId: ${message.invocationId}, content: ${message.content}, queue: ${queue.keySet.mkString(", ")}.")
          }
        }
      } else if (message.messageType == CommunicationMessage.MethodRequest) {
        endPoints.get(message.endPoint).foreach { endPoint =>
          endPoint(message).map { content =>
            send := CommunicationMessage(CommunicationMessage.MethodResponse, message.endPoint, message.invocationId, List(content), None)
          }.failed.foreach { t =>
            val error = Option(t.getMessage).orElse(Some("An error occurred"))
            send := CommunicationMessage(CommunicationMessage.MethodResponse, message.endPoint, message.invocationId, Nil, error)
            communication.error(t)
          }
        }
      }
    }

    communication.connection.receive.text.attach {
      case CommunicationMessage(message) => receive := message
      case _ => // Ignore other messages
    }
    send.attach { message =>
      communication.connection.send.text := message.parsableString
    }
  }

  def nextId(): Int = increment.getAndIncrement()

  // Received remote request for invocation of method
  def onEndPoint[T](endPoint: String)(f: CommunicationMessage => Future[String]): Unit = synchronized {
    endPoints += endPoint -> f
  }

  // Wait for invocation response
  def onInvocation[T](invocationId: Int)(f: CommunicationMessage => T): Future[T] = synchronized {
    val promise = Promise[T]
    val handler: CommunicationMessage => Unit = (m: CommunicationMessage) => {
      scribe.debug(s"Received communication message for handler! $invocationId")
      m.error match {
        case Some(error) => promise.failure(new CommunicationException(error))
        case None => try {
          promise.success(f(m))
        } catch {
          case t: Throwable => promise.failure(t)
        }
      }
    }
    val connectionMonitor = communication.connection.connected.attach { b =>
      if (!b && !promise.isCompleted) {
        promise.failure(new RuntimeException("Connection was closed"))
      }
    }
    queue += invocationId -> handler
    val future = promise.future
    future.onComplete { _ =>
      communication.connection.connected.reactions -= connectionMonitor
    }
    future
  }
}

object Communication {
  def create[C <: Communication](connection: Connection): C = macro Macros.create[C]
}

class CommunicationException(message: String) extends RuntimeException(message)