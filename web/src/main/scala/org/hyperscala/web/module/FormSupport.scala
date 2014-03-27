package org.hyperscala.web.module

import org.powerscala.TypeFilteredIterator
import org.hyperscala.web.Webpage
import org.hyperscala.html.tag.Form
import org.hyperscala.html.{tag, HTMLTag}
import org.powerscala.hierarchy.ChildLike
import org.hyperscala.event.SubmitEvent
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.HttpResponse
import com.outr.net.http.session.Session
import com.outr.net.Method
import com.outr.net.http.content.FormPostContent
import com.outr.net.http.HttpParameters

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait FormSupport[S <: Session] extends Webpage[S] {
  override def onReceive(request: HttpRequest, response: HttpResponse) = {
    var form: Form = null
    val parameters = if (request.method == Method.Post) {
      request.content match {
        case Some(content) => content match {
          case fpc: FormPostContent => fpc.parameters
        }
        case None => HttpParameters.Empty
      }
    } else {
      request.url.parameters
    }

    parameters.values.foreach {
      case (key, values) => html.byName[HTMLTag](key).headOption match {
        case Some(t) if t.renderable => {   // Only apply to tags that are rendered to the page
          if (form == null) {
            TypeFilteredIterator[Form](ChildLike.selfAndAncestors(t)).toStream.headOption match {
              case None => warn(s"WARNING: Unable to find form for $key") // Odd, but not impossible
              case Some(f) => form = f
            }
          }
          val v = values.toArray.map(o => o.toString)
          t match {
            case button: tag.Button => button.submitEvent.fire(new SubmitEvent(button))
            case _ => t.formValue := v.head
          }
        }
        case _ => warn("Unable to find %s = %s".format(key, values.head))
      }
    }
    if (form != null) {
      form.submitEvent.fire(new SubmitEvent(form))
    }

    super.onReceive(request, response)
  }
}
