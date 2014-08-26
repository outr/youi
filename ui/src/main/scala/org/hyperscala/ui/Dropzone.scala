package org.hyperscala.ui

import java.io.File

import argonaut.JsonObject
import com.outr.net.http.content.{ContentType, StringContent}
import com.outr.net.http.handler.{MultipartSupport, MultipartHandler}
import com.outr.net.http.mime.MimeType
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.hyperscala.html._
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.hyperscala.jquery.jQueryComponent
import org.hyperscala.module.Module
import org.powerscala.event.{Intercept, Listenable}
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.property.Property
import org.powerscala.{StorageComponent, Unique, Version}
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.jquery.ui.{ProgressBar, jQueryUI, Dialog}
import org.hyperscala.realtime.Realtime
import language.reflectiveCalls
import com.outr.net.http.session.Session

/**
 * Hyperscala wrapper around Dropzone.js (http://www.dropzonejs.com/).
 *
 * @author Matt Hicks <mhicks@outr.com>
 */
object Dropzone extends Module with StorageComponent[Dropzone, HTMLTag] {
  private def FileScript(id: String, eventName: String) =
    new JavaScriptString(s"""
      |function(file) {
      | realtimeSend('$id', '$eventName', {lastModified: file.lastModifiedDate.getTime(), name: file.name, size: file.size, type: file.type});
      |}
    """.stripMargin)
  private def FileThumbnailScript(id: String, eventName: String) =
    new JavaScriptString(s"""
      |function(file, dataUrl) {
      | if (document.dataUrls == null) {
      |   document.dataUrls = {};
      | }
      | document.dataUrls['$id'] = dataUrl;
      | realtimeSend('$id', '$eventName', {lastModified: file.lastModifiedDate.getTime(), name: file.name, size: file.size, type: file.type});
      |}
    """.stripMargin)
  private def FileProgressScript(id: String, eventName: String) =
    new JavaScriptString(s"""
      |function(file, progress, bytesSent) {
      | realtimeSend('$id', '$eventName', {lastModified: file.lastModifiedDate.getTime(), name: file.name, size: file.size, type: file.type, progress: progress, bytesSent: bytesSent});
      |}
    """.stripMargin)
  private def FileErrorScript(id: String, eventName: String) =
    new JavaScriptString(s"""
      |function(file, message) {
      | realtimeSend('$id', '$eventName', {lastModified: file.lastModifiedDate.getTime(), name: file.name, size: file.size, type: file.type, message: message});
      |}
    """.stripMargin)
  private def UseThumbnailScript[S <: Session](webpage: Webpage[S], dropzone: Dropzone, img: tag.Img) = {
    Realtime.sendJavaScript(webpage,
      s"""
        |$$('#${img.identity}').attr('src', document.dataUrls['${dropzone.wrapped.identity}']);
        |document.dataUrls['${dropzone.wrapped.identity}'] = null;
      """.stripMargin)
  }

  val path = Property[String](default = Some("/dropzone_upload"))

  def name = "dropzone"

  def version = Version(3, 10, 2)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/dropzone.js", "dropzone.js")
    website.addHandler(new DropzoneHandler[S](website), path())
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/dropzone.js")
  }

  override protected def create(t: HTMLTag) = new Dropzone(t)
}

class Dropzone(container: HTMLTag) extends jQueryComponent with Listenable {
  val id = Unique()
  val fileReceived = new UnitProcessor[(String, File)]("fileReceived")

  /**
   * Has to be specified on elements other than form (or when the form doesn't have an action attribute)
   *
   * Defaults to Dropzone.path()
   */
  val url = property("url", Dropzone.path(), includeDefault = true)
  /**
   * Defaults to "post" and can be changed to "put" if necessary.
   */
  val method = property("method", "post")
  val withCredentials = property("withCredentials", false)
  /**
   * How many file uploads to process in parallel.
   *
   * Defaults to 2.
   */
  val parallelUploads = property("parallelUploads", 2)
  val uploadMultiple = property("uploadMultiple", false)
  val maxFilesize = property("maxFilesize", 256L)
  val paramName = property("paramName", "file")
  val createImageThumbnails = property("createImageThumbnails", true)
  val maxThumbnailSize = property("maxThumbnailSize", 10)
  val thumbnailWidth = property("thumbnailWidth", 100)
  val thumbnailHeight = property("thumbnailHeight", 100)
  val maxFiles = property("maxFiles", -1)
  /**
   * Parameters to be sent via POST to the server in the multipart request.
   *
   * The values pageId and tagId are automatically added upon initialization.
   */
  val params = property[List[(String, String)]]("params", Nil, toJS = (list: List[(String, String)]) => JavaScriptString(list.map(i => s"'${i._1}': '${i._2}'").mkString("{", ", ", "}")))
  /**
   * If true, the dropzone element itself will be clickable, if false nothing will be clickable. Otherwise you can pass
   * an HTML element, a CSS selector (for multiple elements) or an array of those.
   *
   * Defaults to "true"
   */
  val clickable = property("clickable", "true")
  val ignoreHiddenFiles = property("ignoreHiddenFiles", true)
  val acceptedFiles = property[List[String]]("acceptedFiles", null)
  val autoProcessQueue = property("autoProcessQueue", true)
  val autoQueue = property("autoQueue", true)
  val addRemoveLinks = property("addRemoveLinks", false)
  val previewsContainer = property[String]("previewsContainer", null)
  val forceFallback = property("forceFallback", false)
  val previewTemplate = property[String]("previewTemplate", null)

