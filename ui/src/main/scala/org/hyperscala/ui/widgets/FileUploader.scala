package org.hyperscala.ui.widgets

import com.outr.webcommunicator.netty.handler.{RequestHandler, UploadHandler}
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.handler.codec.http.multipart.{Attribute, FileUpload}

import org.hyperscala.html._
import attributes.{InputType, Target}
import org.hyperscala.Unique
import org.hyperscala.event.{ChangeEvent, JavaScriptEvent}
import org.hyperscala.web.site.{Webpage, WebpageResource, Website}
import org.hyperscala.web.Scope
import org.hyperscala.css.attributes.Display
import org.hyperscala.realtime.Realtime
import org.hyperscala.ui.BusyDialog

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
abstract class FileUploader extends tag.Div {
  Webpage().require(BusyDialog)

  def uploadTitle = "Uploading file..."
  def processingTitle = "Processing file..."

  def uploaded(upload: FileUpload): Unit

  def finished() = {
    uploadForm.contents -= _input
    _input = createInput()
    uploadForm.contents += _input
    BusyDialog.hide()
  }

  val uid = identity
  val uploadPath = "/file_upload/%s.html".format(uid)

  val handler = new FileUploadHandler(this)
  Website().registerSession(WebpageResource(uploadPath, handler, Scope.Request))

  val iFrame = new tag.IFrame(id = "iframe%s".format(uid), name = Unique(), src = "about:blank") {
    style.display = Display.None
  }
  val uploadForm = new tag.Form(id = "form%s".format(uid), action = uploadPath, encType = "multipart/form-data", method = "post", target = Target(iFrame.name()))
  val inputId = "file%s".format(uid)
  private var _input = createInput()
  def input = _input

  protected def createInput() = {
    new tag.Input(id = inputId, name = "file", inputType = InputType.File, size = 20) {
      event.change := JavaScriptEvent()
      listeners.synchronous {
        case evt: ChangeEvent => {
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

class FileUploadHandler(uploader: FileUploader) extends UploadHandler {
  override def minimumFileSize = 0L

  private val webpage = Webpage()
  private var upload: FileUpload = _

  def receivedAttribute(attribute: Attribute) = {}

  def receivedFileUpload(upload: FileUpload) = this.upload = upload

  def sendResponse(context: ChannelHandlerContext) = {
    RequestHandler.redirect("about:blank", context)
    webpage.context {
      BusyDialog.show(uploader.processingTitle)
      try {
        uploader.uploaded(upload)
      } finally {
        uploader.finished()
      }
    }
  }
}