package org.hyperscala.ui.widgets


import org.hyperscala.html._
import attributes.{InputType, Target}
import org.hyperscala.javascript.JavaScriptString
import org.powerscala.Unique
import org.hyperscala.web._
import org.hyperscala.css.attributes.Display
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import org.hyperscala.ui.BusyDialog

import language.reflectiveCalls
import com.outr.net.http.handler.{MultipartSupport, MultipartHandler}
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.module.Module
import org.powerscala.Version
import com.outr.net.Method
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
abstract class FileUploader extends tag.Div with MultipartSupport {
  this.require(FileUploader)

  def uploadTitle = "Uploading file..."
  def processingTitle = "Processing file..."

  def begin(request: HttpRequest, response: HttpResponse) = {
    BusyDialog.show(this.webpage, processingTitle)
  }

  def finish(request: HttpRequest, response: HttpResponse) = {
    uploadForm.contents -= _input
    _input = createInput()
    uploadForm.contents += _input
    BusyDialog.hide(this.webpage)

    response.copy(status = HttpResponseStatus.OK)
  }

  val iFrame = new tag.IFrame(id = s"iframe$identity", name = Unique(), src = "about:blank") {
    style.display := Display.None
  }

  val uploadForm = new tag.Form(id = s"form$identity", action = "", encType = "multipart/form-data", method = Method.Post, target = Target(iFrame.name()))
  uploadForm.submitEvent := JavaScriptString("")
  val inputId = s"file$identity"
  private var _input = createInput()
  def input = _input

  connected[Webpage[_ <: Session]] {
    case webpage => {
      val uploadPath = s"${FileUploader.Path}?pageId=${webpage.pageId}&fieldId=$identity"
      uploadForm.action := uploadPath
    }
  }
  connected[tag.Form] {         // Make sure another form doesn't wrap this or things will mess up.
    case form => error(s"Another form is wrapping this FileUploader so it will not work properly!")
  }

  protected def createInput() = {
    new tag.Input(id = inputId, name = "file", inputType = InputType.File, size = 20) {
      changeEvent := RealtimeEvent()
      changeEvent.on {
        case evt => {
          BusyDialog.show(this.webpage, uploadTitle)
          println(s"UploadFormID: ${uploadForm.id()}")
          Realtime.sendJavaScript(this.webpage, "$('#%s').submit();".format(uploadForm.id()))
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

  override def init[S <: Session](website: Website[S]) = {
    website.addHandler(new FileUploadHandler(website), Path)
  }

  override def load[S <: Session](webpage: Webpage[S]) = {}
}

class FileUploadHandler[S <: Session](website: Website[S]) extends MultipartHandler {
  def create(request: HttpRequest, response: HttpResponse) = {
    val pageId = request.url.parameters.first("pageId")
    val fieldId = request.url.parameters.first("fieldId")
    val page = website.pages.byId[Webpage[S]](pageId).getOrElse(throw new NullPointerException(s"Cannot find page by id: $pageId"))
    page.getById[FileUploader](fieldId)
  }
}