package org.hyperscala.examples.service

import com.outr.net.http.content.StringContent
import muster._
import muster.codec.jawn._

import com.outr.net.http.HttpHandler
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.html._
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

  val js =
    """
      |$('#form').submit(function(evt) {
      | var json = $('#form').serializeForm();
      | $.post('/message', JSON.stringify(json), function(data) {
      |   var response = jQuery.parseJSON(data);
      |   alert('Message from server: ' + response.message);
      | });
      | evt.stopPropagation();
      | return false;
      |});
    """.stripMargin
  contents += new tag.Script(content = JavaScriptString(js))
}

object MessageService extends HttpHandler {
  override def onReceive(request: HttpRequest, response: HttpResponse) = {
    val responseMessage = request.contentString.map(s => JawnCodec.as[ServiceMessage](s)) match {
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