  val dictDefaultMessage = property("dictDefaultMessage", "Drop files here to upload")
  val dictFallbackMessage = property("dictFallbackMessage", "Your browser does not support drag'n'drop file uploads.")
  val dictFallbackText = property("dictFallbackText", "Please use the fallback form below to upload your files like in the olden days.")
  val dictFileTooBig = property("dictFileTooBig", "File is too big ({{filesize}}MiB). Max filesize: {{maxFilesize}}MiB.")
  val dictInvalidFileType = property("dictInvalidFileType", "You can't upload files of this type.")
  val dictResponseError = property("dictResponseError", "Server responded with {{statusCode}} code.")
  val dictCancelUpload = property("dictCancelUpload", "Cancel upload")
  val dictCancelUploadConfirmation = property("dictCancelUploadConfirmation", "Are you sure you want to cancel this upload?")
  val dictRemoveFile = property("dictRemoveFile", "Remove file")
  val dictRemoveFileConfirmation = property[String]("dictRemoveFileConfirmation", null)
  val dictMaxFilesExceeded = property("dictMaxFilesExceeded", "You can not upload any more files.")

  val drop = property[JavaScriptContent]("drop", null)
  val dragStart = property[JavaScriptContent]("dragstart", null)
  val dragEnd = property[JavaScriptContent]("dragend", null)
  val dragEnter = property[JavaScriptContent]("dragenter", null)
  val dragOver = property[JavaScriptContent]("dragover", null)
  val dragLeave = property[JavaScriptContent]("dragleave", null)

  val addedFile = property[JavaScriptContent]("addedfile", null)
  val removedFile = property[JavaScriptContent]("removedfile", null)
  val thumbnail = property[JavaScriptContent]("thumbnail", null)
  val error = property[JavaScriptContent]("error", null)
  val processing = property[JavaScriptContent]("processing", null)
  val uploadProgress = property[JavaScriptContent]("uploadprogress", null)
  val sending = property[JavaScriptContent]("sending", null)
  val success = property[JavaScriptContent]("success", null)
  val complete = property[JavaScriptContent]("complete", null)
  val canceled = property[JavaScriptContent]("canceled", null)
  val maxFilesReached = property[JavaScriptContent]("maxFilesReached", null)
  val maxFilesExceeded = property[JavaScriptContent]("maxFilesExceeded", null)

  lazy val addedFileEvent = new UnitProcessor[DropzoneFileEvent]("addedFileEvent")
  lazy val removedFileEvent = new UnitProcessor[DropzoneFileEvent]("removedFileEvent")
  lazy val thumbnailEvent = new UnitProcessor[DropzoneThumbnailEvent]("thumbnailEvent")
  lazy val processingEvent = new UnitProcessor[DropzoneFileEvent]("processingEvent")
  lazy val uploadProgressEvent = new UnitProcessor[DropzoneProgressEvent]("uploadProgressEvent")
  lazy val successEvent = new UnitProcessor[DropzoneFileEvent]("successEvent")
  lazy val errorEvent = new UnitProcessor[DropzoneErrorEvent]("errorEvent")
  lazy val completeEvent = new UnitProcessor[DropzoneFileEvent]("completeEvent")

  val processingMultiple = property[JavaScriptContent]("processingmultiple", null)
  val sendingMultiple = property[JavaScriptContent]("sendingmultiple", null)
  val successMultiple = property[JavaScriptContent]("successmultiple", null)
  val completeMultiple = property[JavaScriptContent]("completemultiple", null)
  val cancelMultiple = property[JavaScriptContent]("cancelmultiple", null)

  container.connected[Webpage[_]] {
    case webpage => {
      params := params() ::: List("pageId" -> webpage.pageId, "tagId" -> container.identity)
      init()
    }
  }

