package org.hyperscala.web.live

import org.hyperscala.{Unique, Container, PropertyAttribute}
import org.hyperscala.html.{Script, Text, Title, HTMLTag, Input, Select, TextArea}
import org.hyperscala.css.StyleSheet
import org.hyperscala.web.{Website, HTMLPage}
import actors.threadpool.AtomicInteger
import org.powerscala.hierarchy.event.{ChildRemovedEvent, ChildAddedEvent}
import io.Source
import org.hyperscala.javascript.JavaScriptContent
import annotation.tailrec
import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import util.parsing.json.{JSONFormat, JSONArray, JSONObject, JSON}
import org.powerscala.event.ActionEvent
import org.powerscala.hierarchy.Child
import org.powerscala.property.event.PropertyChangeEvent

import org.powerscala.concurrent.Time._
import org.powerscala.concurrent.Time

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LivePage extends HTMLPage {
  /**
   * Maximum number of times the client / browser will retry a poll before giving up
   */
  def maximumClientRetries = 10

  /**
   * How frequently the client will poll the server when idle.
   *
   * Defaults to 15 seconds
   */
  def pollInterval: Double = 15.seconds

  /**
   * The amount of time a connection should live without any communication.
   *
   * Defaults to 2 minutes.
   */
  def connectionTimeout: Double = 2.minutes

  /**
   * If true the session will be disconnected after all connections are removed.
   *
   * Defaults to true.
   */
  def killSessionOnDisconnect = true

  implicit val livePage = this

  private var connections = List.empty[LiveConnection]
  protected[live] val increment = new AtomicInteger(0)
  protected def nextId = increment.addAndGet(1)

  // True if a value is currently being applied from the client
  private val applying = new ThreadLocal[Boolean] {
    override def initialValue() = false
  }

  head.id := "liveHead"
  head.contents(0).id := "liveTitle"
  ensureScript("/js/jquery-1.7.2.js", ".*/jquery.*")
  head.contents += new Script {
    contents += new JavaScriptContent {
      def content = synchronized {   // Called per render!!!
        val connection = new LiveConnection()
        connections = connection :: connections
        // TODO: add support for timing out connection
        LivePage.Template.format(connection.id, increment.get(), maximumClientRetries, Time.millis(pollInterval))
      }

      protected def content_=(content: String) {}
    }
  }

  body.id := "liveBody"

  def tagsByStyleSheet(ss: StyleSheet, tags: Iterable[HTMLTag] = view)(f: HTMLTag => Any): Unit = {
    if (tags.nonEmpty) {
      val t = tags.head
      if (t.style.loaded && t.style() == ss) {
        f(t)
      }
      tagsByStyleSheet(ss, tags.tail)(f)
    }
  }

  listeners.synchronous.filter.descendant() {
    case evt: ChildAddedEvent if (!applying.get()) => {
      val parent = evt.parent.asInstanceOf[HTMLTag with Container[HTMLTag]]
      val child = evt.child.asInstanceOf[HTMLTag]
      if (parent.id() == null) {
        parent.id := Unique()
      }
      if (child.id() == null) {
        child.id := Unique()
      }
      val index = parent.contents.indexOf(child)
      enqueue(LiveChange(nextId, null, "liveAdd('%s', %s, '%s');".format(parent.id(), index, child.outputString)))
    }
    case evt: ChildRemovedEvent if (!applying.get()) => evt.child match {
      case text: Text => {
        val parent = evt.parent.asInstanceOf[HTMLTag with Container[HTMLTag]]
        val index = parent.contents.indexOf(text)
        enqueue(LiveChange(nextId, null, "liveRemoveByIndex('%s', %s);".format(parent.id(), index)))
      }
      case tag: HTMLTag => enqueue(LiveChange(nextId, null, "liveRemove('%s');".format(tag.id())))
    }
  }

  listeners.synchronous.filter(evt => true) {
    case evt: PropertyChangeEvent if (!applying.get()) => evt.property match {
      case property: PropertyAttribute[_] => property.parent match {
        case tag: HTMLTag => if (hasRoot(tag) && property != tag.style && !tag.isInstanceOf[Text]) {
          if (property == tag.id && evt.oldValue == null) {
            // Ignore
          } else {
            val key = "%s.%s".format(tag.id(), property.name())
            val script = if (tag.isInstanceOf[Title] && property.name() == "content") {
              "document.title = '%s';".format(property.attributeValue)
            } else {
              "$('#%s').attr('%s', '%s');".format(tag.id(), property.name(), property.attributeValue)
            }
            enqueue(LiveChange(nextId, key, script))
          }
        }
        case ss: StyleSheet => tagsByStyleSheet(ss) {
          case tag => {
            val key = "%s.style.%s".format(tag.id(), property.name())
            val script = "$('#%s').css('%s', '%s');".format(tag.id(), property.name(), property.attributeValue)
            enqueue(LiveChange(nextId, key, script))
          }
        }
      }
      case _ => // Ignore
    }
  }

  private def hasRoot(parent: Any): Boolean = parent match {
    case _ if (parent == this) => true
    case c: Child => hasRoot(c.parent)
    case _ => false
  }

  @tailrec
  private def enqueue(change: LiveChange, connections: List[LiveConnection] = this.connections): Unit = {
    if (connections.nonEmpty) {
//      println(change)
      val c = connections.head
      c += change
      enqueue(change, connections.tail)
    }
  }

  override def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) {
    if (method == Method.Post) {
      val postData = Source.fromInputStream(request.getInputStream).mkString
//      println("PostData: %s".format(postData))
      val json = JSON.parseFull(postData).get.asInstanceOf[Map[String, Any]]
      val connectionId = json("liveId").asInstanceOf[String]
      val messageId = json("liveMessageId").asInstanceOf[Double].toInt
      val messages = json("messages").asInstanceOf[List[Any]]

      messages.foreach {
        case m: Map[_, _] => {
          val map = m.asInstanceOf[Map[String, Any]]
          val id = map("id").asInstanceOf[String]
          val tag = view.find(t => t.id() == id).getOrElse(throw new RuntimeException("Unable to find %s".format(id)))
          val messageType = map("type").asInstanceOf[String]
          messageType match {
            case "event" => {
              val event = map("event").asInstanceOf[String]
              tag.fire(ActionEvent(event))
            }
            case "change" => {
              val v = map("value").asInstanceOf[String]
              applying.set(true)    // Make sure we don't send extraneous information back
              try {
                tag match {
                  case input: Input => input.value := v
                  case select: Select => select.contents.foreach {
                    case option: org.hyperscala.html.Option => if (option.value() == v) {
                      option.selected := true
                    } else {
                      option.selected := false
                    }
                  }
                  case textArea: TextArea => textArea.contents.replaceWith(v)
                }
              } finally {
                applying.set(false)
              }
            }
          }
        }
        case m => throw new RuntimeException("Unhandled Message Type: %s".format(m))
      }
      val changes = connections.find(c => c.id == connectionId) match {
        case Some(connection) => {
          connection(messageId)
        }
        case None => {
          System.err.println("Connection dead (%s)!".format(connectionId))
          List(LiveChange(0, null, "window.location.href = window.location.href;"))
        }
      }
      val changesJSON = changes.map(c => new JSONObject(Map("id" -> c.id, "script" -> c.script))).toList
      val responseArray = JSONArray(changesJSON)
      val formatted = JSONFormat.defaultFormatter(responseArray)
      response.setContentType("application/json")
      val output = response.getOutputStream
      try {
        output.write(formatted.getBytes)
      } finally {
        output.flush()
        output.close()
      }
    } else {
      super.service(method, request, response)
    }
  }

  override def update(delta: Double) {
    super.update(delta)

    // Check connections
    checkConnections()
  }

  @tailrec
  private def checkConnections(connections: List[LiveConnection] = connections): Unit = {
    if (connections.nonEmpty) {
      val connection = connections.head
      if (connection.elapsed > connectionTimeout) {   // Connection has timed out
        this.connections = this.connections.filterNot(c => c == connection)
        if (killSessionOnDisconnect &&  this.connections.isEmpty) {   // Kill the page session
          dispose()
          try {
            Website().session.map.foreach {
              case (key, value) => if (value == LivePage.this) {
                Website().session.update(key, null)   // Remove from the session
              }
            }
            Website().application.map.foreach {
              case (key, value) => if (value == LivePage.this) {
                Website().application.update(key, null)   // Remove from the application
              }
            }
          } catch {
            case t: Throwable => t.printStackTrace()
          }
        }
      }
      checkConnections(connections.tail)
    }
  }
}

