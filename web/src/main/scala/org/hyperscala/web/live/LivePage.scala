package org.hyperscala.web.live

import org.hyperscala._
import org.hyperscala.html._
import org.hyperscala.css.StyleSheet
import org.hyperscala.web.{Website, HTMLPage}
import actors.threadpool.AtomicInteger
import scala.io.Source
import org.hyperscala.javascript.JavaScriptContent
import annotation.tailrec
import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import util.parsing.json.{JSONFormat, JSON}
import org.powerscala.hierarchy.Child

import org.powerscala.concurrent.Time._
import org.powerscala.concurrent.Time
import org.powerscala.property.event.PropertyChangeEvent
import org.powerscala.hierarchy.event.ChildRemovedEvent
import org.powerscala.hierarchy.event.ChildAddedEvent
import util.parsing.json.JSONArray
import util.parsing.json.JSONObject

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LivePage extends HTMLPage {
  HTMLTag.GenerateIds = true    // Every element should have an ID

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

  def debugMode = false

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
  head.contents += new tag.Script {
    contents += new JavaScriptContent {
      def content = synchronized {   // Called per render!!!
        val connection = new LiveConnection()
        connections = connection :: connections
        LivePage.Template.format(connection.id, increment.get(), maximumClientRetries, Time.millis(pollInterval), debugMode)
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
      val script = if (index == parent.contents.length - 1) {    // Append to then end
        "$('#%s').append('%s');".format(parent.id(), child.outputString)
      } else if (index == 0) {                                   // Append before
        val after = parent.contents(1)
        "$('#%s').before('%s');".format(after.id(), child.outputString)
      } else {
        val before = parent.contents(index - 1)
        "$('#%s').after('%s');".format(before.id(), child.outputString)
      }
      enqueue(LiveChange(nextId, null, script))
    }
    case evt: ChildRemovedEvent if (!applying.get()) => evt.child match {
      case text: tag.Text => {
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
        case t: HTMLTag => if (hasRoot(t) && property != t.style && !t.isInstanceOf[tag.Text]) {
          if (property == t.id && evt.oldValue == null) {
            // Ignore
          } else {
            val key = "%s.%s".format(t.id(), property.name())
            val script = if (t.isInstanceOf[tag.Title] && property.name() == "content") {
              "document.title = '%s';".format(property.attributeValue)
            } else {
              "$('#%s').attr('%s', '%s');".format(t.id(), property.name(), property.attributeValue)
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

  override def sendRedirect(url: String) = sendJavaScript("window.location.href = '%s';".format(url))

  def sendJavaScript(js: String) = enqueue(LiveChange(nextId, null, js))

  @tailrec
  final def enqueue(change: LiveChange, connections: List[LiveConnection] = this.connections): Unit = {
    if (connections.nonEmpty) {
//      println(change)
      val c = connections.head
      c += change
      enqueue(change, connections.tail)
    }
  }

  override def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) {
    if (method == Method.Post) {
      HTMLPage.instance.set(this)
      try {
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
            val t = view.find(t => t.id() == id).getOrElse(throw new RuntimeException("Unable to find %s".format(id)))
            val messageType = map("type").asInstanceOf[String]
            messageType match {
              case "event" => {
                val event = map("event").asInstanceOf[String]
                t.fire(LiveEvent(t, event))
              }
              case "change" => {
                val v = map("value").asInstanceOf[String]
                applying.set(true)    // Make sure we don't send extraneous information back
                try {
                  t match {
                    case input: tag.Input => input.value := v
                    case select: tag.Select => select.contents.foreach {
                      case option: tag.Option => if (option.value() == v) {
                        option.selected := true
                      } else {
                        option.selected := false
                      }
                    }
                    case textArea: tag.TextArea => textArea.contents.replaceWith(v)
                  }
                } finally {
                  applying.set(false)
                }
              }
            }
          }
          case m => throw new RuntimeException("Unhandled Message Type: %s".format(m))
        }
        pageUpdate()
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
      } finally {
        HTMLPage.instance.set(null)
      }
    } else {
      pageLoading()
      super.service(method, request, response)
      pageLoaded()
    }
  }

  /**
   * Called before the page is reloaded.
   */
  def pageLoading() = {}

  /**
   * Called when the page is reloaded.
   */
  def pageLoaded() = {}

  /**
   * Called when the client checks in to the server.
   */
  def pageUpdate() = {}

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