package org.hyperscala.examples.service

import java.net.URLDecoder

import com.outr.net.http.content.StringContent
import muster._
import muster.codec.jawn._

import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.event.SubmitEvent
import org.hyperscala.html._
import org.hyperscala.javascript.dsl.{JSON, window, JSFunction1}
import org.hyperscala.jquery.dsl._
import org.hyperscala.examples.Example
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.jQuerySerializeForm

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ServiceExample extends Example {
  require(jQuerySerializeForm)

  contents += new tag.Form(id = "form") {
    contents += new tag.Input(id = "message", name = "message", placeHolder = "Enter a Message")
  }

  val submitFunction = $("#form").submit(new JSFunction1[SubmitEvent, Unit] {
    val response = $("#form").serializeForm()
    $.post("/message", JSON.stringify(response), new JSFunction1[String, Unit] {
      val response = $.parseJSON(p1)
      window.alert("Message from server: ", response("message"))
      end()
    })
    stopPropagation()
  })
  contents += new tag.Script(content = submitFunction)
}

object MessageService extends HttpHandler {
  override def onReceive(request: HttpRequest, response: HttpResponse) = {
    val responseMessage = request.contentString.map(s => JawnCodec.as[ServiceMessage](URLDecoder.decode(s, "UTF-8"))) match {
      case Some(m) if m.message.trim.nonEmpty => {
        m.copy(message = m.message.reverse)
      }
      case _ => ServiceMessage("Invalid message!")
    }
    val content = StringContent(JawnCodec.Pretty.from(responseMessage))
    response.copy(status = HttpResponseStatus.OK, content = content)
  }
}

case class ServiceMessage(message: String = "")