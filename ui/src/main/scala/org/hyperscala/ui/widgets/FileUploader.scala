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
import org.hyperscala.web.site.realtime.Realtime
import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.jquery.ui.Dialog

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class FileUploader extends tag.Div {
  Webpage().require(FileUploader)

  val uid = identity
  val uploadPath = "/file_upload/%s.html".format(uid)

  val handler = new FileUploadHandler(this)
  Website().registerSession(WebpageResource(uploadPath, handler, Scope.Request))

  val iframe = new tag.IFrame(id = "iframe%s".format(uid), name = Unique(), src = "http://goclassichomes.intuitwebsites.com/G_O_Red_Page.gif")
  val uploadForm = new tag.Form(id = "form%s".format(uid), action = uploadPath, encType = "multipart/form-data", method = "post", target = Target(iframe.name()))
  val input = new tag.Input(id = "file%s".format(uid), name = "file", inputType = InputType.File) {
    event.change := JavaScriptEvent()
    listeners.synchronous {
      case evt: ChangeEvent => {
        progressDialog.dialog.open()
        Realtime.sendJavaScript("$('#%s').submit();".format(uploadForm.id()))
      }
    }
  }

  val progressDialog = new tag.Div() with Dialog {
    dialog.autoOpen := false
    dialog.closeOnEscape := false
    dialog.modal := true
    dialog.title := "Uploading file..."
    dialog.width := 235
    dialog.height := 120
    dialog.resizable := false
    contents += new tag.Img(src = "/images/indeterminate_progress01.gif")
  }

  uploadForm.contents += input
  contents += uploadForm
  contents += iframe
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
  def receivedAttribute(attribute: Attribute) = println("Received attribute: %s=%s".format(attribute.getName, attribute.getValue))

  def receivedFileUpload(upload: FileUpload) = println("Received file: %s".format(upload.getFilename))

  def sendResponse(context: ChannelHandlerContext) = {
    println("Redirecting to google!")
    RequestHandler.redirect("http://richardkeys.com/Images/blue_page_1c.jpg", context)
    uploader.progressDialog.dialog.close()
  }
}