object LivePage {
  val Template = Source.fromURL(getClass.getClassLoader.getResource("livepage.js")).mkString
}

/**
 * LiveConnections are created for each window representation of the LivePage. This allows LiveChange lists to be
 * trimmed and only include relevant changes for the specific window instance. Reloading a page will reset the
 * LiveConnection.
 */
class LiveConnection(val id: String = Unique(),
                     val created: Long = System.currentTimeMillis(),
                     var lastUpdated: Long = System.currentTimeMillis()) {
  private var changes = Vector.empty[LiveChange]

  def elapsed = Time.fromMillis(System.currentTimeMillis() - lastUpdated)

  /**
   * Retrieves all changes since lastId or Nil if no changes.
   */
  def apply(lastId: Int): Seq[LiveChange] = synchronized {
    if (changes.nonEmpty) {
      val head = changes.head
      if (head.id <= lastId) {    // Client verified receipt of this id - remove and continue
        changes = changes.tail
        apply(lastId)
      } else {                    // Client hasn't receive this id, so send the rest of the list
        lastUpdated = System.currentTimeMillis()
        changes
      }
    } else {                      // No changes to send
      lastUpdated = System.currentTimeMillis()
      Nil
    }
  }

  def +=(change: LiveChange) = synchronized {
    if (change.key != null) {
      changes = changes.filterNot(c => c.key == change.key)   // Remove duplicates
    }
    changes = changes :+ change                               // Append the change
  }
}

case class LiveChange(id: Int, key: String, script: String)