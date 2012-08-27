package org.hyperscala.web.live

import org.hyperscala.{Unique, Container, PropertyAttribute}
import org.hyperscala.html.{Script, Text, Title, HTMLTag}
import org.hyperscala.css.StyleSheet
import org.hyperscala.web.HTMLPage
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
   */
  def pollInterval = 15000

  implicit val livePage = this

  private var connections = List.empty[LiveConnection]
  protected[live] val increment = new AtomicInteger(0)
  protected def nextId = increment.addAndGet(1)

  head.id := "liveHead"
  head.contents(0).id := "liveTitle"
  ensureScript("/js/jquery-1.7.2.js", ".*/jquery.*")
  head.contents += new Script {
    contents += new JavaScriptContent {
      def content = synchronized {   // Called per render!!!
        val connection = new LiveConnection()
        connections = connection :: connections
        // TODO: add support for timing out connection
        LivePage.Template.format(connection.id, increment.get(), maximumClientRetries, pollInterval)
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
    case evt: ChildAddedEvent => {
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
    case evt: ChildRemovedEvent => evt.child match {
      case text: Text => {
        val parent = evt.parent.asInstanceOf[HTMLTag with Container[HTMLTag]]
        val index = parent.contents.indexOf(text)
        enqueue(LiveChange(nextId, null, "liveRemoveByIndex('%s', %s);".format(parent.id(), index)))
      }
      case tag: HTMLTag => enqueue(LiveChange(nextId, null, "liveRemove('%s');".format(tag.id())))
    }
  }

  listeners.synchronous.filter(evt => true) {
    case evt: PropertyChangeEvent => evt.property match {
      case property: PropertyAttribute[_] => property.parent match {
        case tag: HTMLTag => if (hasRoot(tag) && property != tag.style && !tag.isInstanceOf[Text]) {
          if (property == tag.id && evt.oldValue == null) {
            // Ignore
          } else {
            val key = "%s.%s".format(tag.id(), property.name())
            val script = if (tag.isInstanceOf[Title] && property.name() == "content") {
              "document.title = '%s';".format(property.attributeValue)
            } else {
              "$('#%s').attr('%s', %s);".format(tag.id(), property.name(), property.attributeValue)
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
          }
        }
        case m => throw new RuntimeException("Unhandled Message Type: %s".format(m))
      }
      // TODO: apply messages
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