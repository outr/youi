package org.hyperscala.ui.widgets


import org.hyperscala.html._
import attributes.{InputType, Target}
import org.hyperscala.Unique
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.css.attributes.Display
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import org.hyperscala.ui.BusyDialog

import language.reflectiveCalls
import com.outr.net.http.handler.{MultipartSupport, MultipartHandler}
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.module.Module
import org.powerscala.Version

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
abstract class FileUploader extends tag.Div with MultipartSupport {
  Webpage().require(FileUploader)

  def uploadTitle = "Uploading file..."
  def processingTitle = "Processing file..."

  def begin(request: HttpRequest, response: HttpResponse) = {
    BusyDialog.show(processingTitle)
  }

  def finish(request: HttpRequest, response: HttpResponse) = {
    uploadForm.contents -= _input
    _input = createInput()
    uploadForm.contents += _input
    BusyDialog.hide()

    response.copy(status = HttpResponseStatus.OK)
  }

  val iFrame = new tag.IFrame(id = s"iframe$identity", name = Unique(), src = "about:blank") {
    style.display := Display.None
  }
  val uploadPath = s"${FileUploader.Path}?pageId=${Webpage().pageId}&fieldId=$identity"
  val uploadForm = new tag.Form(id = s"form$identity", action = uploadPath, encType = "multipart/form-data", method = "post", target = Target(iFrame.name()))
  val inputId = s"file$identity"
  private var _input = createInput()
  def input = _input

  protected def createInput() = {
    new tag.Input(id = inputId, name = "file", inputType = InputType.File, size = 20) {
      changeEvent := RealtimeEvent()
      changeEvent.on {
        case evt => {
          BusyDialog.show(uploadTitle)
          Realtime.sendJavaScript("$('#%s').submit();".format(uploadForm.id()))
        }
      }
    }
  }

  uploadForm.contents += _input
  contents += uploadForm
  contents += iFrame
}

object FileUploader extends Module {
  val Path = "/file_upload"

  val name = "fileUploader"
  val version = Version(1)

  override def dependencies = List(BusyDialog)

  def init() = {
    Website().addHandler(FileUploadHandler, Path)
  }

  def load() = {}
}

object FileUploadHandler extends MultipartHandler {
  def create(request: HttpRequest, response: HttpResponse) = {
    val pageId = request.url.parameters.first("pageId")
    val fieldId = request.url.parameters.first("fieldId")
    val page = Website().pages.byId[Webpage](pageId).getOrElse(throw new NullPointerException(s"Cannot find page by id: $pageId"))
    Webpage.updateContext(page)
    page.getById[FileUploader](fieldId)
  }
}