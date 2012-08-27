package org.hyperscala.web.live

import org.hyperscala.{Unique, Container, PropertyAttribute}
import org.hyperscala.html.{Script, Text, Title, HTMLTag}
import org.hyperscala.css.{StyleSheet, StyleSheetAttribute}
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

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LivePage extends HTMLPage {
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
        LivePage.Template.format(connection.id, increment.get())
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
      val parent = evt.parent.asInstanceOf[HTMLTag]
      val child = evt.child.asInstanceOf[HTMLTag]
      if (parent.id() == null) {
        parent.id := Unique()
      }
      if (child.id() == null) {
        child.id := Unique()
      }
//      println("hasRoot? %s".format(hasRoot(child)))
//      println("\t%s".format(child.outputString))
      enqueue(LiveChange(nextId, null, "liveAdd("))
    }
    case evt: ChildRemovedEvent => println("ChildRemoved! %s".format(evt))
  }

  /*listeners.synchronous.filter(evt => true) {
    case evt: PropertyChangeEvent => evt.property match {
      case p if (p == title) => // Ignore
      case attribute: StyleSheetAttribute[_] => attribute.parent match {
        case ss: StyleSheet => {
          tagsByStyleSheet(ss) {
            case tag => enqueue(LiveChange.css(tag, attribute))
          }
        }
      }
      case attribute: PropertyAttribute[_] => if (hasRoot(attribute)) {
        attribute.parent match {
          case tag: Text => if (tag.parent != null) {
            println("*** Unhandled Text changed: %s".format(tag.content()))
          }
          case tag: HTMLTag => {
            if (attribute.name() == "id" && evt.oldValue == null) {   // Assigned ID for the first time - create
              enqueue(LiveChange.create(tag))
            } else if (attribute.name() != "style") {
              enqueue(LiveChange.attribute(tag, attribute))
            }
          }
        }
      }
    }
    case evt: ChildAddedEvent => evt.child match {
      case tag: HTMLTag => {
        enqueue(LiveChange.insert(tag))
      }
    }
    case evt: ChildRemovedEvent => evt.child match {
      case tag: HTMLTag => {
        println("REMOVE!!!!")
      }
    }
    case evt: TagCreated => // Ignore
    case evt: ActionEvent => // Ignore
    case evt => println("*** Unhandled LivePage Event: %s".format(evt))
  }*/

  private def hasRoot(parent: Any): Boolean = parent match {
    case _ if (parent == this) => true
    case c: Child => hasRoot(c.parent)
    case _ => false
  }

  @tailrec
  private def enqueue(changes: List[LiveChange], connections: List[LiveConnection] = this.connections): Unit = {
    if (connections.nonEmpty) {
      changes.foreach(println)
      val c = connections.head
      c ++= changes
      enqueue(changes, connections.tail)
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

  @tailrec
  final def ++=(changes: List[LiveChange]): Unit = {
    if (changes.nonEmpty) {
      val change = changes.head
      this += change
      this ++= changes.tail
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

object LiveChange {
  def attribute(tag: HTMLTag, attribute: PropertyAttribute[_])(implicit page: LivePage) = {
    if (tag.id() == null) {
      tag.id := Unique()
    }
    val key = "%s.%s".format(tag.id(), attribute.name())
    val script = if (tag.isInstanceOf[Title] && attribute.name() == "content") {
      "document.title = '%s';".format(attribute.attributeValue)
    } else {
      "liveLookup('%s').attr('%s', %s);".format(tag.id(), attribute.name(), attribute.attributeValue)
    }
    List(LiveChange(page.increment.addAndGet(1), key, script))
  }

  def css(tag: HTMLTag, attribute: StyleSheetAttribute[_])(implicit page: LivePage) = {
    if (tag.id() == null) {
      tag.id := Unique()
    }
    val key = "%s.style.%s".format(tag.id(), attribute.name())
    val script = "liveLookup('%s').css('%s', '%s');".format(tag.id(), attribute.name(), attribute.attributeValue)
    List(LiveChange(page.increment.addAndGet(1), key, script))
  }

  def create(tag: HTMLTag)(implicit page: LivePage) = {
    val key = tag.id()
    val script = "liveCreate('%s', '%s');".format(tag.id(), tag.outputString)
    List(LiveChange(page.increment.addAndGet(1), key, script))
  }

  def insert(tag: HTMLTag)(implicit page: LivePage) = {
    val parent = tag.parent.asInstanceOf[HTMLTag with Container[HTMLTag]]
    if (parent.id() == null) {
      parent.id := Unique()
      Nil
    } else {
      if (tag.id() == null) {
        tag.id := Unique()
      }
      val key = null
      val index = parent.contents.indexOf(tag)
      val script = tag match {
        case text: Text => "liveInsertText('%s', '%s', %s);".format(parent.id(), text.content(), index)
        case _ => "liveInsert('%s', '%s', %s);".format(parent.id(), tag.id(), index)
      }
      List(LiveChange(page.increment.addAndGet(1), key, script))
    }
  }

  def remove(tag: HTMLTag)(implicit page: LivePage) = {

  }
}