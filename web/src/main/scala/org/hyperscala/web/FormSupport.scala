package org.hyperscala.web

import org.hyperscala.html.attributes.Method
import org.hyperscala.html._
import scala.collection.JavaConversions._
import site.Webpage
import tag.Form
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.http.QueryStringDecoder
import org.jboss.netty.util.CharsetUtil
import org.hyperscala.event.FormSubmit

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait FormSupport extends Webpage {
  override protected def processPost(content: ChannelBuffer) = {
    val decoder = new QueryStringDecoder("?%s".format(content.toString(CharsetUtil.UTF_8)))
    var form: Form = null
    decoder.getParameters.foreach {
      case (key, values) => {
        html.byName[HTMLTag](key).headOption match {
          case Some(t) if (t.renderable) => {      // Only apply to tags that are rendered to the page
            if (form == null) {
              t.hierarchy.backward[Form]() match {
                case null => warn("WARNING: Unable to find form for %s".format(key)) // Odd, but not impossible
                case f => form = f
              }
            }
            val v = values.toArray.map(o => o.toString)
            t match {
              case button: tag.Button => button.fire(FormSubmit(Method.Post))
              case _ => t.formValue := v.head
            }
          }
          case _ => warn("Unable to find %s = %s".format(key, values.asInstanceOf[Array[String]].head))
        }
      }
    }
    if (form != null) {
      form.fire(FormSubmit(Method.Post))
    }
  }
}
