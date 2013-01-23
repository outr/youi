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
import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.jquery.ui.Dialog
import org.hyperscala.css.attributes.{Display, Length}
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
abstract class FileUploader extends tag.Div {
  Webpage().require(FileUploader)

  def uploadTitle = "Uploading file..."
  def processingTitle = "Processing file..."

  def uploaded(upload: FileUpload): Unit

  val uid = identity
  val uploadPath = "/file_upload/%s.html".format(uid)

  val handler = new FileUploadHandler(this)
  Website().registerSession(WebpageResource(uploadPath, handler, Scope.Request))

  val iFrame = new tag.IFrame(id = "iframe%s".format(uid), name = Unique(), src = "about:blank") {
    style.display = Display.None
  }
  val uploadForm = new tag.Form(id = "form%s".format(uid), action = uploadPath, encType = "multipart/form-data", method = "post", target = Target(iFrame.name()))
  val input = new tag.Input(id = "file%s".format(uid), name = "file", inputType = InputType.File) {
    event.change := JavaScriptEvent()
    listeners.synchronous {
      case evt: ChangeEvent => {
        progressDialog.dialog.title := uploadTitle
        progressDialog.dialog.open()
        Realtime.sendJavaScript("$('#%s').submit();".format(uploadForm.id()))
      }
    }
  }

  val progressDialog = new tag.Div() with Dialog {
    dialog.autoOpen := false
    dialog.closeOnEscape := false
    dialog.modal := true
    dialog.width := 240
    dialog.height := 120
    dialog.resizable := false
    contents += new tag.Img(src = "/images/indeterminate_progress01.gif", width = "200", height = "40") {
      style.marginLeft = Length.Auto
      style.marginRight = Length.Auto
    }
  }

  uploadForm.contents += input
  contents += uploadForm
  contents += iFrame
  contents += progressDialog
}

object FileUploader extends Module {
  val name = "fileUploader"
  val version = Version(1)

  override def dependencies = List(Realtime)

  def init() = {
    Website().register("/images/indeterminate_progress01.gif", "indeterminate_progress01.gif")
  }

  def load() {}
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
      uploader.progressDialog.dialog.title := uploader.processingTitle
      try {
        uploader.uploaded(upload)
      } finally {
        uploader.progressDialog.dialog.close()
      }
    }
  }
}