  def connectEventsToServer() = {
    // Override default JavaScript to send to server instead of executing in the browser
    addedFile := Dropzone.FileScript(container.identity, "addedFile")
    removedFile := Dropzone.FileScript(container.identity, "removedFile")
    thumbnail := Dropzone.FileThumbnailScript(container.identity, "thumbnail")
    processing := Dropzone.FileScript(container.identity, "processing")
    uploadProgress := Dropzone.FileProgressScript(container.identity, "uploadProgress")
    success := Dropzone.FileScript(container.identity, "success")
    complete := Dropzone.FileScript(container.identity, "complete")
    error := Dropzone.FileErrorScript(container.identity, "error")

    // Add handler for new events
    container.eventReceived.on {
      case evt => evt.event match {
        case "addedFile" => {
          val (lastModified, name, size, mimeType) = fileValues(evt.json)
          addedFileEvent.fire(DropzoneFileEvent(lastModified, name, size, mimeType))
          Intercept.Stop
        }
        case "removedFile" => {
          val (lastModified, name, size, mimeType) = fileValues(evt.json)
          removedFileEvent.fire(DropzoneFileEvent(lastModified, name, size, mimeType))
          Intercept.Stop
        }
        case "thumbnail" => {
          val (lastModified, name, size, mimeType) = fileValues(evt.json)
          thumbnailEvent.fire(DropzoneThumbnailEvent(lastModified, name, size, mimeType))
          Intercept.Stop
        }
        case "processing" => {
          val (lastModified, name, size, mimeType) = fileValues(evt.json)
          processingEvent.fire(DropzoneFileEvent(lastModified, name, size, mimeType))
          Intercept.Stop
        }
        case "uploadProgress" => {
          val (lastModified, name, size, mimeType) = fileValues(evt.json)
          val progress = evt.json.double("progress")
          val bytesSent = evt.json.long("bytesSent")
          uploadProgressEvent.fire(DropzoneProgressEvent(lastModified, name, size, mimeType, progress, bytesSent))
          Intercept.Stop
        }
        case "success" => {
          val (lastModified, name, size, mimeType) = fileValues(evt.json)
          successEvent.fire(DropzoneFileEvent(lastModified, name, size, mimeType))
          Intercept.Stop
        }
        case "complete" => {
          val (lastModified, name, size, mimeType) = fileValues(evt.json)
          completeEvent.fire(DropzoneFileEvent(lastModified, name, size, mimeType))
          Intercept.Stop
        }
        case "error" => {
          val (lastModified, name, size, mimeType) = fileValues(evt.json)
          val message = evt.json.string("message")
          errorEvent.fire(DropzoneErrorEvent(lastModified, name, size, mimeType, message))
          Intercept.Stop
        }
        case _ => Intercept.Continue
      }
    }
  }

  def applyThumbnail[S <: Session](img: tag.Img) = {
    Dropzone.UseThumbnailScript(webpage, this, img)
  }

  private def fileValues(json: JsonObject) = {
    val lastModified = json.long("lastModified")
    val name = json.string("name")
    val size = json.long("size")
    val mimeType = json.string("type")
    (lastModified, name, size, mimeType)
  }

  override protected def functionName = "dropzone"

  override protected def autoInit = false

  override protected def wrapped = container
}

case class DropzoneFileEvent(lastModified: Long, name: String, size: Long, mimeType: String)
case class DropzoneThumbnailEvent(lastModified: Long, name: String, size: Long, mimeType: String)
case class DropzoneProgressEvent(lastModified: Long, name: String, size: Long, mimeType: String, progress: Double, bytesSent: Long)
case class DropzoneErrorEvent(lastModified: Long, name: String, size: Long, mimeType: String, message: String)

class DropzoneHandler[S <: Session](website: Website[S]) extends MultipartHandler {
  override def create(request: HttpRequest, response: HttpResponse) = {
    new DropzoneSupport[S](website)
  }
}

class DropzoneSupport[S <: Session](website: Website[S]) extends MultipartSupport {
  private var page: Webpage[S] = _
  private var t: HTMLTag = _
  private var dropzone: Dropzone = _

  override def begin(request: HttpRequest, response: HttpResponse) = {}

  override def onField(name: String, value: String) = name match {
    case "pageId" => page = website.pages.byId[Webpage[S]](value).getOrElse(throw new NullPointerException(s"Cannot find page by id: $value."))
    case "tagId" => {
      t = page.getById[HTMLTag](value)
      dropzone = Dropzone(t)
    }
  }

  override def finish(request: HttpRequest, response: HttpResponse) = {
    response.copy(content = StringContent("Success", ContentType.Plain), status = HttpResponseStatus.OK)
  }

  override def onFile(filename: String, file: File) = dropzone.fileReceived.fire(filename -> file)
}