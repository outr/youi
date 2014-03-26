package org.hyperscala.realtime

import org.hyperscala.module.Module
import org.powerscala.{Unique, Version}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import org.powerscala.event.Intercept
import org.powerscala.log.Logging
import org.powerscala.reflect._
import org.hyperscala.javascript.dsl.JSFunction0
import argonaut.JsonObject
import com.outr.net.http.session.Session

/**
 * JSRequest allows a JavaScript statement to be supplied, it is invoked in the browser, and the result is sent back to
 * the server in response.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object JSRequest extends Module with Logging {
  val name = "jsrequest"
  val version = Version(1)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/jsrequest.js", "jsrequest.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jsrequest.js")
    webpage.body.eventReceived.on {
      case evt => evt.event match {
        case "jsresponse" => webpage.synchronized {
          val id = evt.json.string("responseId")
          webpage.store.get[JSHandler[_]](id) match {
            case Some(handler) => {
              handler.process(evt.json)
              webpage.store.remove(id)
            }
            case None => warn(s"Unable to find JSHandler by id: $id")
          }
          Intercept.Stop
        }
        case _ => Intercept.Continue
      }
    }
  }

  /**
   * Send is an asynchronous request for data from the client. The supplied requests will be submitted and the responses
   * will be supplied to <code>f</code> on the server when the round-trip has completed. This is a one-time action.
   *
   * @param requests the requests to make of the client in JavaScript function form.
   * @param f the response handling function.
   * @param manifest the manifest of T for conversion
   * @tparam T the type of data to be received
   */
  def send[T, S <: Session](webpage: Webpage[S], requests: JSFunction0[T]*)(f: List[T] => Unit)(implicit manifest: Manifest[T]) = webpage.synchronized {
    val id = Unique()
    val handler = JSHandler[T](f, manifest.runtimeClass.asInstanceOf[Class[T]])
    webpage.store.update(id, handler)
    val js = requests.map(jsf => jsf.content).mkString("[", ", ", "]")
    Realtime.sendJavaScript(webpage, s"jsRequest('$id', $js);", onlyRealtime = false)
  }

  case class JSHandler[T](f: List[T] => Unit, clazz: EnhancedClass) {
    def process(json: JsonObject) = {
      val responseList = json.list("responses")
      val responses = responseList.map(r => clazz.convertTo[T](clazz.simpleName, r))
      f(responses)
    }
  }
}