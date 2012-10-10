package org.hyperscala.web

import event.FormSubmit
import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.hyperscala.html._
import scala.collection.JavaConversions._
import tag.Form

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait FormSupport {
  this: HTMLPage =>

  override protected def processRequest(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    var ignoreResponse = false
    var form: Form = null
    if (method == Method.Post) {
      request.getParameterMap.foreach {
        case (key, values) => {
          //            println("Key: %s, Value: %s".format(key, values.asInstanceOf[Array[String]].head))
          if (key.toString.endsWith("SendResponse") && values.asInstanceOf[Array[String]].head == "false") {
            ignoreResponse = true
          }
          byName[HTMLTag](key.asInstanceOf[String]) match {
            case Some(tag) if (renderable(tag)) => {      // Only apply to tags that are rendered to the page
              if (form == null) {
                tag.hierarchy.backward[Form]() match {
                  case null => println("WARNING: Unable to find form for %s".format(key)) // Odd, but not impossible
                  case f => form = f
                }
              }
              val v = values.asInstanceOf[Array[String]]
              //                println("Updating %s for %s with %s".format(tag, key, v.mkString(", ")))
              updateValue(method, tag, v)
            }
            case _ => println("Unable to find %s = %s".format(key, values.asInstanceOf[Array[String]].head))
          }
        }
      }
      if (form != null) {
        form.fire(FormSubmit(method))
      }
    }
    !ignoreResponse
  }
}
