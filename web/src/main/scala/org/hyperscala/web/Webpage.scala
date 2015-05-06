package org.hyperscala.web

import java.io.OutputStream

import com.outr.net.communicate.ConnectionHolder
import com.outr.net.http.{HttpApplication, HttpHandler}
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.javascript.dsl.Statement
import org.hyperscala.web.event.server.InvokeJavaScript
import org.hyperscala.{XMLContent, Tag, Markup}
import org.hyperscala.html._
import org.hyperscala.module.ModularPage
import org.powerscala.hierarchy.event.{StandardHierarchyEventProcessor, ChildRemovedProcessor, ChildAddedProcessor}
import org.powerscala.json.TypedSupport
import org.powerscala.property.Property
import org.powerscala.reflect._
import org.powerscala.{LocalStack, MapStorage, Unique}
import org.powerscala.hierarchy.{Element, MutableChildLike, ParentLike}
import org.powerscala.concurrent.Time._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Webpage extends HttpHandler with HTMLPage with ModularPage with ParentLike[tag.HTML] with Element[Website] with ConnectionHolder {
  Webpage

  val debugLogging = Property[Boolean](default = Some(false))

  ConnectionHolder.jsonEvent.partial(Unit) {
    case c: WebpageConnect => ConnectionHolder.connection.holder := this      // Switch the connection to the webpage as the holder
  }
  textEvent.on {
    case evt => checkIn()       // Any event should keep the page alive
  }
  binaryEvent.on {
    case evt => checkIn()       // Any event should keep the page alive
  }

  private val _initialized = Property[Boolean](default = Some(false))
  private val _rendered = Property[Boolean](default = Some(false))

  // TODO: replace with PageStatus enum - Created, Initialized, Rendered, Disposed
  def initialized = _initialized.readOnlyView
  def rendered = _rendered.readOnlyView

  val pageId = Unique()
  val store = new MapStorage[Any, Any]

  val childAdded = new ChildAddedProcessor[XMLContent]
  val childRemoved = new ChildRemovedProcessor[XMLContent]
  val pageLoadingEvent = new StandardHierarchyEventProcessor[Webpage]("pageLoading")
  val pageLoadedEvent = new StandardHierarchyEventProcessor[Webpage]("pageLoaded")

  def defaultTitle = CaseValue.generateLabel(getClass.getSimpleName.replaceAll("\\$", ""))

  private lazy val basicHTML = new tag.HTML

  def html = basicHTML

  def head = html.head
  def body = html.body

  def website = hierarchicalParent

  MutableChildLike.assignParent(html, this)

  protected lazy val hierarchicalChildren = List(html)

  def title = head.title

  title := defaultTitle
  head.meta("generator", "Hyperscala")
  head.meta("pageId", pageId)
  head.charset("UTF-8")

  def byName[T <: HTMLTag](name: String)(implicit manifest: Manifest[T]) = html.byName[T](name)(manifest)

  def byTag[T <: HTMLTag](implicit manifest: Manifest[T]) = html.byTag[T](manifest)

  def byId[T <: Tag](id: String)(implicit manifest: Manifest[T]) = html.byId[T](id)(manifest)

  def getById[T <: Tag](id: String)(implicit manifest: Manifest[T]) = html.getById[T](id)(manifest)

  def init(website: Website) = _initialized.synchronized {
    if (!initialized()) {
      // Make the webpage logger's parent reference the website's logger
      logger.parent = Some(website.logger)
      // Make sure the website is the page's parent
      website.addChild(this)

      _initialized := true
    }
  }

  /**
   * Invokes the supplied function once-per session at page loading time.
   *
   * @param f the function to invoke
   */
  def session(f: => Unit) = {
    val id = Unique()
    pageLoadingEvent.on {
      case webpage => {
        webpage.website.session.get[String](id) match {
          case Some(_) => // Already loaded, nothing to do
          case None => try {
            f
          } finally {
            webpage.website.session(id) = id     // Make sure this only happens once per session
          }
        }
      }
    }
  }

  def onReceive(request: HttpRequest, response: HttpResponse) = errorSupport {
    val status = HttpResponseStatus.OK
    val content = new HTMLStreamer(html) {
      override def stream(output: OutputStream) = HttpApplication.around(request) {
        pageLoading()
        super.stream(output)
        pageLoaded()
      }
    }
    val headers = response.headers.CacheControl()
    response.copy(content = content, status = status, headers = headers)
  }.getOrElse(website.errorPage(request, response, HttpResponseStatus.InternalServerError))

  /**
   * The amount of time in seconds this webpage will continue to be cached in memory without any communication.
   *
   * Defaults to 2 minutes.
   */
  def timeout = 2.minutes

  override def checkIn() = {
    if (website.requestOption.nonEmpty) {
      website.session.checkIn()             // Keep the session alive as well
    }
    super.checkIn()
  }

  /**
   * Called before the page is (re)loaded.
   */
  def pageLoading() = {
    pageLoadingEvent.fire(this)
  }

  /**
   * Called after the page is (re)loaded.
   */
  def pageLoaded() = {
    html.byTag[HTMLTag].foreach(Markup.rendered)
    _rendered := true
    pageLoadedEvent.fire(this)
  }

  /**
   * Pages should invoke this if an exception has been thrown to allow the webpage to gracefully handle the error.
   *
   * @param t the throwable thrown
   */
  def pageError(t: Throwable) = website.pageError.fire(this -> t)

  /**
   * Error Support wraps the supplied function so if an exception is thrown while invoking it will be properly reported
   * via the pageError method.
   *
   * @param f the function to execute.
   */
  def errorSupport[R](f: => R): Option[R] = try {
    Some(f)
  } catch {
    case t: Throwable => {
      pageError(t)
      None
    }
  }

  /**
   * Invokes the supplied function on all matching tags immediately and invokes on all new tags created at init time.
   *
   * @param f the function to invoke
   * @param manifest defines the erasured generic type of the matching T
   * @tparam T the filtered tag type to apply to the function
   */
  def live[T <: HTMLTag](f: T => Unit)(implicit manifest: Manifest[T]) = {
    // First we walk through the hierarchical structure
    html.byTag[T](manifest).foreach {
      case t => f(t)
    }
    // Now we intercept init to determine when new items are created
    intercept.init.on {
      case m: Markup if m.getClass.hasType(manifest.runtimeClass) => f(m.asInstanceOf[T])
      case _ => // Ignore
    }
  }

  override def dispose() = {
    super.dispose()

    website.pages.remove(this)
    website.removeChild(this)
  }

  /**
   * Sends the supplied JavaScript to the client for execution in the browser.
   *
   * NOTE: Requires the use of some Communicate implementation like Realtime for this to actually function.
   *
   * @param js the JavaScript to invoke
   * @param condition optional condition that must return true before the JavaScript is invoked
   */
  def eval(js: JavaScriptContent, condition: Option[Statement[Boolean]] = None) = {
    if (debugLogging()) {
      warn(s"eval: ${js.content}", new RuntimeException("Processing JavaScript eval."))
    }
    broadcastJSON(InvokeJavaScript(js.content, condition.map(s => s.content).orNull))
  }
}

case class WebpageConnect(pageId: String)

object Webpage {
  val stack = new LocalStack[Webpage]

  TypedSupport.register("eval", classOf[InvokeJavaScript])
  TypedSupport.register("page", classOf[WebpageConnect])
}